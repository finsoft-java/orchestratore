package it.finsoft.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="ENTITA")
public class Entita implements Serializable {
	
	private static final long serialVersionUID = -1890110511455534110L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="ID_ENTITA")
	private Long idEntita;
	
	@Column(name ="ACRONIMO")
	private String acronimo;
	
	@Column(name ="DESCRIZIONE")
	private String descrizione;

	
	//costruttori
	public Entita() {
		
	}

	public Entita(String acronimo, String descrizione) {
		this.acronimo = acronimo;
		this.descrizione = descrizione;
	}

	//getter and setter
	public Long getidEntita() {
		return idEntita;
	}

	public void setidEntita(Long idEntita) {
		this.idEntita = idEntita;
	}

	public String getAcronimo() {
		return acronimo;
	}

	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
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
		result = prime * result + ((idEntita == null) ? 0 : idEntita.hashCode());
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
		Entita other = (Entita) obj;
		if (idEntita == null) {
			if (other.idEntita != null)
				return false;
		} else if (!idEntita.equals(other.idEntita))
			return false;
		return true;
	}

	//tostring
	@Override
	public String toString() {
		return "Entita [ID_entita=" + idEntita + ", acronimo=" + acronimo + ", descrizione=" + descrizione + "]";
	}

		
	

}
