package com.vexigo.prediction.datatypes;

import java.io.Serializable;
import java.util.ArrayList;

public class Content implements Serializable 
{
	//ID uniquely identifying the content.
	private String id="";
	//Episode number (typically applies to video content).
	private int episode =0;
	//Video Examples: “Search Committee” (television), “A New Hope” (movie), or “Endgame” (made for web).
	private String  title = "";
	//Video Examples: “The Office” (television), “Star Wars” (movie), or “Arby ‘N’ The Chief” (made for web).
	private String series ="";
	//Content season; typically for video content (e.g., “Season 3”).
	private String season ="";
	//Details about the content Producer.
	private Producer producer = null;
	//URL of the content, for buy-side contextualization or review.	
	private String url ="";
	//Array of IAB content categories that describe the content producer. 
	private ArrayList<String> catList= new ArrayList<String>(); 
	//Video quality per IAB’s classification.0 - Unknown; 1 -Professionally Produced; 2 - Prosumer; 3 - User Generated (UGC 
	private int videoquality = 0;
//	Type of content (game, video, text, etc.).
//	1 - Video (a video file or stream that is being watched by the user, including (Internet) television broadcasts)
//	2 - Game (an interactive software game that is being played by the user)
//	3 - Music (an audio file or stream that is being listened to by the user, including (Internet) radio broadcasts)
//	4 - Application (an interactive software application that is being used by the user)
//	5 - Text (a document that is primarily textual in nature that is being read or viewed by the user, including web page, eBook, or news article)
//	6 - Other (content type unknown or the user is consuming content which does not fit into one of the categories above)
//	7 - Unknown
	private int context = 0;
//	Content rating (e.g., MPAA).
	private String contentrating ="";
	//User rating of the content (e.g., number of stars, likes, etc.).
	private String userrating = "";
	//Media rating per QAG guidelines. 
	//1 - All Audiences; 2 - Everyone Over 12;  3 - Mature Audiences
	private int qagmediarating =1;
	//	Comma separated list of keywords describing the content.
	private String   keywords = "";
	//0 = not live, 1 = content is live (e.g., stream, live blog).
	private int livestream = 0;
	//0 = indirect, 1 = direct.
	private int  sourcerelationship = 0;
	//	Length of content in seconds; appropriate for video or audio.
	private int len = 0;
	//Content language using ISO-639-1-alpha-2.
	private String language = "";
	//	Indicator of whether or not the content is embeddable (e
	private int embeddable = 0;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getEpisode() {
		return episode;
	}
	public void setEpisode(int episode) {
		this.episode = episode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public Producer getProducer() {
		return producer;
	}
	public void setProducer(Producer producer) {
		this.producer = producer;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public ArrayList<String> getCatList() {
		return catList;
	}
	public void setCatList(ArrayList<String> catList) {
		this.catList = catList;
	}
	public int getVideoquality() {
		return videoquality;
	}
	public void setVideoquality(int videoquality) {
		this.videoquality = videoquality;
	}
	public int getContext() {
		return context;
	}
	public void setContext(int context) {
		this.context = context;
	}
	public String getContentrating() {
		return contentrating;
	}
	public void setContentrating(String contentrating) {
		this.contentrating = contentrating;
	}
	public String getUserrating() {
		return userrating;
	}
	public void setUserrating(String userrating) {
		this.userrating = userrating;
	}
	public int getQagmediarating() {
		return qagmediarating;
	}
	public void setQagmediarating(int qagmediarating) {
		this.qagmediarating = qagmediarating;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public int getLivestream() {
		return livestream;
	}
	public void setLivestream(int livestream) {
		this.livestream = livestream;
	}
	public int getSourcerelationship() {
		return sourcerelationship;
	}
	public void setSourcerelationship(int sourcerelationship) {
		this.sourcerelationship = sourcerelationship;
	}
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getEmbeddable() {
		return embeddable;
	}
	public void setEmbeddable(int embeddable) {
		this.embeddable = embeddable;
	} 
	
	
	

}
