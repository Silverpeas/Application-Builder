package com.silverpeas.xml.xpath;

import java.util.Arrays;

/**
 * Titre :        Application Builder
 * Description :
 * Copyright :    Copyright (c) 2001
 * Société :      Stratélia
 * @author Jean-Christophe Carry
 * @version 2.0
 */

public class XPathParseException extends Exception {

	private Integer _errorIndex=null;
	private String _indexedErrorMsg=null;

    public XPathParseException() {
		super();
    }

    public XPathParseException(String msg) {
		super(msg);
    }

    public XPathParseException(String msg, String xpathStr, int errorIndex) {
		super(msg+" at column "+errorIndex);
		_errorIndex=new Integer(errorIndex);
		_indexedErrorMsg=xpathStr+"\n";
		char[] padding=new char[errorIndex-1];
		Arrays.fill(padding, '-');
		_indexedErrorMsg+=new String(padding)+'^';
    }

	public Integer getErrorIndex() {
		return _errorIndex;
	}

	public String getErrorLocationMessage() {
		return _indexedErrorMsg;
	}

	public String getMessage() {
		String msg="";
		if (super.getMessage()!=null) {
			msg+=super.getMessage();
		}
		if (getErrorLocationMessage()!=null) {
			msg+="\n"+getErrorLocationMessage();
		}
		return msg.equals("")?null:msg;
	}
}