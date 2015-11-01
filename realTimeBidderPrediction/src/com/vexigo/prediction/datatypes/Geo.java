package com.vexigo.prediction.datatypes;

import java.io.Serializable;

public class Geo implements Serializable 
{

	// Latitude from -90.0 to +90.0, where negative is south.
	private float lat = 0;
	//Longitude from -180.0 to +180.0, where negative is west.	
	private float lon = 0;
	//1 - GPS/Location Services; 2 -  IP Address; 3 - User provided (e.g., registration data)
	private int type = 0;
	//Country code using ISO-3166-1-alpha-3.
	private String country="";
	//Region code using ISO-3166-2; 2-letter state code if USA.
	private String region="";
	//Google metro code; similar to but not exactly Nielsen DMAs. See Appendix A for a link to the codes.
	private String metro="";
	//City using United Nations Code for Trade & Transport Locations. See Appendix A for a link to the codes.
	private String city = "";
	//Zip or postal code.
	private String zip = "";
	//Local time as the number +/- of minutes from UTC.
	private int utcoffset =0;
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLon() {
		return lon;
	}
	public void setLon(float lon) {
		this.lon = lon;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getMetro() {
		return metro;
	}
	public void setMetro(String metro) {
		this.metro = metro;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public int getUtcoffset() {
		return utcoffset;
	}
	public void setUtcoffset(int utcoffset) {
		this.utcoffset = utcoffset;
	}
	
	
	

}
