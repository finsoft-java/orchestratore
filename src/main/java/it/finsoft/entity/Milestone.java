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

	@Column(name = "DESCRIZIONE", unique = true)
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

	public Milestone(Long idMilestone, TipoEvento tipoEvento, Entita entita, String descrizione, String descrizioneTag, Azione azione) {
		this.idMilestone = idMilestone;
		this.tipoEvento = tipoEvento;
		this.entita = entita;
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

}