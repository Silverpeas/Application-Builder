/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.silverpeas.applicationbuilder.maven;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;

/**
 * @author ehugonnet
 */
public class FileComparator implements Serializable, Comparator<File> {

  private static final long serialVersionUID = 1l;

  @Override
  public int compare(final File file1, final File file2) {
    int result;
    if (file1 == null && file2 == null) {
      result = 0;
    } else if (file1 == null) {
      result = -10;
    } else if (file2 == null) {
      result = 10;
    } else if (file1.equals(file2)) {
      result = 0;
    } else {
      result = file1.getName().compareTo(file2.getName());
    }
    return result;
  }

}
