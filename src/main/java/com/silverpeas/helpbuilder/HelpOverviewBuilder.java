package com.silverpeas.helpbuilder;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import com.silverpeas.installedtree.DirectoryLocator;
import com.silverpeas.version.ApplicationInfo;
import com.silverpeas.version.JobBoxInfo;
import com.silverpeas.version.PackageInfo;

/**
 * Titre :        Application Builder
 * Description :
 * Copyright :    Copyright (c) 2001
 * Société :      Stratélia
 * @author Jean-Christophe Carry
 * @version 1.0
 */

public class HelpOverviewBuilder extends TemplateBasedBuilder {

	private static final String overviewName = "overview-silverpeas.htm";

	public HelpOverviewBuilder() throws IOException, Exception{
		super(overviewName);
		writeInDirectory(DirectoryLocator.getHelpHome());
	}

	// surcharge de la methode parente
	public String getTargetContents() throws IOException, Exception {

		if (targetFileContents == null) {

		        Iterator iBox = ApplicationInfo.getJobBoxes().iterator();
		        Iterator iPackage = null;
		        JobBoxInfo boxInfo = null;
		        PackageInfo packInfo = null;
			String master = null;
		        String tmpBoxes = "";
		        String tmpStr = null;
			String tmpPackages = "";

		        while (iBox.hasNext()) {

				boxInfo = (JobBoxInfo)iBox.next();

        		        tmpStr = getBoxTemplate();

				// on ignore les packages Job'Tools, Job'Lets et Spécifique
				// car ils n'ont pas d'aide en ligne utilisateur
				String boxName = boxInfo.getName();
				if (!boxName.equalsIgnoreCase("Job'Tools") &&
					!boxName.equalsIgnoreCase("Job'Lets") &&
					!boxName.equalsIgnoreCase("Spécifique")) {

		        	        tmpStr= setTemplateValue(
	        	        		tmpStr,
		        	        	boxSpecialNamePlaceHolder,
			        	        PackageInfo.specialName(boxName));

		        	        tmpStr= setTemplateValue(
	        	        		tmpStr,
		        	        	boxNamePlaceHolder,
			        	        boxInfo.getName());

				        tmpStr= setTemplateValue(
	        				tmpStr,
		        			packagesContentsPlaceHolder,
			        		tmpPackages);
			                tmpBoxes+=tmpStr;
				} // if
		        } // while

		        master = getMasterTemplate();

			targetFileContents = setTemplateValue(
				master,
				boxesContentsPlaceHolder,
				tmpBoxes);
		} // if

		return targetFileContents;
	}

	protected boolean isPackagePertinent(PackageInfo packInfo) throws Exception {
		String packageUrl = packInfo.getName()+File.separator+packInfo.getName()+".htm";
		File packageIndex = new File(DirectoryLocator.getHelpHome(), packageUrl);
		return packageIndex.exists();
	}
}
