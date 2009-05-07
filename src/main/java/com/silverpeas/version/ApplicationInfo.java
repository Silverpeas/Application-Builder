package com.silverpeas.version;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.silverpeas.installedtree.DirectoryLocator;

/**
 * Titre :        Application Builder
 * Description :
 * Copyright :    Copyright (c) 2001
 * Société :      Stratélia
 * @author Jean-Christophe Carry
 * @version 1.0
 */

public class ApplicationInfo {

	private static final String homeDirectoryKey = "silverpeas.home";
	private static final String applicationSubdir = "silverpeas";
	private static final String versionFileSuffix = "-version.properties";
	private static final String busVersionName = "applicativeBus";

  /**
   * Classe implémentant l'interface java.io.FilenameFilter
   * et permettant de récupérer la liste des fichiers correspondant au masque
   */
	private static class VersionFilter implements FilenameFilter {
		public VersionFilter () {}
		public boolean accept(java.io.File dir, String name) {
		    return name.toLowerCase().endsWith(versionFileSuffix.toLowerCase());
		}
	} // VersionFilter

	private static Map thePackages = null;

	public static Map getPackages() throws IOException {
		if (thePackages == null) {
			File versionDirectory =
	    		new File(DirectoryLocator.getVersionHome());
			String[] versionNames = versionDirectory.list(new VersionFilter());
	    	thePackages = new TreeMap();
			PackageInfo aPackage = null;
			for (int i = 0 ; i<versionNames.length ; i++) {
				aPackage = new PackageInfo(new File(versionDirectory, versionNames[i]));
			    thePackages.put(aPackage.getName(), aPackage);
			}
		}
		return thePackages;
	}

	public static Set getJobBoxes() throws IOException {
		Iterator iPackage = getPackages().keySet().iterator();
		Set jobBoxesNames = new TreeSet();
		PackageInfo info = null;
		while (iPackage.hasNext()) {
			info = (PackageInfo)getPackages().get(iPackage.next());
			jobBoxesNames.add(info.getBoxName());
		}
		Iterator iBox = jobBoxesNames.iterator();
		Set jobBoxes = new TreeSet();
		while (iBox.hasNext()) {
		    jobBoxes.add(new JobBoxInfo((String)iBox.next(), getPackages()));
		}
		return jobBoxes;
	}

	public static PackageInfo getBusInfo() throws IOException {
		return (PackageInfo)getPackages().get(busVersionName);
	}
}