package it.finsoft.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "MILESTONES")
@XmlRootElement
public class Milestone implements Serializable {

	private static final long serialVersionUID = 1070315680400921784L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_MILESTONE")
	private Long idMilestone;

	@ManyToOne(optional = true)
	@JoinColumn(name = "ID_TIPO_EVENTO", referencedColumnName = "ID_TIPO_EVENTO")
	private TipoEvento tipoEvento; // foreign key tabella tipo_evento

	@ManyToOne(optional = true)
	@JoinColumn(name = "ID_ENTITA", referencedColumnName = "ID_ENTITA")
	private Entita entita; // foreign key tabella entita
	
	@Column(name = "CODICE", unique = true)
	private String codice;

	@Column(name = "DESCRIZIONE", unique = false, nullable = true)
	private String descrizione;
	
	@Column(name = "DESCRIZIONE_TAG", unique = false, nullable = true)
	private String descrizioneTag;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "milestone")
	@OrderBy("ordinamento") // possibile soluzione
	private List<MilestoneMilestone> milestoneMilestone = new ArrayList<>();

	// TODO Milestone su Azione
	// o ManyToOne su AZIONI.ID_AZIONE???

	@ManyToOne
	@JoinColumn(name = "ID_AZIONE", referencedColumnName = "ID_AZIONE", nullable = true)
	private Azione azione;

	public Milestone() {

	}

	public Milestone(Long idMilestone, TipoEvento tipoEvento, Entita entita, String codice, String descrizione, 
			String descrizioneTag, Azione azione) {
		this.idMilestone = idMilestone;
		this.tipoEvento = tipoEvento;
		this.entita = entita;
		this.codice = codice;
		this.descrizione = descrizione;
		this.descrizioneTag = descrizioneTag;
		this.azione = azione;
	}

	public Long getIdMilestone() {
		return idMilestone;
	}

	public void setIdMilestone(Long idMilestone) {
		this.idMilestone = idMilestone;
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
		descrizione = descrizione.toUpperCase();
		this.descrizione = descrizione;
	}
	
	public String getDescrizioneTag() {
		return descrizioneTag;
	}

	public void setDescrizioneTag(String descrizioneTag) {
		descrizioneTag = descrizioneTag.toUpperCase();
		this.descrizioneTag = descrizioneTag;
	}

	@XmlTransient
	public List<MilestoneMilestone> getMilestoneMilestone() {
		return milestoneMilestone;
	}

	public void setMilestoneMilestone(List<MilestoneMilestone> milestoneMilestone) {
		this.milestoneMilestone = milestoneMilestone;
	}

	public Azione getAzione() {
		return azione;
	}

	public void setAzione(Azione azione) {
		this.azione = azione;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((azione == null) ? 0 : azione.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((entita == null) ? 0 : entita.hashCode());
		result = prime * result + ((idMilestone == null) ? 0 : idMilestone.hashCode());
		result = prime * result + ((milestoneMilestone == null) ? 0 : milestoneMilestone.hashCode());
		result = prime * result + ((tipoEvento == null) ? 0 : tipoEvento.hashCode());
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
		Milestone other = (Milestone) obj;
		if (azione == null) {
			if (other.azione != null)
				return false;
		} else if (!azione.equals(other.azione))
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (entita == null) {
			if (other.entita != null)
				return false;
		} else if (!entita.equals(other.entita))
			return false;
		if (idMilestone == null) {
			if (other.idMilestone != null)
				return false;
		} else if (!idMilestone.equals(other.idMilestone))
			return false;
		if (milestoneMilestone == null) {
			if (other.milestoneMilestone != null)
				return false;
		} else if (!milestoneMilestone.equals(other.milestoneMilestone))
			return false;
		if (tipoEvento == null) {
			if (other.tipoEvento != null)
				return false;
		} else if (!tipoEvento.equals(other.tipoEvento))
			return false;
		return true;
	}

}