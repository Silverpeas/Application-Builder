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

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import org.jdom.Content;
import org.jdom.Element;

/**
 * @author ehsavoie
 */
public class WarElementComparator implements Comparator<Content> {

  private final static Map<String, Integer> servletTags = new HashMap<String, Integer>();

  static {
    servletTags.put("display-name", 3);
    servletTags.put("servlet-name", 2);
    servletTags.put("servlet-class", 1);
  }

  @Override
  public int compare(Content content1, Content content2) {
    if (content1 instanceof Element) {
      if (content2 instanceof Element) {
        return compare((Element) content1, (Element) content2);
      }
      return 1;
    }
    return -1;
  }

  public int compare(Element content1, Element content2) {
    String name1 = content1.getName();
    int value1 = 0;
    if (servletTags.containsKey(name1)) {
      value1 = servletTags.get(name1);
    }
    String name2 = content1.getName();
    int value2 = 0;
    if (servletTags.containsKey(name2)) {
      value2 = servletTags.get(name2);
    }
    return value1 - value2;
  }
}
