package it.finsoft.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name ="SEMAFORI")
public class Semaforo implements Serializable {
	
	private static final long serialVersionUID = 5334333055740995630L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="ID_SEMAFORO")
	private Long idSemaforo;
	
	@Column(name ="CODICE")
	private String codice;
	
	@Column(name ="DESCRIZIONE")
	private String descrizione;
	
	@ManyToMany
	@JoinTable(
			name ="SEMAFORI_MILESTONES",
			joinColumns=@JoinColumn(name="ID_SEMAFORO", referencedColumnName="ID_SEMAFORO"),
			inverseJoinColumns=@JoinColumn(name="ID_MILESTONE", referencedColumnName="ID_MILESTONE")
			)
	private Collection<Milestone>processiMilestones = new ArrayList<>();

	/*costruttori*/
	public Semaforo() {
		
	}	

	public Semaforo(String codice, String descrizione, Collection<Milestone> processiMilestones) {
		this.codice = codice;
		this.descrizione = descrizione;
		this.processiMilestones = processiMilestones;
	}

	/*getter and setter*/
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
	public Collection<Milestone> getProcessiMilestones() {
		return processiMilestones;
	}

	public void setProcessiMilestones(Collection<Milestone> processiMilestones) {
		this.processiMilestones = processiMilestones;
	}

	/*to string*/
	@Override
	public String toString() {
		return "Semaforo [idSemaforo=" + idSemaforo + ", codice=" + codice + ", descrizione=" + descrizione
				+ ", processiMilestones=" + processiMilestones + "]";
	}


}
