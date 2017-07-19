package it.finsoft.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "T_BDOR0_AZIONI")
@XmlRootElement
public class Azione implements Serializable {

	private static final long serialVersionUID = 5334333055740995630L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_AZIONE")
	private Long idAzione;

	@Column(name = "DESCRIZIONE")
	private String descrizione;
	
	@Basic(optional = false)
	@Column(name = "TIPO")
	private String tipo;
	
	@Basic(optional = false)
	@Column(name = "URL")
	private String url;
	
	@Basic(optional = false)
	@Column(name = "FILENAME")
	private String filename;
	
	/* costruttori */

	public Azione() {
		
	}

	public Azione(String descrizione, String tipo, String url, String filename) {
		this.descrizione = descrizione;
		this.tipo = tipo;
		this.url = url;
		this.filename = filename;
	}

	public Long getIdAzione() {
		return idAzione;
	}

	public void setIdAzione(Long idAzione) {
		this.idAzione = idAzione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		descrizione = descrizione.toUpperCase();
		this.descrizione = descrizione;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "Azione [idAzione=" + idAzione + ", descrizione=" + descrizione + ", tipo=" + tipo + ", url=" + url
				+ ", filename=" + filename + "]";
	}
	
}
