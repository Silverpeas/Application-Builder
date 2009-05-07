/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.silverpeas.applicationbuilder.maven;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 *
 * @author Administrateur
 */
public class ArchiveFilenameFilter implements FilenameFilter {
  protected static final Pattern ARCHIVE_PATTERN = Pattern.compile(".*\\..[aA][rR]");

  @Override
  public boolean accept(File dir, String name) {
    return ARCHIVE_PATTERN.matcher(name).matches();
  }

}
