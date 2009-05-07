//Source file: R:\\StraProduct\\Pkg1.0\\Dev\\SrcJava\\Java\\ApplicationBuilder\\JBuilderEnv\\src\\com\\silverpeas\\applicationbuilder\\WARDescriptor.java

package com.silverpeas.applicationbuilder;

import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

/**
 * this descriptor is created in memory. It is filled with the descriptor parts
 * from the WARParts. Finally, it is integrated in the WAR by the means of a
 * stream.
 * @author Silverpeas
 * @version 1.0/B
 * @since 1.0/B
 */
public class WARDescriptor extends XmlDocument {

	/**
	 * @since 1.0/B
	 */
	private static final String NAME = "web.xml";

	/**
	 * @since 1.0/B
	 */
	private static final String LOCATION = "WEB-INF";

	private static final String ROOT_ELT = "web-app";
	private static final String SERVLET_VERSION = "2.4";
	private static final String ROOT_NAMESPACE = "http://java.sun.com/xml/ns/j2ee";
	private static final String[][] ROOT_ADDITIONAL_NAMESPACE = { 
		{"xsi", "http://www.w3.org/2001/XMLSchema-instance"}, 
		{"schemaLocation","http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd"} };
	
	private static final String NAME_ELT = "display-name";
	private static final String DESC_ELT = "description";
	private static final String[] TAGS_TO_MERGE = {"context-param", "filter", "filter-mapping", "listener", "servlet", "servlet-mapping", "session-config"};
	private static final String[] TAGS_TO_SORT = {NAME_ELT, DESC_ELT, "context-param", "filter", "filter-mapping", "listener", "servlet", "servlet-mapping", "session-config"};;


	public WARDescriptor() {
		super(LOCATION, NAME);
		setDocument();
	}

	/**
	 * @roseuid 3AAE4499010D
	 */
	public void mergeWARPartDescriptor(XmlDocument descriptor) throws AppBuilderException {
		mergeWith(TAGS_TO_MERGE, descriptor);
	}

	/**
	 * Theorically, XML contents is not sorted. Actually, this descriptor must
	 * be sorted to work well in an application server.
	 */
	public void sort() throws AppBuilderException {
		sort(TAGS_TO_SORT);
	}

	private void setDocument() {
		Namespace nameSpace = Namespace.getNamespace(ROOT_NAMESPACE);
		Element root = new Element(ROOT_ELT, nameSpace);
		root.setAttribute("version", SERVLET_VERSION);		
		for (int i=0; i<ROOT_ADDITIONAL_NAMESPACE.length; i++) {
			root.addNamespaceDeclaration(Namespace.getNamespace(ROOT_ADDITIONAL_NAMESPACE[i][0], ROOT_ADDITIONAL_NAMESPACE[i][1]));
		}
		Element name = new Element(NAME_ELT);
		name.setText(ApplicationBuilder.getApplicationName());
		root.addContent(name);
		Element desc = new Element(DESC_ELT);
		desc.setText(ApplicationBuilder.getApplicationDescription());
		root.addContent(desc);
		Document doc = new Document(root);
		super.setDocument(doc);
	}
}
