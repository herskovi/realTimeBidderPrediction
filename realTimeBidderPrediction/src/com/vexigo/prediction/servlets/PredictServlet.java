/*
 * Copyright 2012 Google Inc.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Author: Marc Cohen
 *
 */

package com.vexigo.prediction.servlets;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.prediction.Prediction;
import com.google.api.services.prediction.PredictionScopes;
import com.google.api.services.prediction.model.Input;
import com.google.api.services.prediction.model.Input.InputInput;
import com.google.api.services.prediction.model.Output;
import com.vexigo.prediction.oauth.CredentialUtils;


public class PredictServlet extends HttpServlet 
{

//	/** Application name. */
//	private static final String APPLICATION_NAME =
//			"Prediction API Java Quickstart";
//
//	/** Directory to store user credentials for this application. */
//	private static final java.io.File DATA_STORE_DIR = new java.io.File(
//			System.getProperty("user.home"), ".credentials/drive-java-quickstart");
//
//	/** Global instance of the {@link FileDataStoreFactory}. */
//	private static FileDataStoreFactory DATA_STORE_FACTORY;
//
//	/** Global instance of the JSON factory. */
//	private static final JsonFactory JSON_FACTORY =
//			JacksonFactory.getDefaultInstance();
//
//	/** Global instance of the HTTP transport. */
//	private static HttpTransport HTTP_TRANSPORT;
//
//	/** Global instance of the scopes required by this quickstart. */
//	private static final List<String> SCOPES =
//			Arrays.asList(PredictionScopes.DEVSTORAGE_READ_WRITE);
//
//	private static GoogleClientSecrets clientSecrets = null;
	
	  public static final String redirectUri = 
			    "http://vexigomts.appspot.com/auth_return";

	
	public static final String modelsFile = "rc/models.json";
	
	public static final String  secretsFile = "rc/client_secrets.json";
	
	 @SuppressWarnings("unused")
	  private static final Logger log = 
	    LoggerFactory.getLogger(PredictServlet.class);


//	static {
//		try {
//			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
//			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
//		} catch (Throwable t) {
//			t.printStackTrace();
//			System.exit(1);
//		}
//	}



	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, 
			IOException {

		//Build a new authorized API client service via oAuth2
		Prediction service = CredentialUtils.loadPrediciton();

		// Get user requested model, if specified.
		String model_name = request.getParameter("model");

		// Parse model descriptions from models.json file.
		Map<String, Object> models =  parseJsonFile(modelsFile);
	    request.setAttribute("models", models);

	    // Set selected model name, if specified by user.
	    request.setAttribute("selected_model_name", model_name);



		// Setup reference to user specified model description.
		Map<String, Object> selectedModel = 
				(Map<String, Object>) models.get(model_name);
		

		  		    // Render jsp page with template input (models and selected model).
		    forward(request, response, "index.jsp");

		// Obtain model id (the name under which model was trained), 
		// and iterate over the model fields, building a list of Strings
		// to pass into the prediction request.
//		String modelId = (String) selectedModel.get("model_id");
//		List<Object> params = new ArrayList<Object>();
//		List<Map<String, String> > fields = 
//				(List<Map<String, String> >) selectedModel.get("fields");
//		for (Map<String, String> field : fields) {
//			// This loop is populating the input csv values for the prediction call.
//			String label = field.get("label");
//			String value = request.getParameter(label);
//			params.add(value);
//		}
//
//		// Set up OAuth 2.0 access of protected resources using the retrieved
//		// refresh and access tokens, automatically refreshing the access token 
//		// whenever it expires.
//		//    GoogleAccessProtectedResource requestInitializer = 
//		//      new GoogleAccessProtectedResource(tokens.accessToken, HTTP_TRANSPORT, 
//		//    		  JSON_FACTORY, clientId, clientSecrets, 
//		//                                        tokens.refreshToken);
//
//		// Now populate the prediction data, issue the API call and return the
//		// JSON results to the Javascript AJAX client.
//		HttpTransport HTTP_TRANSPORT=null;
//		JsonFactory JSON_FACTORY = null;
////				JacksonFactory.getDefaultInstance();
//		Prediction prediction = new Prediction(HTTP_TRANSPORT, JSON_FACTORY, (HttpRequestInitializer) service.getGoogleClientRequestInitializer());
//
//		Input input = new Input();
//		InputInput inputInput = new InputInput();
//		inputInput.setCsvInstance(params);
//		input.setInput(inputInput);
//
//		Output output = prediction.trainedmodels().predict("vexigomts", modelId, input).execute();
//		response.getWriter().println(output.toPrettyString());
	}

	 public static Map<String, Object> parseJsonFile(String file) throws FileNotFoundException, IOException {
		FileInputStream in = new FileInputStream(file);
		JacksonFactory factory = new JacksonFactory();
		JsonParser parser = factory.createJsonParser(in);
		Map<String, Object> jsonContainer = new HashMap<String, Object>();

		//JSON parser takes a callback function, which we don't use (so second arg is null).
		parser.parse(jsonContainer, null);
		in.close();
		return jsonContainer;
	}
	 
	 /*
	   * Forwards request and response to given path. Handles any exceptions
	   * caused by forward target by printing them to logger.
	   * 
	   * @param request 
	   * @param response
	   * @param path 
	   */
	  protected void forward(HttpServletRequest request,
				 HttpServletResponse response, String path) {
	    try {
	      RequestDispatcher rd = request.getRequestDispatcher(path);
	      rd.forward(request, response);
	    } catch (Throwable tr) {
	      if (log.isErrorEnabled()) {
	        log.error("Caught Exception: " + tr.getMessage());
	        log.debug("StackTrace:", tr);
	      }
	    }
	  }
	  
	  @Override
		protected void doPost(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, 
				IOException 
	  {
		  doGet(request, response);  
	  }
	  
	  




}
