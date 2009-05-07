package com.silverpeas.helpbuilder;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

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

public class AllPeasIndexBuilder extends TemplateBasedBuilder {

	private static final String allpeasName = "allpeas-silverpeas.htm";

	public AllPeasIndexBuilder() throws IOException, Exception{
		super(allpeasName);
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

			// Liste des packages eligibles pour la doc classes par ordre alphabetique du ShortName
		        Vector v = new Vector();

		        while (iBox.hasNext()) {
				boxInfo = (JobBoxInfo)iBox.next();
				iPackage = boxInfo.getPackages().iterator();

				while (iPackage.hasNext()) {
				        packInfo = (PackageInfo)iPackage.next();
					if (isPackagePertinent(packInfo)) {
						String shortName = packInfo.getShortName();
						int j=0;
						String csn = "";
						for (int i=0;i<v.size();i++) {
							csn = ((PackageInfo)v.get(i)).getShortName();
							if (csn.compareTo(shortName)>0)
								break;
							else
								j++;
						} // for
						v.add(j,packInfo);
					} // if
				} // while
		        } // while

			String tmpPackages = "";
			for (int i=0;i<v.size();i++)
				tmpPackages+=getPackageContents((PackageInfo)v.get(i));

			if (!tmpPackages.trim().equals("")) {
			        tmpStr = getBoxTemplate();
			        tmpStr= setTemplateValue(
					tmpStr,
					boxNamePlaceHolder,
					boxInfo.getName());
			        tmpStr= setTemplateValue(
					tmpStr,
					packagesContentsPlaceHolder,
					tmpPackages);
			        tmpBoxes+=tmpStr;
			}

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
