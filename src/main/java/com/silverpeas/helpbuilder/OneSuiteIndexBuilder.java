package com.silverpeas.helpbuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
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

public class OneSuiteIndexBuilder extends TemplateBasedBuilder {

	private static final String suiteName = "suite-silverpeas.htm";

	public OneSuiteIndexBuilder() throws IOException, Exception{
		super(suiteName);
		// writeInDirectory(DirectoryLocator.getHelpHome());
		getTargetContents();
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

		        while (iBox.hasNext()) {
				boxInfo = (JobBoxInfo)iBox.next();
				iPackage = boxInfo.getPackages().iterator();
				String tmpPackages = "";

			        tmpStr = getBoxTemplate();

			        tmpStr= setTemplateValue(
					tmpStr,
					boxNamePlaceHolder,
					boxInfo.getName());

				while (iPackage.hasNext()) {
				        packInfo = (PackageInfo)iPackage.next();
					if (isPackagePertinent(packInfo)) {
						tmpPackages+=getPackageContents(packInfo);
				        }
				} // while (packages)

			        master = getMasterTemplate();

				targetFileContents = setTemplateValue(
	        			master,
		        		boxesContentsPlaceHolder,
			        	tmpStr);

			        targetFileContents= setTemplateValue(
						targetFileContents,
						packagesContentsPlaceHolder,
						tmpPackages);

				// sauvegarde en renommant
				Reader srcText = new StringReader(targetFileContents);
				FileWriter out = new FileWriter(new File(DirectoryLocator.getHelpHome(), PackageInfo.specialName(boxInfo.getName()) + "-" + suiteName));
				int charsRead;
				while ((charsRead = srcText.read(data, 0, BUFSIZE)) > 0 ) {
					out.write(data, 0, charsRead);
				}
				out.close();
				srcText.close();

		        } //while (boxes)

		} // if

		return null;
	}

	protected boolean isPackagePertinent(PackageInfo packInfo) throws Exception {
		String packageUrl = packInfo.getName()+File.separator+packInfo.getName()+".htm";
		File packageIndex = new File(DirectoryLocator.getHelpHome(), packageUrl);
		return packageIndex.exists();
	}
}
