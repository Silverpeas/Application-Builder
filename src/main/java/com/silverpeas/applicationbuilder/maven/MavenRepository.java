/**
 * Copyright (C) 2000 - 2009 Silverpeas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * As a special exception to the terms and conditions of version 3.0 of
 * the GPL, you may redistribute this Program in connection with Free/Libre
 * Open Source Software ("FLOSS") applications as described in Silverpeas's
 * FLOSS exception.  You should have recieved a copy of the text describing
 * the FLOSS exception, and it is also available here:
 * "http://repository.silverpeas.com/legal/licensing"
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
