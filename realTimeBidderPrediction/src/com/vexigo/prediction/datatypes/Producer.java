package com.vexigo.prediction.datatypes;

import java.io.Serializable;
import java.util.ArrayList;

public class Producer implements Serializable 
{
	//Content producer or originator ID. Useful if content is syndicated and may be posted on a site using embed tags.	
	private String id="";
	//Content producer or originator name (e.g., “Warner Bros”).
	private String 	name=""; 
	//Array of IAB content categories that describe the content producer. Refer to List 5.1.
	private ArrayList<String> catList= new ArrayList<String>(); 
	//	Highest level domain of the content producer (e.g., “producer.com”).
	private String 	domain="";
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
