package it.finsoft.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Username a password per la token-based authentication delle engine
 * 
 * @author Luca Vercelli
 *
 */
@Entity
@Table(name = "T_BDOR0_ENGINES")
@XmlRootElement
public class Engine implements Serializable {

	private static final long serialVersionUID = -4855044822943907860L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME", unique = true)
	private String name;

	@Column(name = "ENCRYPTED_PWD")
	private String encryptedPassword;

	@Temporal(TemporalType.DATE)
	@Column(name = "FINE_VALIDITA")
	private Date fineValidita;

	public Engine() {
	}

	@Override
	public int hashCode() {
		return repr().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return this.repr().equals(((Engine) obj).repr());
	}

	private String repr() {
		return "Engine [ID=" + id + ", name=" + name + "]";
	}

	@Override
	public String toString() {
		return repr();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public Date getFineValidita() {
		return fineValidita;
	}

	public void setFineValidita(Date fineValidita) {
		this.fineValidita = fineValidita;
	}

}
