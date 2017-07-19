package it.finsoft.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "T_BDOR0_ENTITA")
@XmlRootElement
public class Entita implements Serializable {

	private static final long serialVersionUID = -1890110511455534110L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ENTITA")
	private Long idEntita;

	@Column(name = "CODICE", length = 20, unique = true)
	private String codice;

	@Column(name = "ACRONIMO")
	private String acronimo;

	@Column(name = "DESCRIZIONE")
	private String descrizione;

	/* costruttori */
	public Entita() {

	}

	public Entita(String codice, String acronimo, String descrizione) {
		this.codice = codice;
		this.acronimo = acronimo;
		this.descrizione = descrizione;
	}

	/* getter and setter */
	public Long getIdEntita() {
		return idEntita;
	}

	public void setIdEntita(Long idEntita) {
		this.idEntita = idEntita;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		codice = codice.toUpperCase();
		this.codice = codice;
	}

	public String getAcronimo() {
		return acronimo;
	}

	public void setAcronimo(String acronimo) {
		acronimo = acronimo.toUpperCase();
		this.acronimo = acronimo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		descrizione = descrizione.toUpperCase();
		this.descrizione = descrizione;
	}

	/* to string */
	@Override
	public String toString() {
		return "Entita [idEntita=" + idEntita + ", codice=" + codice + ", acronimo=" + acronimo + ", descrizione="
				+ descrizione + "]";
	}

}
