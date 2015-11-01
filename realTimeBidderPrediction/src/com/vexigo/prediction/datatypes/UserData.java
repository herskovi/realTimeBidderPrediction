package com.vexigo.prediction.datatypes;

import java.io.Serializable;
import java.util.ArrayList;

public class UserData implements Serializable 
{
	//Exchange-specific ID for the data provider.
	private String id="";
	//Exchange-specific name for the data provider.

	private String name="";
	
	//Array of Segment (Section 3.2.15) objects that contain the actual data values.
	private ArrayList<Segment> segment= new ArrayList<Segment>();
	
	
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
	public ArrayList<Segment> getSegment() {
		return segment;
	}
	public void setSegment(ArrayList<Segment> segment) {
		this.segment = segment;
	}
}
