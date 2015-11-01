package com.vexigo.prediction.oauth;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.appengine.auth.oauth2.AppEngineCredentialStore;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Preconditions;
import com.google.api.services.prediction.Prediction;
import com.google.api.services.prediction.PredictionScopes;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

/*
 * CredentialUtils.java provides helper methods for generating a callback URI, handling
 * an API authorization code flow, and providing an authorized Analytic API client.
 */
public class CredentialUtils {

	static final HttpTransport HTTP_TRANSPORT = new UrlFetchTransport();
	static final JsonFactory JSON_FACTORY = new JacksonFactory();
	static final String RESOURCE_LOCATION = "WEB-INF/client_secret.json";
	private static GoogleClientSecrets clientSecrets = null;
	private static final String KIND = AppEngineCredentialStore.class.getName();
	

	private static GoogleAuthorizationCodeFlow flow = null;

	/**
	 * Exception thrown when an error occurred while retrieving credentials.
	 */
	public static class GetCredentialsException extends Exception {

		protected String authorizationUrl;

		/**
		 * Construct a GetCredentialsException.
		 * 
		 * @param authorizationUrl
		 *            The authorization URL to redirect the user to.
		 */
		public GetCredentialsException(String authorizationUrl) {
			this.authorizationUrl = authorizationUrl;
		}

		/**
		 * Set the authorization URL.
		 */
		public void setAuthorizationUrl(String authorizationUrl) {
			this.authorizationUrl = authorizationUrl;
		}

		/**
		 * @return the authorizationUrl
		 */
		public String getAuthorizationUrl() {
			return authorizationUrl;
		}
	}

	/**
	 * Exception thrown when a code exchange has failed.
	 */
	public static class CodeExchangeException extends GetCredentialsException {

		/**
		 * Construct a CodeExchangeException.
		 * 
		 * @param authorizationUrl
		 *            The authorization URL to redirect the user to.
		 */
		public CodeExchangeException(String authorizationUrl) {
			super(authorizationUrl);
		}

	}

	/**
	 * Exception thrown when no refresh token has been found.
	 */
	public static class NoRefreshTokenException extends GetCredentialsException {

		/**
		 * Construct a NoRefreshTokenException.
		 * 
		 * @param authorizationUrl
		 *            The authorization URL to redirect the user to.
		 */
		public NoRefreshTokenException(String authorizationUrl) {
			super(authorizationUrl);
		}

	}

	/**
	 * Exception thrown when no user ID could be retrieved.
	 */
	private static class NoUserIdException extends Exception {
	}

	static String getRedirectUri(HttpServletRequest req) {
		GenericUrl url = new GenericUrl(req.getRequestURL().toString());
		url.setRawPath("/oauth2callback");
		return url.build();
	}

	public static GoogleClientSecrets getClientSecret() throws IOException {
		if (clientSecrets == null) {
			InputStream inputStream = new FileInputStream(new File(
					RESOURCE_LOCATION));
			Reader reader = new InputStreamReader(inputStream);

			Preconditions.checkNotNull(inputStream, "Cannot open: %s"
					+ RESOURCE_LOCATION);
			clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, reader);
		}
		return clientSecrets;
	}

	public static GoogleAuthorizationCodeFlow newFlow() throws IOException {
		
		return new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT,
				JSON_FACTORY, getClientSecret(),
				Collections.singleton(PredictionScopes.DEVSTORAGE_READ_WRITE))
				.setCredentialStore(

				new AppEngineCredentialStore()).setAccessType("offline")
				.build(); 
	}

	public static Prediction loadPrediciton() throws IOException 
	{
		String userId = "herskovi77@gmail.com";
		if (UserServiceFactory.getUserService().getCurrentUser() != null)
			 userId = UserServiceFactory.getUserService().getCurrentUser().getUserId();
		return loadPrediction(userId);
	}

	public static Prediction loadPrediction(String userId) throws IOException 
	{
		Credential credential = loadCredential(userId);
		return new Prediction.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
				.build();
	}

	/**
	 * 
	 * @Author: Moshe Herskovits
	 * @Date: Jun 13, 2014
	 * @Description:
	 */

	public boolean load(String userId, Credential credential) 
	{
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Key key = KeyFactory.createKey(KIND, userId);
		try {
			Entity entity = datastore.get(key);
			credential.setAccessToken((String) entity
					.getProperty("accessToken"));
			credential.setRefreshToken((String) entity
					.getProperty("refreshToken"));
			credential.setExpirationTimeMilliseconds((Long) entity
					.getProperty("expirationTimeMillis"));
			return true;
		} catch (Exception exception) {
			return false;
		}

	}

	/**
	 * Exchange an authorization code for OAuth 2.0 credentials.
	 * 
	 * @param authorizationCode
	 *            Authorization code to exchange for OAuth 2.0 credentials.
	 * @return OAuth 2.0 credentials.
	 * @throws CodeExchangeException
	 *             An error occurred.
	 */
	public static Credential exchangeCode(String authorizationCode)
			throws CodeExchangeException 
	{
		try {
			GoogleAuthorizationCodeFlow flow = newFlow();
			GoogleTokenResponse response = flow
					.newTokenRequest(authorizationCode)
					.setRedirectUri(CredentialConsts.REDIRECT_URI).execute();
			return flow.createAndStoreCredential(response, null);
		} catch (IOException e) {
			System.err.println("An error occurred: " + e);
			throw new CodeExchangeException(null);
		}
	}

	/**
	 * @throws IOException
	 * @Author: Moshe Herskovits
	 * @Date: Jun 15, 2014
	 * @Description:
	 */
	public static Credential loadCredential(String userId) throws IOException 
	{
		
		return newFlow().loadCredential(userId);

	}

	/**
	 * Builds an empty credential object.
	 * 
	 * @return An empty credential object.
	 */
	public Credential buildEmpty()
	{
		
		return new GoogleCredential.Builder()
				.setClientSecrets(this.clientSecrets)
				.setTransport(HTTP_TRANSPORT).setJsonFactory(JSON_FACTORY)
				.build();
	}
	
	 

}
