package it.finsoft.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "MILESTONE_MILESTONES")
@XmlRootElement
public class MilestoneMilestone implements Serializable {

	private static final long serialVersionUID = 1501385652586677633L;

	@Id
	@Column(name = "ID_MILESTONE_MILESTONE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMilestoneMilestone;

	@ManyToOne
	@JoinColumn(name = "ID_MILESTONE", referencedColumnName = "ID_MILESTONE")
	private Milestone milestone;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_MILESTONE_CHILD", referencedColumnName = "ID_MILESTONE")
	private Milestone milestoneChild;

	@Column(name = "ORDINAMENTO")
	private Integer ordinamento;

	public MilestoneMilestone() {
	}

	public MilestoneMilestone(long id, Milestone milestone, Milestone milestoneChild, int ordinamento) {
		this.idMilestoneMilestone = id;
		this.milestone = milestone;
		this.milestoneChild = milestoneChild;
		this.ordinamento = ordinamento;
	}

	public Long getIdMilestoneMilestone() {
		return idMilestoneMilestone;
	}

	public void setIdSemaforoMilestone(long idSemaforoMilestone) {
		this.idMilestoneMilestone = idSemaforoMilestone;
	}

	public Milestone getMilestone() {
		return milestone;
	}

	public void setMilestone(Milestone milestone) {
		this.milestone = milestone;
	}

	public Milestone getMilestoneChild() {
		return milestoneChild;
	}

	public void setMilestoneChild(Milestone milestoneChild) {
		this.milestoneChild = milestoneChild;
	}

	public Integer getOrdinamento() {
		return ordinamento;
	}

	public void setOrdinamento(int ordinamento) {
		this.ordinamento = ordinamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idMilestoneMilestone ^ (idMilestoneMilestone >>> 32));
		result = prime * result + ((milestone == null) ? 0 : milestone.hashCode());
		result = prime * result + ordinamento;
		result = prime * result + ((milestoneChild == null) ? 0 : milestoneChild.hashCode());
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
		if (idMilestoneMilestone != other.idMilestoneMilestone)
			return false;
		if (milestone == null) {
			if (other.milestone != null)
				return false;
		} else if (!milestone.equals(other.milestone))
			return false;
		if (ordinamento != other.ordinamento)
			return false;
		if (milestoneChild == null) {
			if (other.milestoneChild != null)
				return false;
		} else if (!milestoneChild.equals(other.milestoneChild))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SemaforoMilestone [idMilestoneMilestone=" + idMilestoneMilestone + ", milestone=" + milestone
				+ ", semaforo=" + milestoneChild + ", ordinamento=" + ordinamento + "]";
	}

}
