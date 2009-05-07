//Source file: R:\\StraProduct\\Pkg1.0\\Dev\\SrcJava\\Java\\ApplicationBuilder\\JBuilderEnv\\src\\com\\silverpeas\\applicationbuilder\\Client.java

package com.silverpeas.applicationbuilder;

import java.io.File;

/**
 * Represent the archive containing the client part of the application. It is
 * filled with parts from contributions. The filling can be achieved on the fly by
 * the means of streams.
 * @author Silverpeas
 * @version 1.0/B
 * @since 1.0/B
 */
public class Client extends WriteOnlyArchive {

	/**
	 * The name of the client archive to build
	 * @since 1.0
	 */
	private static final String NAME = "silverpeas-client.jar";

	public Client(File directory) throws AppBuilderException {
		super(directory, NAME);
	}

	/**
	 * @roseuid 3AAE3D800345
	 */
	public void mergeClientPart(ReadOnlyArchive clientPart) throws AppBuilderException {
		mergeWith(clientPart, MANIFEST_PATH);
	}

	/**
	 * @roseuid 3AAE3D800345
	 */
	public void mergeLibraries(ReadOnlyArchive[] libraries) throws AppBuilderException {
		for (int iLib=0 ; iLib<libraries.length ; iLib++) {
			try {
				mergeWith(libraries[iLib], MANIFEST_PATH);
			} catch (AppBuilderException abe) {
				Log.add("Could not merge library \""+libraries[iLib].getName()+"\"");
				throw abe;
			}
		}
	}
}
