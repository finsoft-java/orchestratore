package it.finsoft.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * You can only have 1 row in this table.
 * 
 * @author u0i8226
 * 
 */
@Entity
@Table(name = "T_BDOR0_SETTINGS")
@XmlRootElement
public class Settings implements Serializable {

	private static final long serialVersionUID = 3945515390166304783L;

	@Id
	@Column(name = "ID")
	private Long id = 1L;

	@Column(name = "WS_PATH")
	private String wsPath = "http://localhost:7001/bdor0ws";

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWsPath() {
		return wsPath;
	}

	public void setWsPath(String wsPath) {
		this.wsPath = wsPath;
	}

}
