package com.vexigo.prediction.datatypes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Imp implements Serializable 
{
	//A unique identifier for this impression within the context of the bid request (typically, starts with 1 and increments.
	private String id="";
	
	//TODO - A Banner object (Section 3.2.3); required if this impression is offered as a banner ad opportunity.
	//Banner banner = null;
	
	//A Video object; required if this impression is offered as a video ad opportunity.
	Video video = null;
	
	//TODO - A Native object ; required if this impression is offered as a native ad opportunity.
	//NativeAd nativead

	//	Name of ad mediation partner, SDK technology, or player responsible for rendering ad (typically video or mobile). 
	// Used by some ad servers to customize ad code by partner. Recommended for video and/or apps.
	private String displaymanager = "";

	//1 = the ad is interstitial or full screen, 0 = not interstitial.
	private int  instl = 0;
	
	//Identifier for specific ad placement or ad tag that was used to initiate the auction. This can be useful for debugging of any issues, or for optimization by the buyer.
	private String tagid  ="";
	
	//Minimum bid for this impression expressed in CPM.
	private BigDecimal bidfloor = new BigDecimal(0.0);
	
	//default “USD”. Currency specified using ISO-4217 alpha codes. This may be different from bid currency returned by bidder if this is allowed by the exchange.
	private String bidfloorcur = "USD"; 
	
	//Flag to indicate if the impression requires secure HTTPS URL creative assets and markup, where 0 = non-secure, 1 = secure.
	//If omitted, the secure state is unknown, but non-secure HTTP support can be assumed
	private int  secure = 0;
	
	//Array of exchange-specific names of supported iframe busters.
	private ArrayList<String> iframebuster = new ArrayList<String>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public String getDisplaymanager() {
		return displaymanager;
	}

	public void setDisplaymanager(String displaymanager) {
		this.displaymanager = displaymanager;
	}

	public int getInstl() {
		return instl;
	}

	public void setInstl(int instl) {
		this.instl = instl;
	}

	public String getTagid() {
		return tagid;
	}

	public void setTagid(String tagid) {
		this.tagid = tagid;
	}
	
	public BigDecimal getBidfloor() {
		return bidfloor;
	}

	public void setBidfloor(BigDecimal bidfloor) {
		this.bidfloor = bidfloor;
	}

	public String getBidfloorcur() {
		return bidfloorcur;
	}

	public void setBidfloorcur(String bidfloorcur) {
		this.bidfloorcur = bidfloorcur;
	}

	public int getSecure() {
		return secure;
	}

	public void setSecure(int secure) {
		this.secure = secure;
	}

	public ArrayList<String> getIframebuster() {
		return iframebuster;
	}

	public void setIframebuster(ArrayList<String> iframebuster) {
		this.iframebuster = iframebuster;
	} 	
	

	

}
