package it.finsoft.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "SEMAFORI")
@XmlRootElement
public class Semaforo implements Serializable {

	private static final long serialVersionUID = 5334333055740995630L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_SEMAFORO")
	private Long idSemaforo;

	@Basic(optional = false)
	@Column(name = "CODICE")
	private String codice;

	@Column(name = "DESCRIZIONE")
	private String descrizione;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "semaforo")
	@OrderBy("ordinamento")
	private List<SemaforoMilestone> semaforoMilestones = new ArrayList<>();


	/* costruttori */
	public Semaforo() {

	}

	public Semaforo(String codice, String descrizione) {
		this.codice = codice;
		this.descrizione = descrizione;

	}
	
	public Semaforo(Long idSemaforo, String codice, String descrizione,
			List<SemaforoMilestone> semaforoMilestones) {
		this.idSemaforo = idSemaforo;
		this.codice = codice;
		this.descrizione = descrizione;
		this.semaforoMilestones = semaforoMilestones;
	}

	public Long getIdSemaforo() {
		return idSemaforo;
	}

	public void setIdSemaforo(Long idSemaforo) {
		this.idSemaforo = idSemaforo;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@XmlTransient
	public List<SemaforoMilestone> getSemaforoMilestones() {
		return semaforoMilestones;
	}

	public void setSemaforoMilestones(List<SemaforoMilestone> semaforoMilestones) {
		this.semaforoMilestones = semaforoMilestones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codice == null) ? 0 : codice.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((idSemaforo == null) ? 0 : idSemaforo.hashCode());

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
		Semaforo other = (Semaforo) obj;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (idSemaforo == null) {
			if (other.idSemaforo != null)
				return false;
		} else if (!idSemaforo.equals(other.idSemaforo))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "Semaforo [idSemaforo=" + idSemaforo + ", codice=" + codice + ", descrizione=" + descrizione + "]";
	}

}
