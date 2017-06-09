package it.finsoft.resources;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import it.finsoft.entity.TipoEvento;
import it.finsoft.manager.TipoEventoManager;

@Stateless
@Path("resources")
@Produces({ MediaType.APPLICATION_JSON })
public class TipoEventoResources {

	@Inject
	TipoEventoManager tipoEventoManager;

	@GET
	@Path("TipiEvento")
	public List<TipoEvento> findAll() {
		return tipoEventoManager.findAll();
	}

	@GET
	@Path("TipiEvento({id})")
	public TipoEvento findById(@PathParam("id") Long id) {
		return tipoEventoManager.findById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("TipiEvento({id})")
	public TipoEvento create(TipoEvento cal) {
		System.out.println("post resources, salvo entita " + cal);
		return tipoEventoManager.save(cal);
	}

	@DELETE
	@Path("TipiEvento({id})")
	public void delete(@PathParam("id") Long id) {
		tipoEventoManager.remove(id);
	}

	@PUT
	@Path("TipiEvento({id})") // richiede di inserire (in json) tutti i campi obbligatori
	public void update(@PathParam("id") Long id, TipoEvento m) {
		m.setidTipoEvento(id);
		tipoEventoManager.save(m);
	}

	/* ---- TEST RESOURCES ---- */
	@GET
	@Path("TipiEvento/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String prova() {
		System.out.println("ok TipiEvento");
		return "ok TipiEvento";
	}
	/* ---- TEST RESOURCES ---- */

}
