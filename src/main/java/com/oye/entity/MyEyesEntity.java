package com.oye.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "myeyes")
public class MyEyesEntity {

	@Id
	private String username;
	private int google;
	private int twitter;
	private int nhk;
	private int mainichi;
	private int asahi;
	private int yomiuri;

	public MyEyesEntity() {

	}

	public MyEyesEntity(String username, int google, int twitter, int nhk, int mainichi, int asahi, int yomiuri) {
		this.username = username;
		this.google = google;
		this.twitter = twitter;
		this.nhk = nhk;
		this.mainichi = mainichi;
		this.asahi = asahi;
		this.yomiuri = yomiuri;
	}

	public String getUsername() {
		return username;
	}

	public int getGoogle() {
		return google;
	}

	public int getTwitter() {
		return twitter;
	}

	public int getNhk() {
		return nhk;
	}

	public int getMainichi() {
		return mainichi;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setGoogle(int google) {
		this.google = google;
	}

	public void setTwitter(int twitter) {
		this.twitter = twitter;
	}

	public void setNhk(int nhk) {
		this.nhk = nhk;
	}

	public void setMainichi(int mainichi) {
		this.mainichi = mainichi;
	}

	public int getAsahi() {
		return asahi;
	}

	public void setAsahi(int asahi) {
		this.asahi = asahi;
	}

	public int getYomiuri() {
		return yomiuri;
	}

	public void setYomiuri(int yomiuri) {
		this.yomiuri = yomiuri;
	}

}
