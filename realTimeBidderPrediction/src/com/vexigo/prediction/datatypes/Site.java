package com.vexigo.prediction.datatypes;

import java.io.Serializable;
import java.util.ArrayList;

public class Site implements Serializable 
{
	//Exchange-specific site ID.
	private String id="";
	
	//Site name (may be aliased at the publisher’s request).
	private String name ="";
	
	//Domain of the site (e.g., “mysite.foo.com”).
	private String domain ="";
	
	//Array of IAB content categories of the site. Refer to List 5.1.
	private ArrayList<String> catList = new ArrayList<String>();
	
	//Array of IAB content categories that describe the current section of the site. Refer to List 5.1.
	private ArrayList<String> sectioncat = new ArrayList<String>();
	
	//Array of IAB content categories that describe the current page or view of the site.
	private ArrayList<String> pagecat = new ArrayList<String>();
	
	//URL of the page where the impression will be shown.
	private String page =""; 
	
	//Referrer URL that caused navigation to the current page.
	private String ref ="";
	
	//Search string that caused navigation to the current page.
	private String search ="";
	
	//Mobile-optimized signal, where 0 = no, 1 = yes.
	private int  mobile = 0; 
	
	//Indicates if the site has a privacy policy, where 0 = no, 1 = yes.
	private int privacypolicy =0;
	
	//Details about the Publisher  of the site.
	private Publisher publisher= null;

	//Details about the Content  within the site.
	private Content content= null;
	
	//Comma separated list of keywords about the site.
	private String keywords= null;
	
	
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
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public ArrayList<String> getCatList() {
		return catList;
	}
	public void setCatList(ArrayList<String> catList) {
		this.catList = catList;
	}
	public ArrayList<String> getSectioncat() {
		return sectioncat;
	}
	public void setSectioncat(ArrayList<String> sectioncat) {
		this.sectioncat = sectioncat;
	}
	public ArrayList<String> getPagecat() {
		return pagecat;
	}
	public void setPagecat(ArrayList<String> pagecat) {
		this.pagecat = pagecat;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public int getMobile() {
		return mobile;
	}
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	public int getPrivacypolicy() {
		return privacypolicy;
	}
	public void setPrivacypolicy(int privacypolicy) {
		this.privacypolicy = privacypolicy;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}


	

	

}
