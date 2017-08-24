package it.finsoft.resources;

import java.util.List;

import it.finsoft.entity.Azione;
import it.finsoft.entity.Calendario;
import it.finsoft.entity.CalendarioMilestone;
import it.finsoft.entity.DettaglioEvento;
import it.finsoft.entity.Entita;
import it.finsoft.entity.Evento;
import it.finsoft.entity.Milestone;
import it.finsoft.entity.MilestoneMilestone;
import it.finsoft.entity.TipoEvento;
import it.finsoft.manager.AzioneManager;
import it.finsoft.manager.CalendarioManager;
import it.finsoft.manager.CalendarioMilestoneManager;
import it.finsoft.manager.DettaglioEventoManager;
import it.finsoft.manager.EntitaManager;
import it.finsoft.manager.EventoManager;
import it.finsoft.manager.MilestoneManager;
import it.finsoft.manager.MilestoneMilestoneManager;
import it.finsoft.manager.TipoEventoManager;

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

/**
 * Questa classe include tutti i possibili servizi REST.
 * 
 * 
 */
// TODO in JAX-RS 2.0, suddividerla in classi
@Path("resources")
@Stateless
@Produces({ MediaType.APPLICATION_JSON })
public class AllResources {

	// ====Entita===========================================================

	@Inject
	EntitaManager entitaManager;

	@GET
	@Path("Entita")
	public List<Entita> findAll() {
		return entitaManager.findAll();
	}

