/**
 * 
 */
package com.vexigo.prediction.oauth;

import java.net.URI;

/**
 * @author admin
 * May 5, 2014
 */public interface CredentialConsts

{
	public static  String TOKEN = "token"; 
	public static  String CLIENT_ID = "350054109263-8rvc4mcjfhhs8hv1h7or1h908hquqs23.apps.googleusercontent.com";
	public static  String CLIENT_SECRET = "YGQDbwIKDj0hxFjb3Pm4xGLa";
	public static  String REDIRECT_URI = "http%3A%2F%2Fdailyreportbysmsforga.appspot.com%2Fsmsforgoogleanalyticcallbacksample";
	public static  String GOOGLE_OAUTH_URL_IS_AUTH = "https://accounts.google.com/o/oauth2/auth?";
	public static  String GOOGLE_OAUTH_SCOPE_IS_ANALYTICS_READ_ONLY = "scope=https://www.googleapis.com/auth/prediction";
	public static  String GOOGLE_OAUTH_CLIENT_ID = "client_id="+ CLIENT_ID;
	public static  String GOOGLE_OAUTH_STATE_IS_EMPTY = "state=";
	public static  String GOOGLE_OAUTH_STATE_IS_PROFILE = "state=%2Fprofile";
	public static  String GOOGLE_OAUTH_RESPONSE_TYPE_IS_CODE = "response_type=code";
	public static  String GOOGLE_OAUTH_ACCESS_TYPE_IS_ONLINE = "access_type=online";
	public static  String GOOGLE_OAUTH_ACCESS_TYPE_IS_OFFLINE = "access_type=offline";
	public static  String GOOGLE_OAUTH_APPROVAL_PROMPT_IS_FORCE = "approval_prompt=force";
	public static  String GOOGLE_OAUTH_INCLUDE_GRANTED_SCOPE_IS_FALSE = "include_granted_scopes=false";
	public static  String GOOGLE_OAUTH_INCLUDE_GRANTED_SCOPE_IS_TRUE = "include_granted_scopes=true";
	public static  String GOOGLE_OAUTH_REDIERCET_URL_IS_LOCAL_HOST = "redirect_uri=http%3A%2F%2Flocalhost%3A8888%2Fsmsforgoogleanalyticcallbacksample";
	public static  String GOOGLE_OAUTH_REDIERCET_URL_IS_GAE_HOST = "redirect_uri=http%3A%2F%2Fdailyreportbysmsforga.appspot.com%2Fsmsforgoogleanalyticcallbacksample";
	public static  String REVOKE_TOKEN_URL = "https://accounts.google.com/o/oauth2/revoke";
	public static String HTML_OPERAND_AND = "&";
	
	
	public static final String GOOGLE_OAUTH_FOR_WEB_SERVER_FROM_LOCAL_HOST = GOOGLE_OAUTH_URL_IS_AUTH +  HTML_OPERAND_AND + 
																			 GOOGLE_OAUTH_SCOPE_IS_ANALYTICS_READ_ONLY + HTML_OPERAND_AND +
																			 GOOGLE_OAUTH_CLIENT_ID + HTML_OPERAND_AND +
																			 GOOGLE_OAUTH_STATE_IS_PROFILE + HTML_OPERAND_AND +
																			 GOOGLE_OAUTH_REDIERCET_URL_IS_LOCAL_HOST + HTML_OPERAND_AND +
																			 GOOGLE_OAUTH_RESPONSE_TYPE_IS_CODE + HTML_OPERAND_AND +
																			 GOOGLE_OAUTH_ACCESS_TYPE_IS_OFFLINE + HTML_OPERAND_AND +
																			 GOOGLE_OAUTH_APPROVAL_PROMPT_IS_FORCE + HTML_OPERAND_AND +
																			 GOOGLE_OAUTH_INCLUDE_GRANTED_SCOPE_IS_TRUE;
							 
	public static final String GOOGLE_OAUTH_FOR_WEB_SERVER_FROM_GAE_HOST =   GOOGLE_OAUTH_URL_IS_AUTH +  HTML_OPERAND_AND + 
				 															 GOOGLE_OAUTH_SCOPE_IS_ANALYTICS_READ_ONLY + HTML_OPERAND_AND +
				 															 GOOGLE_OAUTH_CLIENT_ID + HTML_OPERAND_AND +
																			 GOOGLE_OAUTH_STATE_IS_PROFILE + HTML_OPERAND_AND +
																			 GOOGLE_OAUTH_REDIERCET_URL_IS_GAE_HOST + HTML_OPERAND_AND +
																			 GOOGLE_OAUTH_RESPONSE_TYPE_IS_CODE + HTML_OPERAND_AND +
																			 GOOGLE_OAUTH_ACCESS_TYPE_IS_OFFLINE + HTML_OPERAND_AND +
																			 GOOGLE_OAUTH_APPROVAL_PROMPT_IS_FORCE + HTML_OPERAND_AND +
																			 GOOGLE_OAUTH_INCLUDE_GRANTED_SCOPE_IS_TRUE;
																			 




}
