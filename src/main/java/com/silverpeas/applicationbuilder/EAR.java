package com.silverpeas.applicationbuilder;

import java.io.File;

import com.silverpeas.installedtree.DirectoryLocator;

/**
 * This class dispatches the contributions parts in the target structures and then
 * creates the archive.
 * @author Silverpeas
 * @version 1.0/B
 * @since 1.0/B
 */
public class EAR extends EARDirectory {

	/**
	 * The name of the application archive to build
	 * @since 1.0/B
	 */
	private static final String NAME = "silverpeas.ear";
	private static final String LIB_DIRECTORY = "lib";

	private AppDescriptor theAppDescriptor = null;
	private WAR theWAR = null;

	public EAR(File directory) throws AppBuilderException {
		super(directory, NAME);
		setWAR(this.earDir);
		setAppDescriptor();
	}

	/**
	 * @roseuid 3AAE4A2B024E
	 */
	public void addLibrary(ApplicationBuilderItem library) throws AppBuilderException {
		library.setLocation(LIB_DIRECTORY);
		add(library);
		// Adds the library's name to the application descriptor
		getAppDescriptor().setClientInfos(LIB_DIRECTORY+'/'+library.getName());
	}
	
	/**
	 * Adds a set of libraries and updates the application descriptor
	 */
	public void addLibraries(ApplicationBuilderItem[] libraries)
		throws AppBuilderException
	{
		for (int i=0 ; i<libraries.length ; i++) {
			addLibrary(libraries[i]);
		}
	}

	/**
	 * When all entries have been added, call this method to close the archive
	 * @roseuid 3AB1EAFE02FD
	 */
	public void close() throws AppBuilderException {
		// WAR
		getWAR().close();
		try {
			if (getWAR().getPath() != null && getWAR().getPath().exists() && !getWAR().getPath().delete()) {
				Log.add("WARNING : could not delete \""+getWAR().getName()+"\" from temporary space");
			}
		} catch (Exception e) {
			Log.add("WARNING : could not delete \""+getWAR().getName()+"\" from temporary space");
			Log.add(e);
		}
				
		// Application descriptor
		add(getAppDescriptor());
	}

	/**
	 * Adds a set of EJBs and updates the application descriptor
	 * @roseuid 3AAFC08C01E2
	 */
	public void addEJBs(ApplicationBuilderItem[] srcEjbs)
		throws AppBuilderException
	{
		for (int iEjb=0 ; iEjb<srcEjbs.length ; iEjb++) {
			add(srcEjbs[iEjb]);
			// Adds the EJB name to the application descriptor
			getAppDescriptor().addEJBName(srcEjbs[iEjb].getName());
		}
	}

	/**
	 * @return the WAR object
	 * @since 1.0/B
	 * @roseuid 3AAFC0FC0084
	 */
	public WAR getWAR() {
		return theWAR;
	}
	
	private void setWAR(File directory) throws AppBuilderException {
		theWAR = new WAR(directory);
	}

	/**
	 * @return the AppDescriptor object
	 * @since 1.0
	 * @roseuid 3AB10CAE03BF
	 */
	public AppDescriptor getAppDescriptor() {
		return theAppDescriptor;
	}

	private void setAppDescriptor() throws AppBuilderException {
		theAppDescriptor = new AppDescriptor();
		getAppDescriptor().setWARInfos(getWAR().getName(), ApplicationBuilder.getApplicationRoot());
	}
}