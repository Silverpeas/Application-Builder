package com.silverpeas.helpbuilder;

import java.io.IOException;

import com.silverpeas.installedtree.DirectoryLocator;

/**
 * Titre :        Application Builder
 * Description :
 * Copyright :    Copyright (c) 2001
 * Société :      Stratélia
 * @author Jean-Christophe Carry
 * @version 1.0
 */

public class AboutBuilder extends TemplateBasedBuilder {

	private static final String indexName = "about-silverpeas.htm";

    public AboutBuilder() throws IOException, Exception {
		super(indexName);
		writeInDirectory(DirectoryLocator.getHelpHome());
    }

}
