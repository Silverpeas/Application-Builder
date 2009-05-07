//Source file: R:\\StraProduct\\Pkg1.0\\Dev\\SrcJava\\Java\\ApplicationBuilder\\JBuilderEnv\\src\\com\\silverpeas\\applicationbuilder\\WAR.java

package com.silverpeas.applicationbuilder;

import java.io.File;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * Name = "war-ic.war"
 * Handles the "war-ic.war" archive. The archive file is filled with the added
 * WARParts and finally with the built WARDescriptor. With the help of the
 * java.util.zip or java.util.jar package combined with streams, it must be
 * possible to achieve this
 * goal without uncompressing the archives.
 * @author Silverpeas
 * @version 1.0/B
 * @since 1.0/B
 */
public class WAR extends WARDirectory {

	/**
	 * The name of the presentation part archive to build and to integrate to the
	 * application archive (EAR)
	 * @since 1.0/B
	 */
	private static final String NAME = "war-ic.war";

	private WARDescriptor theWARDescriptor;

	protected static final String MANIFEST_PATH = "META-INF"+File.separator+"MANIFEST.MF";

	public WAR(File directory) throws AppBuilderException {
		super(directory, NAME);
		setWARDescriptor();
	}

    public String getName() {
      return NAME;
    }

	/**
	 * Adds the entries in the WARPart to the WAR.
	 * The descriptor entry in the WARPart is not directly added to the WAR. The
	 * WARPart descriptor is added to the WARDescriptor.
	 * When all the WARParts are added, the WARDescriptor can be added.
	 * @roseuid 3AAE3DB80074
	 */
	public void mergeWARPart(ReadOnlyArchive warPart) throws AppBuilderException {
		Set excludeSet = new HashSet(2);
		excludeSet.add(MANIFEST_PATH);
		excludeSet.add(getWARDescriptor().getArchivePath());
		mergeWith(warPart, excludeSet);
		ApplicationBuilderItem entry =
			new ApplicationBuilderItem(getWARDescriptor().getLocation(),
										getWARDescriptor().getName());
		InputStream descriptorIn = warPart.getEntry(entry);
		if (descriptorIn != null) {
			XmlDocument warPartDesc = new XmlDocument("","web.xml from "+warPart.getName());
			warPartDesc.loadFrom(descriptorIn);
			getWARDescriptor().mergeWARPartDescriptor(warPartDesc);
		}
	}

	/**
	 * When all entries have been added, call this method to close the archive
	 * @roseuid 3AB1EAFE02FD
	 */
	public void close() throws AppBuilderException {
		integrateDescriptor();
		//super.close();
	}

	public WARDescriptor getWARDescriptor() {
		return theWARDescriptor;
	}

	private void setWARDescriptor() {
		theWARDescriptor = new WARDescriptor();
	}

	/**
	 * @roseuid 3AAE3DBE00EB
	 */
	private void integrateDescriptor() throws AppBuilderException {
		getWARDescriptor().sort();
		add(getWARDescriptor());
	}

}
