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
	private int sankei;
	private int nikkei;

	public MyEyesEntity() {

	}

	public MyEyesEntity(String username, int google, int twitter, int nhk, int mainichi, int asahi, int yomiuri,
			int sankei, int nikkei) {
		this.username = username;
		this.google = google;
		this.twitter = twitter;
		this.nhk = nhk;
		this.mainichi = mainichi;
		this.asahi = asahi;
		this.yomiuri = yomiuri;
		this.sankei = sankei;
		this.nikkei = nikkei;
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

	public int getSankei() {
		return sankei;
	}

	public void setSankei(int sankei) {
		this.sankei = sankei;
	}

	public int getNikkei() {
		return nikkei;
	}

	public void setNikkei(int nikkei) {
		this.nikkei = nikkei;
	}

}
