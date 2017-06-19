package it.finsoft.manager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.logging.Logger;

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
	// ------------------------------------WSPolling--------------------------------------//

	public DatiPolling getPollingOld(String descMilestone, List<String> tags) {
		DatiPolling result = new DatiPolling();

		LOG.info("Parametri di ricerca: Milestone " + descMilestone + " Tag " + tags);
		// descMilestone = syntax.trimToUp(descMilestone);
		Milestone milestone = null;
		try {
			milestone = milestoneManager.findByCod(descMilestone);
		} catch (Exception sqlError) {
			LOG.error("ERROR: La Milestone: " + descMilestone
					+ " non e' stata trovata, controllare la sintassi o la presenza effettiva sul database");
			result.errorMessage = "ERROR: La Milestone: " + descMilestone
					+ " non e' stata trovata, controllare la sintassi o la presenza effettiva sul database";
			result.semaforoOk = false;
		}
		System.out.println(milestone.getMilestoneMilestone());
		List<MilestoneMilestone> milestoneMilestones = milestone.getMilestoneMilestone();
		result.expectedMilestones = milestoneMilestones.size();
		System.out.println(milestoneMilestones.size());
		System.out.println(milestoneMilestones);
		for (int i = 0; i < milestoneMilestones.size(); i++) {
			MilestoneMilestone sc = milestoneMilestones.get(i);
			// non deve piu' prendere la
			// milestone "INSERITA" ma verificare se le child si sono
			// verificate.
			Milestone m = sc.getMilestoneChild();
			String tag = "";
			try {
				tag = tags.get(i);
				tag = utilityCheck.trimToUp(tag);
			} catch (Exception e) {
				LOG.error("ERROR:non sono stati passati sufficienti tag");
				result.errorMessage = "ERROR:il numero di tag in input (" + tags.size()
						+ ") non corrisponde con il numero di Milestone da controllare (" + milestoneMilestones.size()
						+ "), i tag mancanti verranno calcolati come vuoti";
			}
			Entita ent = m.getEntita();
			TipoEvento tp = m.getTipoEvento();
			List<Evento> tmp = null;
			tmp = eventoManager.findBy(tag, ent, tp);
			System.out.println(tmp);
			if (tmp.isEmpty()) {
				result.semaforoOk = Boolean.FALSE;
				result.NonVerificati.add(m.getDescrizione() + " - " + tag);
			} else {
				++result.okMilestones;
			}
			result.eventi.addAll(tmp);
		}
		return result;
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
	public DatiCollector insertEvent(String codiceEntita, String codiceTipoEvento, String tag, List<String> keys,
			List<String> values) {
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
			if (keys.size() != values.size()) {
				// GENERARE ERRORE
				LOG.error("ERROR:il numero di key e di valori inseriti non corrisponde");
				result.detailError = "Il numero di key e di valori inseriti non corrisponde, vedere dettaglio per maggiori informazioni";
				DettaglioEvento dettaglioErr = new DettaglioEvento();
				dettaglioErr.setEvento(e);
				dettaglioErr.setChiave("ERROR");
				dettaglioErr.setValore("il numero di key e di valori inseriti non corrisponde: key=" + keys.toString()
						+ " valori=" + values.toString());
				listaDettagliEvento.add(dettaglioErr);
			} else {
				for (int i = 0; i < keys.size(); i++) {
					DettaglioEvento dettaglio = new DettaglioEvento();
					String key = keys.get(i);
					String value = values.get(i);
					dettaglio.setChiave(key);
					dettaglio.setValore(value);
					dettaglio.setEvento(e);
					listaDettagliEvento.add(dettaglio);
				}
				e.setDettagliEvento(listaDettagliEvento);
			}
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

}