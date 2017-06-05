package it.finsoft.resources;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import it.finsoft.manager.WSManager;
import it.finsoft.manager.WSManager.DatiCollector;

@Stateless
@Path("collector")
@Produces({ MediaType.APPLICATION_JSON })
public class WSCollector {

	/*
	 * public static final Logger LOG = Logger.getLogger(WSReset.class);
	 * 
	 * @Inject EntitaManager entitam;
	 * 
	 * @Inject EventoManager eventom;
	 * 
	 * @Inject TipoEventoManager tipoevm;
	 * 
	 * @Inject UtilityChecker syntax;
	 */
	@Inject
	WSManager wsManager;

	/*
	 * Metodo GET per inserire dati via http esegue due query per risolvere dal
	 * codiceEnt a Entita e da codiceTipi a TipoEvento
	 */
	@GET
	public DatiCollector create(@QueryParam("entita") String codiceEnt, @QueryParam("tipiEvento") String codiceTipi,
			@QueryParam("tag") String tag, @QueryParam("key") List<String> keys,
			@QueryParam("valore") List<String> values) {
		return wsManager.getCollector(codiceEnt, codiceTipi, tag, keys, values);
		/*
		 * DatiCollector result = new DatiCollector(); try { Evento e = new
		 * Evento(); codiceEnt = syntax.trimToUp(codiceEnt); codiceTipi =
		 * syntax.trimToUp(codiceTipi);
		 * e.setEntita(entitam.findByCod(codiceEnt));
		 * e.setTipoEvento(tipoevm.findByCod(codiceTipi)); e.setTag(tag);
		 * List<DettaglioEvento> listaDettagliEvento = e.getDettagliEvento(); //
		 * TODO controllare che keys e values abbiano lo stesso numero di //
		 * valori if (keys.size() != values.size()) { // GENERARE ERRORE LOG.
		 * error("ERROR:il numero di key e di valori inseriti non corrisponde");
		 * result.detailError =
		 * "Il numero di key e di valori inseriti non corrisponde, vedere dettaglio per maggiori informazioni"
		 * ; DettaglioEvento dettaglioErr = new DettaglioEvento();
		 * dettaglioErr.setEvento(e); dettaglioErr.setChiave("ERROR");
		 * dettaglioErr.
		 * setValore("il numero di key e di valori inseriti non corrisponde: key="
		 * + keys.toString() + " valori=" + values.toString());
		 * listaDettagliEvento.add(dettaglioErr); } else { for (int i = 0; i <
		 * keys.size(); i++) { DettaglioEvento dettaglio = new
		 * DettaglioEvento(); String key = keys.get(i); String value =
		 * values.get(i); dettaglio.setChiave(key); dettaglio.setValore(value);
		 * dettaglio.setEvento(e); listaDettagliEvento.add(dettaglio); }
		 * e.setDettagliEvento(listaDettagliEvento); } // per ogni chiave,
		 * inserire un record chiave/valore nella tabella // dettagli
		 * eventom.save(e); result.evento = e;
		 * result.listaDettagli.addAll(listaDettagliEvento);
		 * 
		 * } catch (Exception sqlError) { if (result.detailError == null) {
		 * LOG.error("ERROR: nessuna corrispondenza con " + codiceEnt + " e " +
		 * codiceTipi + " nella base dati, controllare che siano presenti");
		 * result.eventError = "ERROR: nessuna corrispondenza con " + codiceEnt
		 * + " e " + codiceTipi +
		 * " nella base dati, controllare che siano presenti"; return result; }
		 * 
		 * }
		 */

	}

	/*
	 * public static class DatiCollector { // per visualizzare l'evento
	 * Inserito, la lista dei dettagli Aggiuntivi // (opzionale) e l'eventuale
	 * errore correlato sulla lista public Evento evento; public String
	 * eventError = null; public String detailError = null; public
	 * List<DettaglioEvento> listaDettagli = new ArrayList<>();
	 * 
	 * }
	 */

}
