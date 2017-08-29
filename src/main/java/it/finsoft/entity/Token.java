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

@Entity
@Table(name = "T_BDOR0_TOKENS")
@XmlRootElement
public class Token implements Serializable {

	private static final long serialVersionUID = -4855044822943907860L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REQ_TIMESTAMP")
	private Date reqTimestamp;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXPIRE_TIMESTAMP")
	private Date expireTimestamp;

	@Column(name = "REQ_USERNAME")
	private String reqUsername;

	@Column(name = "REQ_IP")
	private String reqIP;

	@Column(name = "TOKEN")
	private String token;

	public Token() {
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
		return this.repr().equals(((Token) obj).repr());
	}

	private String repr() {
		return "Token [ID=" + id + ", token=" + token + "]";
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

	public String getReqUsername() {
		return reqUsername;
	}

	public void setReqUsername(String reqUsername) {
		this.reqUsername = reqUsername;
	}

	public String getReqIP() {
		return reqIP;
	}

	public void setReqIP(String reqIP) {
		this.reqIP = reqIP;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getReqTimestamp() {
		return reqTimestamp;
	}

	public void setReqTimestamp(Date reqTimestamp) {
		this.reqTimestamp = reqTimestamp;
	}

	public Date getExpireTimestamp() {
		return expireTimestamp;
	}

	public void setExpireTimestamp(Date expireTimestamp) {
		this.expireTimestamp = expireTimestamp;
	}

}
