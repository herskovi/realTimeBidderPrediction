package com.vexigo.prediction.datatypes;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "Bid")

public class BidResponse implements Serializable 
{
	private String protocolVersion = "";
	private String htmlSnippet = "";
	private String clickThroughUrl ="";
	private String buyerCreativeId ="";
	private String vendor_type = "";
	private String category ="";
	private String max_cpm_micros ="";
	private String processingTimeMs = "";
	
	
	public String getProtocolVersion() {
		return protocolVersion;
	}
	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}
	public String getHtmlSnippet() {
		return htmlSnippet;
	}
	public void setHtmlSnippet(String htmlSnippet) {
		this.htmlSnippet = htmlSnippet;
	}
	public String getClickThroughUrl() {
		return clickThroughUrl;
	}
	public void setClickThroughUrl(String clickThroughUrl) {
		this.clickThroughUrl = clickThroughUrl;
	}
	public String getBuyerCreativeId() {
		return buyerCreativeId;
	}
	public void setBuyerCreativeId(String buyerCreativeId) {
		this.buyerCreativeId = buyerCreativeId;
	}
	public String getVendor_type() {
		return vendor_type;
	}
	public void setVendor_type(String vendor_type) {
		this.vendor_type = vendor_type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getMax_cpm_micros() {
		return max_cpm_micros;
	}
	public void setMax_cpm_micros(String max_cpm_micros) {
		this.max_cpm_micros = max_cpm_micros;
	}
	public String getProcessingTimeMs() {
		return processingTimeMs;
	}
	public void setProcessingTimeMs(String processingTimeMs) {
		this.processingTimeMs = processingTimeMs;
	}
	
	
	 
	
	     
//	platform 
//	model v
}

