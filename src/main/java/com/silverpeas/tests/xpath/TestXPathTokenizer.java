package com.silverpeas.tests.xpath;

import com.silverpeas.xml.xpath.XPathTokenizer;

/**
 * Titre :        Application Builder
 * Description :
 * Copyright :    Copyright (c) 2001
 * Société :      Stratélia
 * @author Jean-Christophe Carry
 * @version 2.0
 */

public class TestXPathTokenizer {

	public TestXPathTokenizer() throws Exception {
		testlexical();
	}

	public void testlexical(){
		x = new XPathTokenizer();

		System.out.println("##### Cas d'ERREURs :");
		System.out.println();

		testRead(null);
		testRead("");
		System.out.println();
		System.out.println("ERREUR sur ','");
		testRead("w,n!<à'( ç\"éè(néM95BHNDàA3%724 4²<U ");
		System.out.println();
		System.out.println("ERREUR sur car 15 du nom");
		testRead("nimporte_quoi-ésurtout@tres bizarre");
		System.out.println();
		System.out.println("ERREUR sur car 27");
		testRead("nimporte_quoi-surtout@tres bizarre");
		System.out.println();
		System.out.println("ERREUR literaux non fermés");
		testRead("'single quoted literal");
		testRead("\"double quoted literal");
		testRead("'");
		testRead("\"");

		System.out.println("##### Cas normaux :");
		System.out.println();

		testRead(".");
		testRead("..");
		testRead("/");
		testRead("@");
		testRead("[");
		testRead("]");
		testRead("=");

		testRead("'single quoted literal'");
		testRead("\"double quoted literal\"");
		testRead("''");
		testRead("\"\"");

		testRead("123456");
		testRead("123.456");
		testRead(".456");

		testRead("/Domain/Server/@NativeIOEnabled");
		testRead("/WAAddressbooks/param[param-name='Home']");
		testRead("param-value");

		testRead(".nimportequoi");
		testRead("..nimportequoi");
		testRead("/nimportequoi");
		testRead("@nimportequoi");
		testRead("[nimportequoi");
		testRead("]nimportequoi");
		testRead("=nimportequoi");
		testRead("'single quoted literal'nimportequoi");
		testRead("\"double quoted literal\"nimportequoi");
		testRead("123456nimportequoi");
		testRead("123.456nimportequoi");
		testRead(".456nimportequoi");

		testRead("name.");
		testRead("name..");
		testRead("name/");
		testRead("name@");
		testRead("name[");
		testRead("name]");
		testRead("name=");

		testRead("name'single quoted literal'");
		testRead("name\"double quoted literal\"");
		testRead("name''");
		testRead("name\"\"");

		testRead("name123456");
		testRead("name123.456");
		testRead("name.456");
	}

	private XPathTokenizer x = null;

	private void testRead(String s) {
	try{
		System.out.println(s+" =>");
		x.setXPath(s);
		String token = null;
		char tokenType;
		while((tokenType=x.readNextToken()) != XPathTokenizer.END_OF_XPATH){
			token=x.getCurrentToken();
			if (token==null)
				System.out.println("\t"+tokenType+" : "+tokenType);
			else
				System.out.println("\t"+tokenType+" : "+x.getCurrentToken());
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	}

    public static void main(String[] args) throws Exception {
		TestXPathTokenizer t = new TestXPathTokenizer();
    }
}