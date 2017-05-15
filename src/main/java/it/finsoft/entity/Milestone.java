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
@Table(name ="MILESTONES")
public class Milestone implements Serializable {
	
	private static final long serialVersionUID = 1070315680400921784L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="ID_MILESTONE")
	private Long idMilestone;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_TIPO_EVENTO", referencedColumnName ="ID_TIPO")
	private TipoEvento tipoEvento; //foreign key tabella tipo_evento
	
	@ManyToOne(optional = false)
	@JoinColumn(name ="ID_ENTITA", referencedColumnName ="ID_ENTITA")
	private Entita entita; //foreign key tabella entita

	
	public Milestone() {
		
	}


	public Long getidMilestones() {
		return idMilestone;
	}


	public void setidMilestone(Long idMilestone) {
		this.idMilestone = idMilestone;
	}


	public TipoEvento gettipoEvento() {
		return tipoEvento;
	}


	public void settipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}


	public Entita getEntita() {
		return entita;
	}


	public void setEntita(Entita entita) {
		this.entita = entita;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMilestone == null) ? 0 : idMilestone.hashCode());
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
		Milestone other = (Milestone) obj;
		if (idMilestone == null) {
			if (other.idMilestone != null)
				return false;
		} else if (!idMilestone.equals(other.idMilestone))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Milestones [IDmilestones=" + idMilestone + ", tipoevento=" + tipoEvento + ", entita=" + entita + "]";
	}
	
	
	

	

}
