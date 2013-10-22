/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * EchoClient
 * 
 * Created: 09.10.2009
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.old.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * @author EremenkoAA
 * 
 */
public class EchoClient {

	static Logger log = Logger.getLogger(EchoClient.class);

	public static void main(String[] args) throws IOException {
		BasicConfigurator.configure();

		Socket echoSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		String host = InetAddress.getLocalHost().getHostName();

		try {
			echoSocket = new Socket(host, 777);

			if (echoSocket.isConnected()) {
				log.debug("Connected =)");
				out = new PrintWriter(echoSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			} else {
				log.debug("Not connected =(");
			}
			
		} catch (UnknownHostException e) {
			log.error(String.format("Don't know about host: %s.", host), e);
			System.exit(1);
		} catch (IOException e) {
			log.error(String.format("Couldn't get I/O for " + "the connection to: %s.", host), e);
			System.exit(1);
		}

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String userInput;

		while ((userInput = stdIn.readLine()) != null) {
			out.println(userInput);
			log.info("echo: " + in.readLine());
		}

		out.close();
		in.close();
		stdIn.close();
		echoSocket.close();
	}

}
