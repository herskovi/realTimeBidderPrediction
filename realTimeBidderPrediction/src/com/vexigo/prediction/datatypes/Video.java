package com.vexigo.prediction.datatypes;

import java.io.Serializable;
import java.util.ArrayList;

import com.vexigo.prediction.datatypes.enums.*;

public class Video  implements Serializable
{
	//Content MIME types supported. Popular MIME types may include “video/x-ms-wmv” for Windows Media and “video/x-flv” for Flash Video
	private ArrayList<String> mimeList = new ArrayList<String>();
	
	// Minimum video ad duration in seconds.
	private int minAdDuration = 8; 	
	
	//Maximum video ad duration in seconds.	
	private int maxduration = 8; 	
	
	//Array of supported video bid response protocols
	//1 - VAST 1.0; 2 - VAST 2.0; 3 - VAST 3.0; 4 -  VAST 1.0 Wrapper; 
	//5 - VAST 2.0 Wrapper; 6 - VAST 3.0 Wrapper
	private ArrayList<Integer> protocols = new ArrayList<Integer>();
	
	//Width of the video player in pixels.
	private int w=0;
	
	//Height of the video player in pixels.
	private int h=0;
		
	//Indicates the start delay in seconds for pre-roll, mid-roll, or post-roll ad placements. 
	//> 0 Mid-Roll (value indicates start delay in second); 0 - Pre-Roll; -1 - Generic Mid-Roll;  -2 - Generic Post-Roll
	private int startdelay = 0;
	
	
	
	// The URL of the page that the publisher gives Google to describe the video content, with parameters removed.
	private String descriptionUrl = ""; 	
	
	//
	private Gender gender = Gender.UNKNOWN;
	
	private boolean isEmbeddedOffsite = false; 	// If true, the video is embedded on pages out of the publisher's domain.
	private VideoPlayBackMode  playbackMethod = VideoPlayBackMode.AUTO_PLAY_SOUND_ON;
	// The time in milliseconds from the start of the video when the ad will be
	// displayed. 0 means pre-roll and -1 means post-roll. The value is valid
	// only if this param is set. When not set, the display position is unknown.
	private int videoadStartDelay = 1;
	private int maxAdDuration = 2; //The maximum duration in milliseconds of the ad that you should return.If this is not set or has value <= 0, any duration is allowed.
	
	
	// The maximum number of ads in an Adx video pod. A non-zero value indicates
	// that the current ad slot is a video pod that can show multiple video
	// ads. Actual number of video ads shown can be less than or equal to this
	// value but cannot exceed it.
	private int maxAdsInPod = 12;
	private InventoryType inventoryType = InventoryType.WEB_VIDEO; //	// The type of inventory from which request is sent. default = WEB_VIDEO;
	private SkippableBidRequestType videoAdSkippable = SkippableBidRequestType.ALLOW_SKIPPABLE; // Does the publisher allow/require/block skippable video ads?
	// The maximum duration in milliseconds for the ad you should return, if
	// this ad is skippable (this generally differs from the maximum duration
	// allowed for non-skippable ads). If this is not set or has value <= 0, any
	// duration is allowed.
	private int skippableMaxAdDuration = 5000;
	private VideoFormat allowedVideoFormats = VideoFormat.VIDEO_HTML5; 	// The video technologies that are allowed for this request. The response should support at least one of them.
	public String getDescriptionUrl() {
		return descriptionUrl;
	}
	public void setDescriptionUrl(String descriptionUrl) {
		this.descriptionUrl = descriptionUrl;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public boolean isEmbeddedOffsite() {
		return isEmbeddedOffsite;
	}
	public void setEmbeddedOffsite(boolean isEmbeddedOffsite) {
		this.isEmbeddedOffsite = isEmbeddedOffsite;
	}
	public VideoPlayBackMode getPlaybackMethod() {
		return playbackMethod;
	}
	public void setPlaybackMethod(VideoPlayBackMode playbackMethod) {
		this.playbackMethod = playbackMethod;
	}
	public int getVideoadStartDelay() {
		return videoadStartDelay;
	}
	public void setVideoadStartDelay(int videoadStartDelay) {
		this.videoadStartDelay = videoadStartDelay;
	}
	public int getMaxAdDuration() {
		return maxAdDuration;
	}
	public void setMaxAdDuration(int maxAdDuration) {
		this.maxAdDuration = maxAdDuration;
	}
	public int getMinAdDuration() {
		return minAdDuration;
	}
	public void setMinAdDuration(int minAdDuration) {
		this.minAdDuration = minAdDuration;
	}
	public int getMaxAdsInPod() {
		return maxAdsInPod;
	}
	public void setMaxAdsInPod(int maxAdsInPod) {
		this.maxAdsInPod = maxAdsInPod;
	}
	public InventoryType getInventoryType() {
		return inventoryType;
	}
	public void setInventoryType(InventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}
	public SkippableBidRequestType getVideoAdSkippable() {
		return videoAdSkippable;
	}
	public void setVideoAdSkippable(SkippableBidRequestType videoAdSkippable) {
		this.videoAdSkippable = videoAdSkippable;
	}
	public int getSkippableMaxAdDuration() {
		return skippableMaxAdDuration;
	}
	public void setSkippableMaxAdDuration(int skippableMaxAdDuration) {
		this.skippableMaxAdDuration = skippableMaxAdDuration;
	}
	public VideoFormat getAllowedVideoFormats() {
		return allowedVideoFormats;
	}
	public void setAllowedVideoFormats(VideoFormat allowedVideoFormats) {
		this.allowedVideoFormats = allowedVideoFormats;
	}
	
	
}


