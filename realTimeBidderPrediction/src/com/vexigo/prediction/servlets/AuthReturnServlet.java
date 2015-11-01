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

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class AuthReturnServlet extends HttpServlet {

  @SuppressWarnings("unused")
  private static final Logger log = 
    LoggerFactory.getLogger(AuthReturnServlet.class);

  @Override
  protected void doGet(HttpServletRequest request, 
                       HttpServletResponse response) throws 
                         ServletException, IOException {

    log.info ("doGet for /auth_return service");

    // Parse client secrets json file and extract info needed for OAuth dance.
    Map<String, String> clientSecrets = 
      (Map<String, String>) PredictServlet.parseJsonFile(PredictServlet.secretsFile).get("installed");
    String clientId = clientSecrets.get("client_id");
    String clientSecret = clientSecrets.get("client_secret");

    // Read the auth code from URL.
    String code = request.getParameterValues("code")[0];

    // We need an App Engine specific HTTP Transport and a JSON parser
    HttpTransport transport = new NetHttpTransport();
    JacksonFactory jsonFactory = new JacksonFactory();

    // Exchange auth code for access and refresh tokens
//    AccessTokenResponse tokens = new GoogleAuthorizationCodeGrant(
//      transport, jsonFactory, clientId, clientSecret, code, 
//      PredictServlet.redirectUri).execute();
//    
    GoogleTokenResponse googleTokenResponse=new GoogleAuthorizationCodeTokenRequest(transport,jsonFactory, clientId, clientSecret,code,PredictServlet.redirectUri).execute();
   // return buildEmptyCredential().setFromTokenResponse(response);


    // Populate credentials object and store in app engine datastore.
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Entity credentials = new Entity("Credentials", "Credentials");
    credentials.setProperty("accessToken", googleTokenResponse.getAccessToken());
    credentials.setProperty("expiresIn", googleTokenResponse.getExpiresInSeconds());
    credentials.setProperty("refreshToken", googleTokenResponse.getRefreshToken());
    credentials.setProperty("clientId", clientId);
    credentials.setProperty("clientSecret", clientSecret);
    datastore.put(credentials);

    // With server credentials in hand, redirect session to main page.
    response.sendRedirect("/");
  }
}