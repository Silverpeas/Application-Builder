package com.silverpeas.version;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.PropertyResourceBundle;

/**
 * Titre :        Application Builder
 * Description :
 * Copyright :    Copyright (c) 2001
 * Société :      Stratélia
 * @author Jean-Christophe Carry
 * @version 1.0
 */

public class PackageInfo implements Comparable {

	// expected property names
	private static final String packageKey = "Package";
	private static final String nameKey = "Name";
	private static final String shortnameKey = "ShortName";
	private static final String boxKey = "Box";
	private static final String versionKey = "Version";
	private static final String releaseKey = "Release";
	private static final String buildKey = "Build";
	private static final String keyKey = "Key";

	private PropertyResourceBundle infos = null;

	public boolean equals(Object o) {
		if (!(o instanceof PackageInfo)) {
			return false;
		}
		return getDisplayNameIfAvailable().equals(((PackageInfo)o).getDisplayNameIfAvailable());
	}

	public int compareTo(Object o) {
		if (!(o instanceof PackageInfo)) {
		    return -1;
		}
		return getDisplayNameIfAvailable().compareTo(((PackageInfo)o).getDisplayNameIfAvailable());
	}

    public PackageInfo(File propertiesFile) throws MalformedURLException, IOException {
	    infos = new PropertyResourceBundle(propertiesFile.toURL().openStream());
    }

	public String getName() {
		return infos.getString(packageKey).trim();
	}

	public String getShortName() {
		return infos.getString(shortnameKey).trim();
	}

	public String getDisplayName() {
		return infos.getString(nameKey).trim();
	}

	public String getDisplayNameIfAvailable() {
		if (getDisplayName().equals("")) {
			return getName();
		} else {
			return getDisplayName();
		}
	}

	public String getBoxName() {
		return infos.getString(boxKey).trim();
	}

	public String getVersion() {
		return infos.getString(versionKey).trim();
	}

	public String getReleaseId() {
		return infos.getString(releaseKey).trim();
	}

	public String getBuildTag() {
		return infos.getString(buildKey).trim();
	}

	public String getLicenceKey() {
		return infos.getString(keyKey).trim();
	}

	public String toString() {
		String _displayName = getName();
		if (!getDisplayName().equals("")) {
			_displayName += "|"+getDisplayName();
		}
		_displayName += " "+getVersion();
		if (!getBoxName().equals("")) {
			_displayName += " ("+getBoxName()+")";
		}
		return
			_displayName
			+" build:"+getBuildTag()
			+" release:"+getReleaseId();
	}


	public static String specialName(String source) {

		String s = sReplace(" ", "", source);
		s = sReplace("&", "", s);
		s = sReplace("'", "", s);

		return s;
	}

	private static String sReplace(String search, String replace, String source) {

		int spot;
		String returnString;
		String origSource = new String(source);

		spot = source.indexOf(search);
		if (spot > -1) returnString = "";
		else returnString = source;
		while (spot > -1) {
			if (spot == source.length() + 1) {
				returnString = returnString.concat(source.substring(0, source.length() - 1).concat(replace));
				source = "";
			}
			else if (spot > 0) {
				returnString = returnString.concat(source.substring(0, spot).concat(replace));
				source = source.substring(spot + search.length(), source.length());
			}
			else {
				returnString = returnString.concat(replace);
				source = source.substring(spot + search.length(), source.length());
			}
			spot = source.indexOf(search);
		}
		if (! source.equals(origSource)) {
			return returnString.concat(source);
		}
		else {
			return returnString;
		}
	}


}