package it.finsoft.manager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.finsoft.entity.Calendario;
import it.finsoft.entity.CalendarioMilestone;
import it.finsoft.entity.Evento;
import it.finsoft.entity.Milestone;
import it.finsoft.entity.MilestoneMilestone;

public class WSNewPollingPredeccesoriDiretti {

	@PersistenceContext(unitName = "persistenceUnit")
	private EntityManager em;
	@Inject
	MilestoneManager milestoneManager;

	@Inject
	UtilityCheck utilityCheck;

	@Inject
	CalendarioManager calendarioManager;

	@Inject
	CalendarioMilestoneManager calendarioMilestoneManager;

	public boolean getPollingByDesc(String descMilestone, String tag) {
		descMilestone = utilityCheck.toUp(descMilestone);
		Milestone milestone = null;
		try {
			milestone = milestoneManager.findByDesc(descMilestone);
		} catch (Exception sqlError) {
			return false;
		}
		Calendario cal = findInCalendario(milestone, tag);
		List<MilestoneMilestone> milestoneMilestones = milestone.getMilestoneMilestone();
		System.out.println(milestoneMilestones.size());
		System.out.println(milestoneMilestones);
		List<Milestone> milestonePred = new ArrayList<Milestone>();
		for (MilestoneMilestone single : milestoneMilestones) {
			Milestone milestoneAdd = milestoneManager.findById(single.getMilestoneChild().getIdMilestone());
			milestonePred.add(milestoneAdd);
		}
		List<String> tags = calendarioMilestoneManager.findTags(milestonePred, cal);
		boolean go = newPolling(milestonePred, tags);
		if (go == true) {
			return true;
		} else {
			return false;
		}
	}

	public Calendario findInCalendario(Milestone milestone, String tag) {
		CalendarioMilestone calM = calendarioMilestoneManager.findCalPolling(milestone, tag);
		Calendario cal = calendarioManager.findById(calM.getCalendario().getIdCalendario());
		return cal;
	}

	private boolean newPolling(List<Milestone> milestoneFoglie, List<String> tags) {
		int verified = 0;
		for (int i = 0; i < milestoneFoglie.size(); i++) {
			Evento e = null;
			try {
				e = em.createQuery("FROM Evento WHERE tipoEvento=:tipoEvento AND entita=:entita AND tag=:tag",
						Evento.class).setParameter("tipoEvento", milestoneFoglie.get(i).getTipoEvento())
						.setParameter("entita", milestoneFoglie.get(i).getEntita()).setParameter("tag", tags.get(i))
						.getSingleResult();
			} catch (Exception sqlError) {
				// TODO: handle exception
			}
			if (e != null) {
				verified += 1;
			}
		}
		if (verified == milestoneFoglie.size()) {
			return true;
		} else {
			return false;
		}
	}
}
