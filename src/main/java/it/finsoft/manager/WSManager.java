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
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.jboss.logging.Logger;

import it.finsoft.entity.Calendario;
import it.finsoft.entity.DettaglioEvento;
import it.finsoft.entity.Entita;
import it.finsoft.entity.Evento;
import it.finsoft.entity.Milestone;
import it.finsoft.entity.MilestoneMilestone;
import it.finsoft.entity.TipoEvento;
import it.finsoft.util.BusinessException;

/**
 * Contiene le implementazioni di (quasi) tutti i webservice.
 * 
 */
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
	 * Inserisce un evento a sistema (Webservice Collector). Non c'e'nessuna
	 * relazione con le milestone, codiceEntita e codiceTipoEvento potrebbero
	 * non corrispondere ad una milestone.
	 * 
	 * @throws HibernatetException
	 */
	public void insertEvento(Entita entita, TipoEvento tipoEvento, String tag, Map<String, String> dettagli) {

		Evento e = new Evento();
		e.setEntita(entita);
		e.setTipoEvento(tipoEvento);
		e.setTag(tag);
		List<DettaglioEvento> listaDettagliEvento = e.getDettagliEvento();

		for (String key : dettagli.keySet()) {
			DettaglioEvento dettaglio = new DettaglioEvento();
			String valore = dettagli.get(key);
			dettaglio.setChiave(key);
			dettaglio.setValore(valore);
			dettaglio.setEvento(e);
			listaDettagliEvento.add(dettaglio);
		}
		e.setDettagliEvento(listaDettagliEvento);

		eventoManager.save(e);
	}

	/**
	 * Inserisce un evento a sistema (Webservice Collector). Non c'e'nessuna
	 * relazione con le milestone, codiceEntita e codiceTipoEvento potrebbero
	 * non corrispondere ad una milestone.
	 * 
	 * @throws HibernatetException
	 * @throws BusinessException
	 */
	public void insertEvento(String codiceEntita, String codiceTipoEvento, String tag, Map<String, String> dettagli)
			throws BusinessException {

		codiceEntita = utilityCheck.trimToUp(codiceEntita);
		Entita entita = entitaManager.findByCod(codiceEntita);
		if (entita == null)
			throw new BusinessException("Entita '" + codiceEntita + "' non esistente");

		codiceTipoEvento = utilityCheck.trimToUp(codiceTipoEvento);
		TipoEvento tipoEvento = tipoEventoManager.findByCod(codiceTipoEvento);
		if (tipoEvento == null)
			throw new BusinessException("Tipo evento  '" + codiceTipoEvento + "' non esistente");

		insertEvento(entita, tipoEvento, tag, dettagli);
	}

	/**
	 * per la milestone (aggregata) data, è stata correttamente definita la tag
	 * data? Per le milestone atomiche la risposta è sempre positiva.
	 * 
	 * @param codiceMilestone
	 * @param tag
	 * @return
	 * @throws BusinessException
	 */
	public boolean tagBenDefinita(String codiceMilestone, String tag) throws BusinessException {
		Milestone milestone = milestoneManager.findByCod(codiceMilestone);
		if (milestone == null)
			throw new BusinessException("Milestone '" + codiceMilestone + "' inesistente");
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

		if (!milestoneManager.milestoneAggregata(milestone))
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
	 * Restiutisce un codice partizione costruito sulla base degli input. Il
	 * codice è essenzialmente una concatenzaione degli input, più una lettera
	 * che si trova in apposita tabella di configurazione.
	 * 
	 * @param nomeTabella
	 * @param annoMese
	 *            YYYYMM
	 * @param giro
	 *            01, 02, 03...
	 * @param perimetro
	 *            A, B, C
	 * @param abi
	 *            can be null
	 * @return
	 * @throws BusinessException 
	 */
	public String getPartCol(String nomeTabella, String annoMese, String giro, String perimetro, String abi) throws BusinessException {

		if (nomeTabella == null || nomeTabella.equals("")) {
			throw new BusinessException("missing parameter 'nomeTabella'");
		}

		if (annoMese == null || annoMese.equals("")) {
			throw new BusinessException("missing parameter 'annoMese'");
		}

		if (annoMese.length() != 6) {
			throw new BusinessException("'annoMese' must be in form 'YYYYMM'");
		}

		if (giro == null || giro.equals("")) {
			throw new BusinessException("missing parameter 'giro'");
		}

		if (giro.length() > 2) {
			throw new BusinessException("'giro' can have at most 2 characters");
		}

		if (perimetro == null || perimetro.equals("")) {
			throw new BusinessException("missing parameter 'perimetro'");
		}

		if (perimetro.length() > 1) {
			throw new BusinessException("'perimetro' must be 1 char long");
		}

		if (abi != null) {
			if (abi.equals(""))
				abi = null;
			else if (abi.length() != 5)
				throw new BusinessException("'abi' can be null, or a 5-digits ABI.");
		}

		// TODO. Momentaneaente, chiamiamo la stored procedure esistente.
		// In futuro, dovremo convertire questa procedure in Java.
		StoredProcedureQuery query = em.createStoredProcedureQuery("PKG_INPUT_CAR_CRMS.prepareInputAbi");
		query.registerStoredProcedureParameter("nomeTabella", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("idPeriodo", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("idGiro", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("idPerimetro", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("simula", Character.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("inABI", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("partOut", String.class, ParameterMode.OUT);
		query.setParameter("nomeTabella", nomeTabella);
		query.setParameter("idPeriodo", annoMese);
		query.setParameter("idGiro", giro);
		query.setParameter("idPerimetro", perimetro);
		query.setParameter("simula", new Character('N'));
		query.setParameter("inABI", abi);
		query.execute();
		String partOut = (String) query.getOutputParameterValue("partOut");

		return partOut;

	}

}