	@GET
	@Path("Entita({id})")
	public Entita findById(@PathParam("id") Long id) {
		return entitaManager.findById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("Entita")
	public Entita create(Entita cal) {
		System.out.println("post resources, salvo entita " + cal);
		return entitaManager.save(cal);
	}

	@DELETE
	@Path("Entita({id})")
	public void delete(@PathParam("id") Long id) {
		entitaManager.remove(id);
	}

	@PUT
	@Path("Entita({id})")
	public void update(@PathParam("id") Long id, Entita m) {
		m.setIdEntita(id);
		entitaManager.save(m);
	}

	// ==== Tipo
	// evento===========================================================

	@Inject
	TipoEventoManager tipoEventoManager;

	@GET
	@Path("TipiEvento")
	public List<TipoEvento> findAll2() {
		return tipoEventoManager.findAll();
	}

	@GET
	@Path("TipiEvento({id})")
	public TipoEvento findById2(@PathParam("id") Long id) {
		return tipoEventoManager.findById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("TipiEvento")
	public TipoEvento create2(TipoEvento cal) {
		System.out.println("post resources, salvo entita " + cal);
		return tipoEventoManager.save(cal);
	}

	@DELETE
	@Path("TipiEvento({id})")
	public void delete2(@PathParam("id") Long id) {
		tipoEventoManager.remove(id);
	}

	@PUT
	@Path("TipiEvento({id})")
	public void update2(@PathParam("id") Long id, TipoEvento m) {
		m.setidTipoEvento(id);
		tipoEventoManager.save(m);
	}

	// ==== milestones========================================
	@Inject
	MilestoneManager milestoneManager;

	@GET
	@Path("Milestones")
	public List<Milestone> findAll3() {
		return milestoneManager.findAll();
	}

	@GET
	@Path("Milestones({id})")
	public Milestone findById3(@PathParam("id") Long id) {
		return milestoneManager.findById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("Milestones")
	public Milestone create3(Milestone cal) {
		System.out.println("post resources, salvo entita " + cal);
		return milestoneManager.save(cal);
	}

	@DELETE
	@Path("Milestones({id})")
	public void delete3(@PathParam("id") Long id) {
		milestoneManager.remove(id);
	}

	@PUT
	@Path("Milestones({id})")
	public void update3(@PathParam("id") Long id, Milestone m) {
		m.setIdMilestone(id);
		milestoneManager.save(m);
	}

	// ===milestone milestone=============================================
	@Inject
	MilestoneMilestoneManager milestoneMilestonesManager;

	@GET
	@Path("MilestoneMilestones")
	public List<MilestoneMilestone> findAll4() {
		return milestoneMilestonesManager.findAll();
	}

	@GET
	@Path("MilestoneMilestones({idM},{idCh})")
	public MilestoneMilestone findById4(@PathParam("idM") Long id,
			@PathParam("idCh") Long idCh) {
		return milestoneMilestonesManager.findById(id, idCh);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("MilestoneMilestones")
	public MilestoneMilestone create4(MilestoneMilestone cal) {
		System.out.println("post resources, salvo semaforomilestone " + cal);
		return milestoneMilestonesManager.preCheck(cal);
	}

	@DELETE
	@Path("MilestoneMilestones({idM},{idCh})")
	public void delete(@PathParam("idM") Long id, @PathParam("idCh") Long idCh) {
		milestoneMilestonesManager.remove(id, idCh);
	}

	@PUT
	@Path("MilestoneMilestones({id},{idCh})")
	// Bisogna vedere se va bene per l'update via interfaccia
	public void update(@PathParam("id") Long id, @PathParam("idCh") Long idCh,
			MilestoneMilestone m) {
		m.setMilestone(milestoneManager.findById(id));
		m.setMilestoneComponente(milestoneManager.findById(idCh));
		milestoneMilestonesManager.save(m);
	}

	// =====Eventi==============================================
	@Inject
	EventoManager eventoManager;

	@GET
	@Path("Eventi")
	public List<Evento> findAll5() {
		return eventoManager.findAll();
	}

	@GET
	@Path("Eventi({id})")
	public Evento findById5(@PathParam("id") Long id) {
		return eventoManager.findById(id);
	}

	@POST
	@Path("Eventi")
	@Consumes(MediaType.APPLICATION_JSON)
	public Evento create5(Evento cal) {
		System.out.println("post resources, salvo entita " + cal);
		return eventoManager.save(cal);
	}

	@DELETE
	@Path("Eventi({id})")
	public void delete5(@PathParam("id") Long id) {
		eventoManager.remove(id);
	}

	@PUT
	@Path("Eventi({id})")
	public void update5(@PathParam("id") Long id, Evento m) {
		m.setIdEvento(id);
		eventoManager.save(m);
	}

	// ===Dettagli evento============================================
	@Inject
	DettaglioEventoManager dettaglioEventoManager;

	@GET
	@Path("DettagliEvento")
	public List<DettaglioEvento> findAll6() {
		return dettaglioEventoManager.findAll();
	}

	@GET
	@Path("DettagliEvento({id})")
	public DettaglioEvento findById6(@PathParam("id") Long id) {
		return dettaglioEventoManager.findById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("DettagliEvento")
	public DettaglioEvento create6(DettaglioEvento cal) {
		System.out.println("post resources, salvo entita " + cal);
		return dettaglioEventoManager.save(cal);
	}

	@DELETE
	@Path("DettagliEvento({id})")
	public void delete6(@PathParam("id") Long id) {
		dettaglioEventoManager.remove(id);
	}

	@PUT
	@Path("DettagliEvento({id})")
	public void update6(@PathParam("id") Long id, DettaglioEvento m) {
		m.setIdDettaglioEvento(id);
		dettaglioEventoManager.save(m);
	}

	// ===Calendari====================================
	@Inject
	CalendarioManager calendarioManager;

	@GET
	@Path("Calendari")
	public List<Calendario> findAll7() {
		return calendarioManager.findAll();
	}

	@GET
	@Path("Calendari({id})")
	public Calendario findById7(@PathParam("id") Long id) {
		return calendarioManager.findById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("Calendari")
	public Calendario create7(Calendario cal) {
		System.out.println("post resources, salvo entita " + cal);
		return calendarioManager.save(cal);
	}

	@DELETE
	@Path("Calendari({id})")
	public void delete7(@PathParam("id") Long id) {
		calendarioManager.remove(id);
	}

	@PUT
	@Path("Calendari({id})")
	public void update7(@PathParam("id") Long id, Calendario m) {
		m.setIdCalendario(id);
		calendarioManager.save(m);
	}

	// ==Calendari milestone===========================================
	@Inject
	CalendarioMilestoneManager calendarioMilestoneManager;

	@GET
	@Path("CalendarioMilestones")
	public List<CalendarioMilestone> findAll8() {
		return calendarioMilestoneManager.findAll();
	}

	@GET
	@Path("CalendarioMilestones({id})")
	public CalendarioMilestone findById8(@PathParam("id") Long id) {
		return calendarioMilestoneManager.findById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("CalendarioMilestones")
	public CalendarioMilestone create8(CalendarioMilestone cal) {
		System.out.println("post resources, salvo entita " + cal);
		return calendarioMilestoneManager.save(cal);
	}

	@DELETE
	@Path("CalendarioMilestones({id})")
	public void delete8(@PathParam("id") Long id) {
		calendarioMilestoneManager.remove(id);
	}

	@PUT
	@Path("CalendarioMilestones({id})")
	public void update8(@PathParam("id") Long id, CalendarioMilestone m) {
		m.setIdCalendarioMilestone(id);
		calendarioMilestoneManager.save(m);
	}

	/*
	 * { "milestone" : { "idMilestone" : 1 }, "calendario" : { "idCalendario" :
	 * 1 }, "tag" : "fasdfafa" }
	 */

	/* ---- RELAZIONI ---- */

	@GET
	@Path("Calendari({idc})/Milestone")
	public List<CalendarioMilestone> findByIdCalendario8(
			@PathParam("idc") Long idCalendario) {
		return calendarioMilestoneManager.findByIdCalendario(idCalendario);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("Calendari({idc})/Milestone")
	public CalendarioMilestone create8(@PathParam("idc") Long idCalendario,
			CalendarioMilestone cal) {
		return calendarioMilestoneManager.save(idCalendario, cal);
	}

	// ===Azioni=================================
	@Inject
	AzioneManager azioneManager;

	@GET
	@Path("Azioni")
	public List<Azione> findAll9() {
		return azioneManager.findAll();
	}

	@GET
	@Path("Azioni({id})")
	public Azione findById9(@PathParam("id") Long id) {
		return azioneManager.findById(id);
	}

	@POST
	@Path("Azioni")
	@Consumes(MediaType.APPLICATION_JSON)
	public Azione create9(Azione cal) {
		System.out.println("post resources, salvo azione " + cal);
		return azioneManager.save(cal);
	}

	@DELETE
	@Path("Azioni({id})")
	public void delete9(@PathParam("id") Long id) {
		azioneManager.remove(id);
	}

	@PUT
	@Path("Azioni({id})")
	public void update9(@PathParam("id") Long id, Azione m) {
		m.setIdAzione(id);
		azioneManager.save(m);
	}
}
