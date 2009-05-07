//Source file: R:\\StraProduct\\Pkg1.0\\Dev\\SrcJava\\Java\\ApplicationBuilder\\JBuilderEnv\\src\\com\\silverpeas\\applicationbuilder\\AppDescriptor.java

package com.silverpeas.applicationbuilder;

import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;

/**
 * This descriptor is generated in memory. It is filled with the information and
 * then integrated to the EAR archive by the means of a stream.
 * @author Silverpeas
 * @version 1.0/B
 * @since 1.0/B
 */
public class AppDescriptor extends XmlDocument {

	/**
	 * @since 1.0/B
	 */
	private static final String NAME = "application.xml";

	/**
	 * @since 1.0/B
	 */
	private static final String LOCATION = "META-INF";

	private static final String PUBLIC_ID = "-//Sun Microsystems, Inc.//DTD J2EE Application 1.2//EN";
	private static final String SYSTEM_ID = "http://java.sun.com/j2ee/dtds/application_1_2.dtd";
	private static final String ROOT_ELT = "application";
	private static final String NAME_ELT = "display-name";
	private static final String DESC_ELT = "description";
	private static final String MODULE_ELT = "module";
	private static final String MOD_EJB_ELT = "ejb";
	private static final String MOD_WEB_ELT = "web";
	private static final String MOD_LIB_ELT = "java";
	private static final String MOD_WEB_URI_ELT = "web-uri";
	private static final String MOD_WEB_ROOT_ELT = "context-root";

	public AppDescriptor() {
		super(LOCATION, NAME);
		setDocument();
	}

	/**
	 * Adds a "module" tag with nested EJB JAR name within "ejb" tag
	 * example :
	 * &lt;module&gt;
	 *    &lt;ejb&gt;ejb-jar-ic-forums.jar&lt;/ejb&gt;
	 * &lt;/module&gt;
	 *
	 * @since 1.0/B
	 * @roseuid 3AAE3C420398
	 */
	public void addEJBName(String ejbJarName) {
		Element ejb = new Element(MOD_EJB_ELT);
		ejb.setText(ejbJarName);
		Element module = new Element(MODULE_ELT);
		module.addContent(ejb);
		getDocument().getRootElement().addContent(module);
	}

	/**
	 * Example :
	 * &lt;module&gt;
	 *    &lt;web&gt;
	 *       &lt;web-uri&gt;war-ic.war&lt;/web-uri&gt;
	 *       &lt;context-root&gt;webactiv&lt;/context-root&gt;
	 *    &lt;/web&gt;
	 * &lt;/module&gt;
	 * @roseuid 3AAE3CF503E5
	 */
	public void setWARInfos(String webUri, String contextRoot) {
		Element uri = new Element(MOD_WEB_URI_ELT);
		uri.setText(webUri);
		Element rootCntxt = new Element(MOD_WEB_ROOT_ELT);
		rootCntxt.setText(contextRoot);
		Element web = new Element(MOD_WEB_ELT);
		web.addContent(uri);
		web.addContent(rootCntxt);
		Element module = new Element(MODULE_ELT);
		module.addContent(web);
		getDocument().getRootElement().addContent(module);
	}
	
	public void setClientInfos(String clientJarName)
	{
		Element lib = new Element(MOD_LIB_ELT);
		lib.setText(clientJarName);
		Element module = new Element(MODULE_ELT);
		module.addContent(lib);
		getDocument().getRootElement().addContent(module);
	}

	private void setDocument() {
		DocType type = new DocType(ROOT_ELT, PUBLIC_ID, SYSTEM_ID);
		Element root = new Element(ROOT_ELT);
		Element name = new Element(NAME_ELT);
		name.setText(ApplicationBuilder.getApplicationName());
		root.addContent(name);
		Element desc = new Element(DESC_ELT);
		desc.setText(ApplicationBuilder.getApplicationDescription());
		root.addContent(desc);
		Document doc = new Document(root, type);
		super.setDocument(doc);
	}
}
