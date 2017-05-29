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
@Table(name = "CALENDARI_SEMAFORI")
public class CalendarioSemaforo implements Serializable {

	private static final long serialVersionUID = -3328166212616139600L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CALENDARIO_SEMAFORO")
	private Long idCalendarioSemaforo;

	@JoinColumn(name = "ID_SEMAFORO", referencedColumnName = "ID_SEMAFORO")
	@ManyToOne(optional = false)
	private Semaforo semaforo;

	@JoinColumn(name = "ID_CALENDARIO", referencedColumnName = "ID_CALENDARIO")
	@ManyToOne(optional = false)
	private Calendario calendario;

	@Column(name = "TAGS")
	private String tags;

	@Column(name = "DATA_ORA_PREVISTE")
	private Date dataOraPreviste;

	public CalendarioSemaforo() {

	}

	public CalendarioSemaforo(Semaforo semaforo, Calendario calendario, String tags, Date dataOraPreviste) {
		this.semaforo = semaforo;
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

	public Semaforo getSemaforo() {
		return semaforo;
	}

	public void setSemaforo(Semaforo semaforo) {
		this.semaforo = semaforo;
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

		// FIXME split(',') normalizziamo i tag poi lo ricostruiamo

		tags = tags.replaceAll(" ", "").toUpperCase();

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
		CalendarioSemaforo other = (CalendarioSemaforo) obj;
		if (idCalendarioSemaforo == null) {
			if (other.idCalendarioSemaforo != null)
				return false;
		} else if (!idCalendarioSemaforo.equals(other.idCalendarioSemaforo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CalendarioSemaforo [idCalendarioSemaforo=" + idCalendarioSemaforo + ", semaforo=" + semaforo
				+ ", calendario=" + calendario + ", tags=" + tags + ", dataOraPreviste="
				+ dataOraPreviste + "]";
	}

}
