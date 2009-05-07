
package com.silverpeas.applicationbuilder;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Makes logging available to every class in this package. The static initializer
 * opens the log file and closing is ensured by the "finalize" method of a
 * FileOutputStream.
 * @author Silverpeas
 * @version 1.0/B
 * @since 1.0/B
 */
public class AppBuilderException extends Exception {

	private Throwable nestedException = null;

    public AppBuilderException() {
		super();
    }

    public AppBuilderException(String message) {
		super(message);
    }

    public AppBuilderException(String message, Throwable underlyingException) {
		super(message);
		nestedException = underlyingException;
    }

	public void printStackTrace() {
    	if (nestedException != null) {
	    	System.err.println(getMessage());
		    nestedException.printStackTrace();
		} else {
	   		super.printStackTrace();
	    }
	}

	public void printStackTrace(PrintStream s) {
    	if (nestedException != null) {
	    	s.println(getMessage());
		    nestedException.printStackTrace(s);
		} else {
	   		super.printStackTrace(s);
	    }
	}

	public void printStackTrace(PrintWriter s) {
    	if (nestedException != null) {
	    	s.println(getMessage());
		    nestedException.printStackTrace(s);
		} else {
	   		super.printStackTrace(s);
	    }
	}

	public void printLogMessage() {
		if (getMessage() != null && !getMessage().trim().equals("")) {
			System.err.println(getMessage());
		}
		if (nestedException != null) {
			nestedException.printStackTrace();
		}
	}

	public void printLogMessage(PrintStream s) {
		if (getMessage() != null && !getMessage().trim().equals("")) {
			s.println(getMessage());
		}
		if (nestedException != null) {
			nestedException.printStackTrace(s);
		}
	}

	public void printLogMessage(PrintWriter s) {
		if (getMessage() != null && !getMessage().trim().equals("")) {
			s.println(getMessage());
		}
		if (nestedException != null) {
			nestedException.printStackTrace(s);
		}
	}
}