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

import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "CALENDARI_MILESTONE")
public class CalendarioMilestone implements Serializable {

	private static final long serialVersionUID = -3328166212616139600L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CALENDARIO_MILESTONE")
	private Long idCalendarioSemaforo;

	@JoinColumn(name = "ID_AZIONE", referencedColumnName = "ID_AZIONE")
	@ManyToOne(optional = true)
	private Azione azione;

	@JoinColumn(name = "ID_MILESTONE", referencedColumnName = "ID_MILESTONE")
	@ManyToOne(optional = false)
	private Milestone milestone;

	@JoinColumn(name = "ID_CALENDARIO", referencedColumnName = "ID_CALENDARIO")
	@ManyToOne(optional = false)
	private Calendario calendario;

	@Column(name = "TAGS")
	private String tags;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_ORA_PREVISTE")
	private Date dataOraPreviste;

	public CalendarioMilestone() {

	}

	public CalendarioMilestone(Azione azione, Calendario calendario, String tags, Date dataOraPreviste) {
		this.azione = azione;
		this.calendario = calendario;
		this.tags = tags;
		this.dataOraPreviste = dataOraPreviste;
	}

	public Long getIdCalendarioSemaforo() {
		return idCalendarioSemaforo;
	}

	public void setIdCalendarioSemaforo(Long idCalendarioSemaforo) {
		this.idCalendarioSemaforo = idCalendarioSemaforo;
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

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		tags = tags.replaceAll(" ", "").toUpperCase();
		this.tags = tags;
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
		result = prime * result + ((idCalendarioSemaforo == null) ? 0 : idCalendarioSemaforo.hashCode());
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
		if (idCalendarioSemaforo == null) {
			if (other.idCalendarioSemaforo != null)
				return false;
		} else if (!idCalendarioSemaforo.equals(other.idCalendarioSemaforo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CalendarioSemaforo [idCalendarioSemaforo=" + idCalendarioSemaforo + ", semaforo=" + azione
				+ ", calendario=" + calendario + ", tags=" + tags + ", dataOraPreviste="
				+ dataOraPreviste + "]";
	}

}
