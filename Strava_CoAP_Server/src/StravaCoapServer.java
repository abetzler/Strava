/*******************************************************************************
 * Copyright (c) 2014 Institute for Pervasive Computing, ETH Zurich and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 * 
 * The Eclipse Public License is available at
 *    http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *    http://www.eclipse.org/org/documents/edl-v10.html.
 * 
 * Contributors:
 *    Matthias Kovatsch - creator and main architect
 ******************************************************************************/


import java.net.SocketException;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.server.resources.CoapExchange;


public class StravaCoapServer extends CoapServer {
    
    /*
     * Application entry point.
     */
    public static void main(String[] args) {
        
        try {
            
            // create server
            StravaCoapServer server = new StravaCoapServer();
            server.start();
            
        } catch (SocketException e) {
            
            System.err.println("Failed to initialize server: " + e.getMessage());
        }
    }
    
    /*
     * Constructor for a new Hello-World server. Here, the resources
     * of the server are initialized.
     */
    public StravaCoapServer() throws SocketException {
        
        // provide an instance of a Hello-World resource
        add(new HelloWorldResource());
    }
    
    /*
     * Definition of the Hello-World Resource
     */
    class HelloWorldResource extends CoapResource {
        
        public HelloWorldResource() {
            
            // set resource identifier
            super("kilometers");
            
            // set display name
            getAttributes().setTitle("Strava Group Kilometers");
        }
        
        @Override
        public void handleGET(CoapExchange exchange) {
            
            // respond to the request
        	StravaInfo info = new StravaInfo();
        	String response = info.getCurrentStatus();
            exchange.respond(response);
        }
    }
}
