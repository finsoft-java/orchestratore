package it.finsoft.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name ="SEMAFORI")
public class Semaforo implements Serializable {
	
	private static final long serialVersionUID = 5334333055740995630L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="ID_SEMAFORO")
	private Long idSemaforo;
	
	@Column(name ="DESCRIZIONE")
	private String descrizione;
	
	@ManyToMany
	@JoinTable(
			name ="SEMAFORI_MILESTONES",
			joinColumns=@JoinColumn(name="ID_SEMAFORO", referencedColumnName="ID_SEMAFORO"),
			inverseJoinColumns=@JoinColumn(name="ID_MILESTONE", referencedColumnName="ID_MILESTONE")
			)
	private Collection<Milestone>processiMilestones;

	//costruttori
	public Semaforo() {
		
	}

	public Semaforo(String descrizione) {
		this.descrizione = descrizione;
	}

	//getter and setter
	public Long getidSemaforo() {
		return idSemaforo;
	}

	public void setidSemaforo(Long idSemaforo) {
		this.idSemaforo = idSemaforo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	//hashcode and equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idSemaforo == null) ? 0 : idSemaforo.hashCode());
		return result;
	}

	//tostring
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Semaforo other = (Semaforo) obj;
		if (idSemaforo == null) {
			if (other.idSemaforo != null)
				return false;
		} else if (!idSemaforo.equals(other.idSemaforo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Semaforo [ID_semaforo=" + idSemaforo + ", descrizione=" + descrizione + "]";
	}
	
}
