package it.finsoft.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "MILESTONES")
@XmlRootElement
public class Milestone implements Serializable {

	private static final long serialVersionUID = 1070315680400921784L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_MILESTONE")
	private Long idMilestone;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_TIPO_EVENTO", referencedColumnName = "ID_TIPO_EVENTO")
	private TipoEvento tipoEvento; // foreign key tabella tipo_evento

	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_ENTITA", referencedColumnName = "ID_ENTITA")
	private Entita entita; // foreign key tabella entita

	@Column(name = "DESCRIZIONE", unique=true)
	private String descrizione;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "milestone")
	@OrderBy("ordinamento")
	private List<MilestoneMilestone> milestoneMilestone = new ArrayList<>();
	
	public Milestone() {

	}

	public Milestone(Long idMilestone, TipoEvento tipoEvento, Entita entita, String descrizione) {
		this.idMilestone = idMilestone;
		this.tipoEvento = tipoEvento;
		this.entita = entita;
		this.descrizione = descrizione;
	}

	public Long getIdMilestone() {
		return idMilestone;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setIdMilestone(Long idMilestone) {
		this.idMilestone = idMilestone;
	}

	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public Entita getEntita() {
		return entita;
	}

	public void setEntita(Entita entita) {
		this.entita = entita;
	}
	
	@XmlTransient
	public List<MilestoneMilestone> getMilestoneMilestone() {
		return milestoneMilestone;
	}

	public void setMilestoneMilestone(List<MilestoneMilestone> milestoneMilestone) {
		this.milestoneMilestone = milestoneMilestone;
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
		return "Milestone [idMilestone=" + idMilestone + ", tipoEvento=" + tipoEvento + ", entita=" + entita
				+ ", descrizione=" + descrizione + "]";
	}

}
