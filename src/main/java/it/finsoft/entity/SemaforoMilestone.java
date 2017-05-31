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
	private Azione azione;

	@Column(name = "ORDINAMENTO")
	private Integer ordinamento;

	public SemaforoMilestone() {
	}

	public SemaforoMilestone(long idSemaforoMilestone, Milestone milestone, Azione azione, int ordinamento) {
		this.idSemaforoMilestone = idSemaforoMilestone;
		this.milestone = milestone;
		this.azione = azione;
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

	public Azione getSemaforo() {
		return azione;
	}

	public void setSemaforo(Azione azione) {
		this.azione = azione;
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
		result = prime * result + ((azione == null) ? 0 : azione.hashCode());
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
		if (azione == null) {
			if (other.azione != null)
				return false;
		} else if (!azione.equals(other.azione))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SemaforoMilestone [idSemaforoMilestone=" + idSemaforoMilestone + ", milestone=" + milestone
				+ ", semaforo=" + azione + ", ordinamento=" + ordinamento + "]";
	}

}
