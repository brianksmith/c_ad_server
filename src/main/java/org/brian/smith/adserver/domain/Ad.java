package org.brian.smith.adserver.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Ad {

	private String partner_id;
	private String ad_content;
	private int duration;
	@JsonIgnore
	private Date created;
	@JsonIgnore
	private Date expires;
	
	public String getPartner_id() {
		return partner_id;
	}
	
	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}
	
	public String getAd_content() {
		return ad_content;
	}
	
	public void setAd_content(String ad_content) {
		this.ad_content = ad_content;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getExpires() {
		return expires;
	}

	public void setExpires(Date expires) {
		this.expires = expires;
	}
}
