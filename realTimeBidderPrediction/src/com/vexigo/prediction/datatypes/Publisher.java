package com.vexigo.prediction.datatypes;

import java.io.Serializable;
import java.util.ArrayList;

public class Publisher implements Serializable 
{
	//Exchange-specific publisher ID.
	private String id = "";
	
	//Publisher name (may be aliased at the publisher’s request).
	private String name="";
	
	//Array of IAB content categories that describe the publisher.
	private ArrayList<String> catList= new ArrayList<String>();
	
	//Highest level domain of the publisher (e.g., “publisher.com”).
	private String domain = "";
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getCatList() {
		return catList;
	}
	public void setCatList(ArrayList<String> catList) {
		this.catList = catList;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	} 
	
	
	

}
