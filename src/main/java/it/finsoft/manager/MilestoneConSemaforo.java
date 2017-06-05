package it.finsoft.manager;

import java.util.List;
import it.finsoft.entity.Milestone;

public class MilestoneConSemaforo extends Milestone {

	private static final long serialVersionUID = 5115044789737668255L;
	private Milestone milestone;
	private boolean semaforo;
	private List<String> tags;

	public MilestoneConSemaforo() {
	}

	public MilestoneConSemaforo(Milestone milestone, boolean semaforo, List<String> tags) {
		this.milestone = milestone;
		this.semaforo = semaforo;
		this.tags = tags;
	}

	public Milestone getMilestone() {
		return milestone;
	}

	public void setMilestone(Milestone milestone) {
		this.milestone = milestone;
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

}