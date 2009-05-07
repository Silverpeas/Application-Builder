package com.silverpeas.version;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


/**
 * Titre :        Application Builder
 * Description :
 * Copyright :    Copyright (c) 2001
 * Société :      Stratélia
 * @author Jean-Christophe Carry
 * @version 1.0
 */

public class JobBoxInfo implements Comparable {

	private static final String busBoxName = "Job'Bus & Job'Manager";

	private String name = null;
	private Set myPackages = null;

	public boolean equals(Object o) {
		if (!(o instanceof JobBoxInfo)) {
			return false;
		}
		return getName().equalsIgnoreCase(((JobBoxInfo)o).getName());
	}

	public int compareTo(Object o) {
		if (!(o instanceof JobBoxInfo)) {
		    return -1;
		}
		if (getName().equalsIgnoreCase(busBoxName)) {
			if (equals(o)) {
				return 0;
			} else {
				return -1;
			}
		}
		JobBoxInfo jbo = (JobBoxInfo)o;
		if (getName().equals("")) {
		    if (jbo.getName().equalsIgnoreCase(busBoxName)) {
				return 1;
		    } else if (equals(o)) {
				return 0;
			} else {
				return -1;
			}
		}
		return getName().compareTo(jbo.getName());
	}

    public JobBoxInfo(String _name, Map _packages) {
		setName(_name);
		setPackages(_packages);
    }

	private void setName(String _name) {
		name = _name;
	}

	private void setPackages(Map _packages) {
	    myPackages = new TreeSet();
		Iterator iPackage = _packages.values().iterator();
		PackageInfo info = null;
		while (iPackage.hasNext()) {
			info = (PackageInfo)iPackage.next();
			if (info.getBoxName().equals(getName())) {
				myPackages.add(info);
			}
		}
	}

	public String getName() {
		return name;
	}

	public Set getPackages() {
		return myPackages;
	}
}