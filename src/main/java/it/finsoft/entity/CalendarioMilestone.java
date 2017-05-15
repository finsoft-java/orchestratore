package it.finsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name ="CALENDARI_MILESTONES")
public class CalendarioMilestone implements Serializable {
	
	private static final long serialVersionUID = -3328166212616139600L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="ID_CALENDARIO_MILESTONE")
	private Long idCalendarioMilestone;
	
	@JoinColumn(name ="ID_CALENDARIO", referencedColumnName ="ID_CALENDARIO")
	@ManyToOne(optional = false)
	private Calendario calendario;

	@JoinColumn(name ="ID_MILESTONE", referencedColumnName ="ID_MILESTONE")
	@ManyToOne(optional = false)
	private Milestone milestone;
	
	@Column(name ="TAG")
	private String tag;
	
	@Column(name = "DATA_ORA_PREVISTE")
	private Date dataOraPreviste;

	public CalendarioMilestone() {
		
	}

	public CalendarioMilestone(Calendario calendario, Milestone milestone, String tag, Date dataOraPreviste) {
		this.calendario = calendario;
		this.milestone = milestone;
		this.tag = tag;
		this.dataOraPreviste = dataOraPreviste;
	}

	public Long getIdCalendarioMilestone() {
		return idCalendarioMilestone;
	}

	public void setIdCalendarioMilestone(Long idCalendarioMilestone) {
		this.idCalendarioMilestone = idCalendarioMilestone;
	}

	public Calendario getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}

	public Milestone getMilestone() {
		return milestone;
	}

	public void setMilestone(Milestone milestone) {
		this.milestone = milestone;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Date getDataOraPreviste() {
		return dataOraPreviste;
	}

	public void setDataOraPreviste(Date dataOraPreviste) {
		this.dataOraPreviste = dataOraPreviste;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCalendarioMilestone == null) ? 0 : idCalendarioMilestone.hashCode());
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
		CalendarioMilestone other = (CalendarioMilestone) obj;
		if (idCalendarioMilestone == null) {
			if (other.idCalendarioMilestone != null)
				return false;
		} else if (!idCalendarioMilestone.equals(other.idCalendarioMilestone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CalendarioMilestone [idCalendarioMilestone=" + idCalendarioMilestone + ", calendario=" + calendario
				+ ", milestone=" + milestone + ", tag=" + tag + ", dataOraPreviste=" + dataOraPreviste + "]";
	}
	
	
	
	
	
}
