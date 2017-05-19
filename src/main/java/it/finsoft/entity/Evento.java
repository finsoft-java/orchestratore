package it.finsoft.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EVENTI")
public class Evento implements Serializable {

	private static final long serialVersionUID = 1706249082759274352L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_EVENTO")
	private Long idEvento;

	@ManyToOne(optional = true)
	@JoinColumn(name = "ID_TIPO_EVENTO", referencedColumnName = "ID_TIPO")
	private TipoEvento tipoEvento; // foreign key tabella Tipi_evento

	@ManyToOne(optional = true)
	@JoinColumn(name = "ID_ENTITA", referencedColumnName = "ID_ENTITA")
	private Entita entita; // foreign key tabella entita

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TSTAMP_EVENTO")
	private Date tStampEvento = new Date(); // timestamp per la registrazione

	@Column(name = "TAG")
	private String tag;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "evento")
	private Collection<DettaglioEvento> dettaglioEvento;

	public Evento() {

	}

	public Evento(TipoEvento tipoEvento, Entita entita, Date tStampEvento, String tag) {
		this.tipoEvento = tipoEvento;
		this.entita = entita;
		this.tStampEvento = tStampEvento;
		this.tag = tag;
	}

	public Long getidEvento() {
		return idEvento;
	}

	public void setidEvento(Long idEvento) {
		this.idEvento = idEvento;
	}

	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public Entita getEntita() {
		return entita;
	}

	public void setEntita(Entita entita) {
		this.entita = entita;
	}

	public Date gettStampEvento() {
		return tStampEvento;
	}

	public void settStampEvento(Date tStampEvento) {
		this.tStampEvento = tStampEvento;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Collection<DettaglioEvento> getDettagliEvento() {
		return dettaglioEvento;
	}

	public void setDettagliEvento(Collection<DettaglioEvento> dettaglioEvento) {
		this.dettaglioEvento = dettaglioEvento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEvento == null) ? 0 : idEvento.hashCode());
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
		Evento other = (Evento) obj;
		if (idEvento == null) {
			if (other.idEvento != null)
				return false;
		} else if (!idEvento.equals(other.idEvento))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Eventi [IDevento=" + idEvento + ", tipoEvento=" + tipoEvento + ", entita=" + entita + ", tStampEvento="
				+ tStampEvento + ", tag=" + tag + ", dettagliEvento=" + dettaglioEvento + "]";
	}

}
