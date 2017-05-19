package it.finsoft.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="CALENDARI")
public class Calendario implements Serializable {
	
	private static final long serialVersionUID = 6714115891652097006L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="ID_CALENDARIO")
	private Long idCalendario;
	
	@Column(name ="DESCRIZIONE")
	private String descrizione;

	public Calendario() {
		
	}

	public Calendario(String descrizione) {
		this.descrizione = descrizione;
	}

	public Long getIdCalendario() {
		return idCalendario;
	}

	public void setIdCalendario(Long idCalendario) {
		this.idCalendario = idCalendario;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCalendario == null) ? 0 : idCalendario.hashCode());
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
		Calendario other = (Calendario) obj;
		if (idCalendario == null) {
			if (other.idCalendario != null)
				return false;
		} else if (!idCalendario.equals(other.idCalendario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Calendario [idCalendario=" + idCalendario + ", descrizione=" + descrizione + "]";
	}
	

}
