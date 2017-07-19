package it.finsoft.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@IdClass(MilestoneMilestonePK.class)
@Table(name = "MILESTONE_MILESTONES")
@XmlRootElement
public class MilestoneMilestone implements Serializable {

	private static final long serialVersionUID = 1501385652586677633L;

	@Id
	@ManyToOne
	@JoinColumn(name = "ID_MILESTONE", referencedColumnName = "ID_MILESTONE")
	private Milestone milestone;

	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_MILESTONE_CHILD", referencedColumnName = "ID_MILESTONE")
	private Milestone milestoneComponente;

	@Column(name = "ORDINAMENTO")
	private Integer ordinamento;

	public MilestoneMilestone() {
	}

	public MilestoneMilestone(Milestone milestone, Milestone milestoneChild, Integer ordinamento) {

		this.milestone = milestone;
		this.milestoneComponente = milestoneChild;
		this.ordinamento = ordinamento;
	}

	public Milestone getMilestone() {
		return milestone;
	}

	public void setMilestone(Milestone milestone) {
		this.milestone = milestone;
	}

	public Milestone getMilestoneComponente() {
		return milestoneComponente;
	}

	public void setMilestoneComponente(Milestone milestoneChild) {
		this.milestoneComponente = milestoneChild;
	}

	public Integer getOrdinamento() {
		return ordinamento;
	}

	public void setOrdinamento(Integer ordinamento) {
		this.ordinamento = ordinamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((milestone == null) ? 0 : milestone.hashCode());
		result = prime * result + ((milestoneComponente == null) ? 0 : milestoneComponente.hashCode());
		result = prime * result + ((ordinamento == null) ? 0 : ordinamento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MilestoneMilestone other = (MilestoneMilestone) obj;
		if (milestone == null) {
			if (other.milestone != null)
				return false;
		} else if (!milestone.equals(other.milestone))
			return false;
		if (milestoneComponente == null) {
			if (other.milestoneComponente != null)
				return false;
		} else if (!milestoneComponente.equals(other.milestoneComponente))
			return false;
		if (ordinamento == null) {
			if (other.ordinamento != null)
				return false;
		} else if (!ordinamento.equals(other.ordinamento))
			return false;
		return true;
	}

}
