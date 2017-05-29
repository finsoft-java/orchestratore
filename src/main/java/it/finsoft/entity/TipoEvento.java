package it.finsoft.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TIPI_EVENTO")
public class TipoEvento implements Serializable {

	private static final long serialVersionUID = 314854374810280841L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TIPO_EVENTO")
	private Long idTipoEvento;

	@Column(name = "CODICE", unique = true)
	private String codice;

	@Column(name = "DESCRIZIONE")
	private String descrizione;

	/* costruttori */
	public TipoEvento() {

	}

	public TipoEvento(String codice, String descrizione) {
		this.codice = codice;
		this.descrizione = descrizione;
	}

	/* getter and setter */
	public Long getidTipoEvento() {
		return idTipoEvento;
	}

	public void setidTipoEvento(Long idTipoEvento) {
		this.idTipoEvento = idTipoEvento;
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

	/* to string */
	@Override
	public String toString() {
		return "TipoEvento [idTipo=" + idTipoEvento + ", codice=" + codice + ", descrizione=" + descrizione + "]";
	}

}
