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

@Entity
@Table(name = "SEMAFORI_MILESTONES")
public class SemaforoMilestone implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1501385652586677633L;

	@Id
	@Column(name = "ID_SEMAFORO_MILESTONE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSemaforoMilestone;

	@ManyToOne
	@JoinColumn(name = "ID_MILESTONE", referencedColumnName = "ID_MILESTONE")
	private Milestone milestone;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_SEMAFORO", referencedColumnName = "ID_SEMAFORO")
	private Semaforo semaforo;
	
	@Column(name="ORDINAMENTO")
	private Integer ordinamento;

	public SemaforoMilestone() {
	}

	public SemaforoMilestone(long idSemaforoMilestone, Milestone milestone, Semaforo semaforo, int ordinamento) {
		this.idSemaforoMilestone = idSemaforoMilestone;
		this.milestone = milestone;
		this.semaforo = semaforo;
		this.ordinamento = ordinamento;
	}

	public Long getIdSemaforoMilestone() {
		return idSemaforoMilestone;
	}

	public void setIdSemaforoMilestone(long idSemaforoMilestone) {
		this.idSemaforoMilestone = idSemaforoMilestone;
	}

	public Milestone getMilestone() {
		return milestone;
	}

	public void setMilestone(Milestone milestone) {
		this.milestone = milestone;
	}

	public Semaforo getSemaforo() {
		return semaforo;
	}

	public void setSemaforo(Semaforo semaforo) {
		this.semaforo = semaforo;
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
		result = prime * result + (int) (idSemaforoMilestone ^ (idSemaforoMilestone >>> 32));
		result = prime * result + ((milestone == null) ? 0 : milestone.hashCode());
		result = prime * result + ordinamento;
		result = prime * result + ((semaforo == null) ? 0 : semaforo.hashCode());
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
		SemaforoMilestone other = (SemaforoMilestone) obj;
		if (idSemaforoMilestone != other.idSemaforoMilestone)
			return false;
		if (milestone == null) {
			if (other.milestone != null)
				return false;
		} else if (!milestone.equals(other.milestone))
			return false;
		if (ordinamento != other.ordinamento)
			return false;
		if (semaforo == null) {
			if (other.semaforo != null)
				return false;
		} else if (!semaforo.equals(other.semaforo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SemaforoMilestone [idSemaforoMilestone=" + idSemaforoMilestone + ", milestone=" + milestone
				+ ", semaforo=" + semaforo + ", ordinamento=" + ordinamento + "]";
	}
	
	
	
}
