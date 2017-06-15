package it.finsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "CALENDARI_MILESTONE")
@XmlRootElement
public class CalendarioMilestone implements Serializable {

	private static final long serialVersionUID = -3328166212616139600L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CALENDARIO_MILESTONE")
	private Long idCalendarioMilestone;
	
	@JoinColumn(name = "ID_AZIONE", referencedColumnName = "ID_AZIONE")
	@ManyToOne(optional = true)
	private Azione azione;

	@JoinColumn(name = "ID_MILESTONE", referencedColumnName = "ID_MILESTONE")
	@ManyToOne(optional = false)
	private Milestone milestone;

	@JoinColumn(name = "ID_CALENDARIO", referencedColumnName = "ID_CALENDARIO")
	@ManyToOne(optional = false)
	private Calendario calendario;

	@Column(name = "TAG")
	private String tag;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_ORA_PREVISTE")
	private Date dataOraPreviste;

	public CalendarioMilestone() {

	}

	public CalendarioMilestone(Azione azione, Milestone milestone, Calendario calendario, String tag,
			Date dataOraPreviste) {
		this.azione = azione;
		this.milestone = milestone;
		this.calendario = calendario;
		this.tag = tag;
		this.dataOraPreviste = dataOraPreviste;
	}

	public Long getIdCalendarioMilestone() {
		return idCalendarioMilestone;
	}

	public void setIdCalendarioMilestone(Long idCalendarioMilestone) {
		this.idCalendarioMilestone = idCalendarioMilestone;
	}
	
	public Azione getAzione() {
		return azione;
	}

	public void setAzione(Azione azione) {
		this.azione = azione;
	}

	public Milestone getMilestone() {
		return milestone;
	}

	public void setMilestone(Milestone milestone) {
		this.milestone = milestone;
	}

	public Calendario getCalendario() {
		return calendario;
	}

	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		tag = tag.replaceAll(",", "");
		tag = tag.replaceAll(" ", "").toUpperCase();
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
		return "CalendarioMilestone [idCalendarioMilestone=" + idCalendarioMilestone + ", azione=" + azione
				+ ", milestone=" + milestone + ", calendario=" + calendario + ", tag=" + tag + ", dataOraPreviste="
				+ dataOraPreviste + "]";
	}

}
