/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silverpeas.applicationbuilder.maven;

import com.silverpeas.applicationbuilder.AppBuilderException;
import com.silverpeas.installedtree.DirectoryLocator;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Administrateur
 */
public class MavenRepository {

  private List<MavenContribution> contributions = new LinkedList<MavenContribution>();

  public MavenRepository() throws AppBuilderException {
    init();
  }

  protected void init() throws AppBuilderException {
    loadClients();
    loadEjbs();
    loadLibrairies();
    loadWarParts();
  }

  protected void loadClients() throws AppBuilderException {
    File[] archives = listArchivesInDirectory(DirectoryLocator.getClientContribHome());
    for (File archive : archives) {
      contributions.add(new MavenContribution(new File[]{archive}, MavenContribution.TYPE_CLIENT));
    }
  }

  protected void loadWarParts() throws AppBuilderException {
    File[] archives = listArchivesInDirectory(DirectoryLocator.getWarContribHome());
    for (File archive : archives) {
      contributions.add(new MavenContribution(new File[]{archive}, MavenContribution.TYPE_WAR));
    }
  }

  protected void loadEjbs() throws AppBuilderException {
    File[] archives = listArchivesInDirectory(DirectoryLocator.getEjbContribHome());
    contributions.add(new MavenContribution(archives, MavenContribution.TYPE_EJB));
  }

  protected void loadLibrairies() throws AppBuilderException {
    File[] archives = listArchivesInDirectory(DirectoryLocator.getLibContribHome());
    contributions.add(new MavenContribution(archives, MavenContribution.TYPE_LIB));
  }

  protected File[] listArchivesInDirectory(String directoryPath) {
    File directory = new File(directoryPath);
    if (directory.exists() && directory.isDirectory()) {
      return directory.listFiles(new ArchiveFilenameFilter());
    }
    return new File[0];
  }

  public MavenContribution[] getContributions() {
    return contributions.toArray(new MavenContribution[contributions.size()]);
  }
}
