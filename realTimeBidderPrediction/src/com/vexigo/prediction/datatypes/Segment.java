package com.vexigo.prediction.datatypes;

import java.io.Serializable;

public class Segment implements Serializable 
{
	//ID of the data segment specific to the data provider.
	private String id = "";
	
	//Name of the data segment specific to the data provider.
	private String name = "";
	
	//String representation of the data segment value.
	private String value = "";
		
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	

}
