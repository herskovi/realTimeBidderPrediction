package com.vexigo.prediction.datatypes;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "Bid")

public class BidRequest implements Serializable 
{
	@Id
	private String id = "";
	private Site site = null;
	
	private String timezoneOffset ="";
	private String userAgent="";
	private String timestamp = ""; 
	private Device device= null; 
	private String sellerNetworkId ="";
	private  String url = "";
	private  String detectedLanguage = "";
	private String geoCriteriaId="";
	private Video video = null;
	private int minimumCpmMicros = 5;
	private User user = null;

	/**
	 * 
	 * @param id
	 * @param url
	 */
	public BidRequest(String id, String url) {
		super();
		this.id = id;
		this.url = url;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}
	public String getSellerNetworkId() {
		return sellerNetworkId;
	}
	public void setSellerNetworkId(String sellerNetworkId) {
		this.sellerNetworkId = sellerNetworkId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDetectedLanguage() {
		return detectedLanguage;
	}
	public void setDetectedLanguage(String detectedLanguage) {
		this.detectedLanguage = detectedLanguage;
	}
	public String getGeoCriteriaId() {
		return geoCriteriaId;
	}
	public void setGeoCriteriaId(String geoCriteriaId) {
		this.geoCriteriaId = geoCriteriaId;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	
	public String getTimezoneOffset() {
		return timezoneOffset;
	}
	public void setTimezoneOffset(String timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}
	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public int getMinimumCpmMicros() {
		return minimumCpmMicros;
	}
	public void setMinimumCpmMicros(int minimumCpmMicros) {
		this.minimumCpmMicros = minimumCpmMicros;
	}
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}

