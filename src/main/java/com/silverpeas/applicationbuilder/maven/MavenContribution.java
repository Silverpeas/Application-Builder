/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silverpeas.applicationbuilder.maven;

import com.silverpeas.applicationbuilder.AppBuilderException;
import com.silverpeas.applicationbuilder.ApplicationBuilderItem;
import com.silverpeas.applicationbuilder.ReadOnlyArchive;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrateur
 */
public class MavenContribution {

  public static final int TYPE_WAR = 0;
  public static final int TYPE_EJB = 1;
  public static final int TYPE_CLIENT = 2;
  public static final int TYPE_LIB = 3;
  private int type;
  private StringBuffer packageName = new StringBuffer();
  /** attributes */
  private ReadOnlyArchive client = null;
  private List<ApplicationBuilderItem> ejbs = new ArrayList<ApplicationBuilderItem>();
  private ReadOnlyArchive warPart = null;
  private List<ReadOnlyArchive> librairies = new ArrayList<ReadOnlyArchive>();

  public MavenContribution(File[] contributions, int type) throws AppBuilderException {
    this.type = type;
    for (File contribution : contributions) {
      packageName.append(contribution.getAbsolutePath());
      packageName.append(System.getProperty("path.separator") + ' ');
      switch (type) {
        case TYPE_WAR:
          warPart = new ReadOnlyArchive(contribution.getParentFile(), contribution.getName());
          break;
        case TYPE_EJB:
          ejbs.add(new ApplicationBuilderItem(contribution.getParentFile(), contribution.getName()));
          break;
        case TYPE_CLIENT:
          client = new ReadOnlyArchive(contribution.getParentFile(), contribution.getName());
          break;
        case TYPE_LIB:
          librairies.add(new ReadOnlyArchive(contribution.getParentFile(), contribution.getName()));
          break;
        default:
          break;
      }
    }
  }

  /**
   * If no client part is contributed, returns <code>null</code>
   *
   * @return the client archive
   * @roseuid 3AAE586D01D4
   */
  public ReadOnlyArchive getClientPart() {
    return client;
  }

  /**
   * @return the array of contributed EJB archives. <code>null</code> if none
   * @roseuid 3AAE5877025A
   */
  public ApplicationBuilderItem[] getEJBs() {
    return ejbs.toArray(new ApplicationBuilderItem[ejbs.size()]);
  }

  public ReadOnlyArchive getWARPart() {
    return warPart;
  }

  public ReadOnlyArchive[] getLibraries() {
    return librairies.toArray(new ReadOnlyArchive[librairies.size()]);
  }

  public String getPackageName() {
    return packageName.toString();
  }

  public String getPackageType() {
    switch (type) {
      case TYPE_WAR:
        return "WAR";
      case TYPE_EJB:
        return "EJB";
      case TYPE_CLIENT:
        return "CLIENT";
      case TYPE_LIB:
        return "LIBRAIRY";
      default:
        return "";
    }
  }
}
