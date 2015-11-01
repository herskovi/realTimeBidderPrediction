package com.vexigo.prediction.datatypes;

import java.io.Serializable;


public class Device implements Serializable
{
	
	//TODO - Prepare ENUM for each parameter

	private String DeviceType = "";
	private String platform = "";
	// The brand of the device, e.g. Nokia, Samsung.
	private String brand = "";
	//The model of the device, e.g. N70, Galaxy.
	private String model = "";
	// The OS version; e.g. 2 for Android 2.1, or 3.3 for iOS 3.3.1.
	private String osVersion = "";
	// Unique identifer for the mobile carrier if the device is connected to the
	// internet via a carrier (as opposed to via WiFi). To look up carrier name
	// and country from carrier ID, please refer to:
	// https://developers.google.com/adwords/api/docs/appendix/mobilecarriers.
	private int carrierId = 6;
	// The width of the device screen in pixels.
	private int screenWidth = 7;
	// The height of the device screen in pixels.
	private int screenHeight = 8;
	// Used for high-density devices (e.g. iOS retina displays).  A non-default
	// value indicates that the nominal screen size (with pixels as the unit)
	// does not describe the actual number of pixels in the screen. For example,
	// nominal width and height may be 320x640 for a screen that actually has
	// 640x1080 pixels, in which case screen_width=320, screen_height=640, and
	// screen_pixel_ratio_millis=2000, since each axis has twice as many pixels
	// as its dimensions would indicate.
	private String screen_pixelRatioMillis ="";

	public String getDeviceType() {
		return DeviceType;
	}

	public void setDeviceType(String deviceType) {
		DeviceType = deviceType;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public int getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(int carrierId) {
		this.carrierId = carrierId;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public String getScreen_pixelRatioMillis() {
		return screen_pixelRatioMillis;
	}

	public void setScreen_pixelRatioMillis(String screen_pixelRatioMillis) {
		this.screen_pixelRatioMillis = screen_pixelRatioMillis;
	}

		
	

}
