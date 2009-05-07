package com.silverpeas.installedtree;


/**
 * Titre :        Application Builder
 * Description :
 * Copyright :    Copyright (c) 2001
 * Société :      Stratélia
 * @author Jean-Christophe Carry
 * @version 2.0
 */

public class SilverpeasHomeResolver {

	private static final String HOME_KEY = "silverpeas.home";

	private static String silverpeasHome = null;
	private static boolean abortOnError = true;

	/**
	 * If set to TRUE, program is exited if Silverpeas install location cannot be found.
	 * Else, a message is issued and execution goes on
	 */
	public static void setAbortOnError(boolean on) {
		abortOnError = on;
	}

	/**
	 * If TRUE, program is exited if Silverpeas install location cannot be found.
	 * Else, a message is issued and execution goes on
	 */
	public static boolean getAbortOnError() {
		return abortOnError;
	}

	/**
	 * Finds Silverpeas install directory
	 * Silverpeas install location may be set by using -Dsilverpeas.home=<i>location</i> on java command line
	 * @return the silverpeas home directory
	 */
	public static String getHome() {
		if (silverpeasHome == null) {
			if (!System.getProperties().containsKey(HOME_KEY)) {
	    		System.err.println("### CANNOT FIND SILVERPEAS INSTALL LOCATION ###");
		    	System.err.println("please use \"-D"+HOME_KEY+"=<install location>\" on the command line");
				if (getAbortOnError()) {
		    		System.err.println("### ABORTED ###");
					System.exit(1);
				}
			}
			silverpeasHome = System.getProperty(HOME_KEY);
		}
		return silverpeasHome;
	}
}