package com.silverpeas.helpbuilder;

import java.io.File;
import java.io.IOException;

import com.silverpeas.installedtree.DirectoryLocator;
import com.silverpeas.version.PackageInfo;

/**
 * Titre :        Application Builder
 * Description :
 * Copyright :    Copyright (c) 2001
 * Société :      Stratélia
 * @author Jean-Christophe Carry
 * @version 1.0
 */

public class HelpIndexBuilder extends TemplateBasedBuilder {

	private static final String indexName = "index-silverpeas.htm";

	public HelpIndexBuilder() throws IOException, Exception{
		super(indexName);
		writeInDirectory(DirectoryLocator.getHelpHome());
	}

	protected boolean isPackagePertinent(PackageInfo packInfo) throws Exception {
		String packageUrl = packInfo.getName()+File.separator+packInfo.getName()+".htm";
		File packageIndex = new File(DirectoryLocator.getHelpHome(), packageUrl);
		return packageIndex.exists();
	}
}
