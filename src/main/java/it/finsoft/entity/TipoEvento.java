package it.finsoft.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="TIPI_EVENTO")
public class TipoEvento implements Serializable {
	
	private static final long serialVersionUID = 314854374810280841L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="ID_TIPO")
	private Long idTipo;
	
	@Column(name ="DESCRIZIONE")
	private String descrizione;

	//costruttori
	public TipoEvento() {
		
	}

	public TipoEvento(String descrizione) {
		this.descrizione = descrizione;
	}

	public Long getidTipo() {
		return idTipo;
	}

	public void setidTipo(Long idTipo) {
		this.idTipo = idTipo;
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
		result = prime * result + ((idTipo == null) ? 0 : idTipo.hashCode());
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
		TipoEvento other = (TipoEvento) obj;
		if (idTipo == null) {
			if (other.idTipo != null)
				return false;
		} else if (!idTipo.equals(other.idTipo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipiEvento [IDtipo=" + idTipo + ", descrizione=" + descrizione + "]";
	}

	
	
}
