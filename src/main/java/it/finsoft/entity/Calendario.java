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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "CALENDARI")
@XmlRootElement
public class Calendario implements Serializable {

	private static final long serialVersionUID = 6714115891652097006L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CALENDARIO")
	private Long idCalendario;

	@Column(name = "DESCRIZIONE")
	private String descrizione;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "calendario")
	private List<CalendarioMilestone> calendarioMilestone = new ArrayList<>();

	public Calendario() {

	}

	public Calendario(String descrizione) {
		this.descrizione = descrizione;
	}

	@XmlTransient
	public List<CalendarioMilestone> getCalendarioMilestone() {
		return calendarioMilestone;
	}

	public void setCalendarioMilestone(List<CalendarioMilestone> calendarioMilestone) {
		this.calendarioMilestone = calendarioMilestone;
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
