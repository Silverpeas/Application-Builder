/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silverpeas.applicationbuilder.maven;

import java.util.Collections;
import java.util.List;
import java.io.File;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ehugonnet
 */
public class FileComparatorTest {

  public FileComparatorTest() {
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  /**
   * Test of compare method, of class FileComparator.
   */
  @Test
  public void testCompareWithNullFiles() {
    File file1 = null;
    File file2 = null;
    FileComparator instance = new FileComparator();
    int expResult = 0;
    int result = instance.compare(file1, file2);
    assertEquals(expResult, result);
    file2 = new File("toto.txt");
    result = instance.compare(file1, file2);
    expResult = -10;
    assertEquals(expResult, result);
    file1 = new File("toto.txt");
    file2 = null;
    result = instance.compare(file1, file2);
    expResult = 10;
    assertEquals(expResult, result);
  }



  @Test
  public void testSortList() {
    List<File> files = new ArrayList<File>(5);
    File file1 = new File("Antoine.txt");
    files.add(file1);
    File file2 = new File("antoine.txt");
    files.add(file2);
    File file3 = new File("Xavier.txt");
    files.add(file3);
    File file4 = new File("benoit.txt");
    files.add(file4);
    FileComparator comparator = new FileComparator();
    Collections.sort(files, comparator);
    assertEquals("Antoine.txt", files.get(0).getName());
    assertEquals("Xavier.txt", files.get(1).getName());
    assertEquals("antoine.txt", files.get(2).getName());
    assertEquals("benoit.txt", files.get(3).getName());
    
  }
}
