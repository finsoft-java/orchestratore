package it.finsoft.resources;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import it.finsoft.entity.Entita;
import it.finsoft.entity.Evento;
import it.finsoft.manager.EntitaManager;
import it.finsoft.manager.EventoManager;
import it.finsoft.manager.TipoEventoManager;

@Stateless
@Path("collector")
@Produces({ MediaType.APPLICATION_JSON })
public class WSCollector {

	@Inject
	EntitaManager entitam;
	@Inject
	EventoManager eventom;
	@Inject
	TipoEventoManager tipoevm;

	/*
	 * Metodo GET per inserire dati via http esegue due query per risolvere dal
	 * codiceEnt a Entita e da codiceTipi a TipoEvento
	 */
	@GET
	public Evento create(@QueryParam("entita") String codiceEnt, @QueryParam("tipiEvento") String codiceTipi,
			@QueryParam("tag") String tag) {
		Evento evento = new Evento();
		evento.setEntita(entitam.findByCod(codiceEnt));
		evento.setTipoEvento(tipoevm.findByCod(codiceTipi));
		evento.setTag(tag);
		return eventom.save(evento);
	}
	
}
