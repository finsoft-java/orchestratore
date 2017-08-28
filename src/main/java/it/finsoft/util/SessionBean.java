package it.finsoft.util;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Named;

import javax.enterprise.context.SessionScoped;

@Named("sessionBean")
@SessionScoped
public class SessionBean implements Serializable {

	private static final long serialVersionUID = -2545698595043737577L;

	private String wsPath;
	private String language;

	private String profileId;
	private String userName;
	private String svcUrl;
	private boolean errorsConnectingSWA = false;
	private Set<String> profili = new HashSet<String>();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWsPath() {
		return wsPath;
	}

	public void setWsPath(String wsPath) {
		this.wsPath = wsPath;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getSvcUrl() {
		return svcUrl;
	}

	public void setSvcUrl(String svcUrl) {
		this.svcUrl = svcUrl;
	}

	public Set<String> getProfili() {
		return profili;
	}

	public void setProfili(Set<String> profili) {
		this.profili = profili;
	}

	public boolean getErrorsConnectingSWA() {
		return errorsConnectingSWA;
	}

	public void setErrorsConnectingSWA(boolean errorConnectionSWA) {
		this.errorsConnectingSWA = errorConnectionSWA;
	}
}