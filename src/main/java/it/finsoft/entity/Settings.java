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

	private Long id = 1L;
	private String wsPath = "http://localhost:7001/bdor0ws";

	@Id
	@Column(name = "ID", unique = true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		if (id.longValue() != 1L)
			throw new IllegalArgumentException("Cannot create another instance of this class");
		this.id = id;
	}

	@Column(name = "WS_PATH")
	public String getWsPath() {
		return wsPath;
	}

	public void setWsPath(String wsPath) {
		this.wsPath = wsPath;
	}

}
