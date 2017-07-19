package it.finsoft.manager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import it.finsoft.entity.Calendario;
import it.finsoft.entity.DettaglioEvento;
import it.finsoft.entity.Entita;
import it.finsoft.entity.Evento;
import it.finsoft.entity.Milestone;
import it.finsoft.entity.MilestoneMilestone;
import it.finsoft.entity.TipoEvento;

@Stateless
public class WSManager {

	@PersistenceContext
	EntityManager em;
	@Inject
	EntitaManager entitaManager;
	@Inject
	EventoManager eventoManager;
	@Inject
	MilestoneManager milestoneManager;
	@Inject
	CalendarioMilestoneManager calendarioMilestoneManager;
	@Inject
	TipoEventoManager tipoEventoManager;
	@Inject
	UtilityCheck utilityCheck;

	public final static Logger LOG = Logger.getLogger(WSManager.class);

	// ---------------------------------WSReset----------------------------------------//

	/**
	 * Esegue i comandi contenuti in script.sql
	 */
	public String resetDb() throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(getClass().getResourceAsStream("/script.sql")));
		String sql = "";
		String error = "";
		while (reader.ready() == true) {
			try {
				sql = reader.readLine().toString();
				if (sql == null)
					continue;
				sql = sql.trim();
				if (sql.equals("") || sql.startsWith("--"))
					continue;
				Query q = em.createNativeQuery(sql);
				q.executeUpdate();
			} catch (Exception e) {
				LOG.error("Error while executing SQL command", e);
				error += "Error while executing SQL command: " + sql + ";";
			}
		}
		reader.close();
		// mettere un if, eventualmente considerare di stampare la riga SQL che
		// ha restituito l'errore
		// TODO da completare
		if (error == "") {
			LOG.info("RESET effettuato con successo");
			return "RESET dati predefiniti DB effettuato";
		} else {
			return "RESET eseguito con errori: ";
		}
	}

	// classe di output per il polling
	public static class DatiPolling {
		public Boolean semaforoOk = Boolean.TRUE;
		public Integer expectedMilestones = 0;
		public Integer okMilestones = 0;
		public List<Evento> eventi = new ArrayList<>();
		public String errorMessage = null;
		public List<String> NonVerificati = new ArrayList<>();
	}

	// ------------------------------------WSCollector--------------------------------------//
	/**
	 * Inserisce un evento a sistema (Webservice Collector). Non c'è nessuna
	 * relazione con le milestone, codiceEntita e codiceTipoEvento potrebbero
	 * non corrispondere ad una milestone.
	 * 
	 * @param codiceEntita
	 * @param codiceTipoEvento
	 * @param tag
	 * @param keys
	 * @param values
	 * @return
	 */
	public DatiCollector insertEvent(String codiceEntita, String codiceTipoEvento, String tag,
			Map<String, String> dettagli) {
		DatiCollector result = new DatiCollector();
		try {
			Evento e = new Evento();
			codiceEntita = utilityCheck.trimToUp(codiceEntita);
			codiceTipoEvento = utilityCheck.trimToUp(codiceTipoEvento);
			e.setEntita(entitaManager.findByCod(codiceEntita));
			e.setTipoEvento(tipoEventoManager.findByCod(codiceTipoEvento));
			e.setTag(tag);
			List<DettaglioEvento> listaDettagliEvento = e.getDettagliEvento();
			// TODO controllare che keys e values abbiano lo stesso numero di
			// valori

			for (String key : dettagli.keySet()) {
				DettaglioEvento dettaglio = new DettaglioEvento();
				String valore = dettagli.get(key);
				dettaglio.setChiave(key);
				dettaglio.setValore(valore);
				dettaglio.setEvento(e);
				listaDettagliEvento.add(dettaglio);
			}
			e.setDettagliEvento(listaDettagliEvento);

			// per ogni chiave, inserire un record chiave/valore nella tabella
			// dettagli
			eventoManager.save(e);
			result.evento = e;
			result.listaDettagli.addAll(listaDettagliEvento);

		} catch (Exception sqlError) {
			if (result.detailError == null) {
				LOG.error("ERROR: nessuna corrispondenza con " + codiceEntita + " e " + codiceTipoEvento
						+ " nella base dati, controllare che siano presenti");
				result.eventError = "ERROR: nessuna corrispondenza con " + codiceEntita + " e " + codiceTipoEvento
						+ " nella base dati, controllare che siano presenti";
				return result;
			}

		}
		return result;
	}

	/**
	 * Classe di output per il collector
	 *
	 */
	public static class DatiCollector {
		public Evento evento;
		public String eventError = null;
		public String detailError = null;
		public List<DettaglioEvento> listaDettagli = new ArrayList<>();
	}

	/**
	 * per la milestone (aggregata) data, è stata correttamente definita la tag
	 * data? Per le milestone atomiche la risposta è sempre positiva.
	 * 
	 * @param codiceMilestone
	 * @param tag
	 * @return
	 */
	public boolean tagBenDefinita(String codiceMilestone, String tag) {
		Milestone milestone = milestoneManager.findByCod(codiceMilestone);
		return tagBenDefinita(milestone, tag);
	}

	/**
	 * per la milestone (aggregata) data, è stata correttamente definita la tag
	 * data? Per le milestone atomiche la risposta è sempre positiva.
	 * 
	 * @param codiceMilestone
	 * @param tag
	 * @return
	 */
	public boolean tagBenDefinita(Milestone milestone, String tag) {
		if (milestone == null)
			return false;

		if (!milestoneAggregata(milestone))
			return true;

		// la milestone/tag appare in qualche calendario?
		Calendario cal = calendarioMilestoneManager.findUltimoCalendario(milestone, tag);
		if (cal == null)
			return false;

		// le milestone componenti, appaiono nello stesso calendario, e sono qui
		// ben definite?
		Map<String, String> map = calendarioMilestoneManager.getMapMilestonesTags(cal);
		for (MilestoneMilestone mmChild : milestone.getMilestoneMilestone()) {
			Milestone mChild = mmChild.getMilestone();
			if (!map.containsKey(mChild.getCodice()))
				return false;
			String tagChild = map.get(mChild.getCodice());
			if (tagChild == null || tagChild.trim().equals(""))
				return false;
		}

		// le milestone componenti, sono ricorsivamente ben definite?
		for (MilestoneMilestone mmChild : milestone.getMilestoneMilestone()) {
			Milestone mChild = mmChild.getMilestone();
			if (!tagBenDefinita(mChild, map.get(mChild.getCodice())))
				return false;
		}

		// se arrivo fin qui, tutto a posto.
		return true;
	}

	/**
	 * Ci dice se la milestone è atomica o aggregata
	 * 
	 * @param milestone
	 * @return
	 */
	public boolean milestoneAggregata(Milestone milestone) {
		return milestone != null && milestone.getTipoEvento() == null && milestone.getEntita() == null;
	}

}