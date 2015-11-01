package com.vexigo.prediction.datatypes;

import java.io.Serializable;

public class User implements Serializable
{
	//Buyer-specific ID for the user as mapped by the exchange for the buyer. At least one of buyerid or id is recommended.
	private String buyerId ="";
	
	//Year of birth as a 4-digit integer.	
	private int yob =0;
	
	//Gender, where “M” = male, “F” = female, “O” = known to be
	private String gender ="O";
	
	//Optional feature to pass bidder data that was set in the exchange’s cookie. The string must be in base85 cookie safe characters and be in any format. Proper JSON encoding must be used to include “escaped” quotation marks.
	private String customdata="";
	
	//Location of the user’s home base defined by a Geo object
	private	Geo geo = null;
	
	//Additional user data. Each Data object represents a different data source.
	private UserData userData= null;
	
	
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public int getYob() {
		return yob;
	}
	public void setYob(int yob) {
		this.yob = yob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCustomdata() {
		return customdata;
	}
	public void setCustomdata(String customdata) {
		this.customdata = customdata;
	}
	public Geo getGeo() {
		return geo;
	}
	public void setGeo(Geo geo) {
		this.geo = geo;
	}
	public UserData getUserData() {
		return userData;
	}
	public void setUserData(UserData userData) {
		this.userData = userData;
	}
	
}
