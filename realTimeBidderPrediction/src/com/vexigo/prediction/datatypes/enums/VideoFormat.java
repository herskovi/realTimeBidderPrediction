package com.vexigo.prediction.datatypes.enums;

public enum VideoFormat 
{
	VIDEO_FLASH,    
	VIDEO_HTML5,  // Valid HTML5 VAST ads contain both mp4 and webm media files in the first Ad/Creative VAST node.
    YT_HOSTED;;    // Valid VAST ads with at least one media file hosted on youtube.com.

}
