package it.finsoft.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="DETTAGLI_EVENTO")
public class DettaglioEvento implements Serializable {
	
	private static final long serialVersionUID = 3310069293232292518L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name ="ID_DETTAGLIO")
	private Long idDettaglioEvento;
			
	@Column(name ="CHIAVE")
	private String chiave;
	
	@Column(name ="VALORE")
	private String valore;
	
	@ManyToOne(optional=false)
	@JoinColumn(name ="ID_EVENTO", referencedColumnName ="ID_EVENTO")
	private Evento evento; //foreign key with tabella Eventi

	public DettaglioEvento() {
		
	}

	public DettaglioEvento(String chiave, String valore, Evento evento) {
		this.chiave = chiave;
		this.valore = valore;
		this.evento = evento;
	}

	public Long getidDettaglioEvento() {
		return idDettaglioEvento;
	}

	public void setidDettaglioEvento(Long idDettaglioEvento) {
		this.idDettaglioEvento = idDettaglioEvento;
	}

	public String getChiave() {
		return chiave;
	}

	public void setChiave(String chiave) {
		this.chiave = chiave;
	}

	public String getValore() {
		return valore;
	}

	public void setValore(String valore) {
		this.valore = valore;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idDettaglioEvento == null) ? 0 : idDettaglioEvento.hashCode());
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
		DettaglioEvento other = (DettaglioEvento) obj;
		if (idDettaglioEvento == null) {
			if (other.idDettaglioEvento != null)
				return false;
		} else if (!idDettaglioEvento.equals(other.idDettaglioEvento))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DettagliEvento [IDdettaglioEvento=" + idDettaglioEvento + ", chiave=" + chiave + ", valore=" + valore
				+ ", evento=" + evento + "]";
	}
	
	
	

	
	
	
	


}
