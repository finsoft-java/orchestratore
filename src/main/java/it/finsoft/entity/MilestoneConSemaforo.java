package it.finsoft.entity;

import java.util.ArrayList;
import java.util.List;

public class MilestoneConSemaforo extends Milestone {

	private static final long serialVersionUID = 5115044789737668255L;
	// private Milestone milestone;
	private boolean semaforo;
	private List<String> tags = new ArrayList<>();
	private List<Evento> eventi = new ArrayList<>();
	public MilestoneConSemaforo() {
	}

	public MilestoneConSemaforo(Long idMilestone, TipoEvento tipoEvento, Entita entita, String descrizione,
			Azione azione, boolean semaforo, List<String> tags, List<Evento> eventi) {
		this.semaforo = semaforo;
		this.tags = tags;
		this.eventi=eventi;
	}

	public boolean isSemaforo() {
		return semaforo;
	}

	public void setSemaforo(boolean semaforo) {
		this.semaforo = semaforo;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<Evento> getEventi() {
		return eventi;
	}

	public void setEventi(List<Evento> eventi) {
		this.eventi = eventi;
	}

}