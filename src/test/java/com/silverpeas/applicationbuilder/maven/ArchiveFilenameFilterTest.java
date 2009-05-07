/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.silverpeas.applicationbuilder.maven.ArchiveFilenameFilter;
import junit.framework.TestCase;

/**
 *
 * @author Administrateur
 */
public class ArchiveFilenameFilterTest extends TestCase {
    
    public ArchiveFilenameFilterTest(String testName) {
        super(testName);
    }

    public void testAccept() throws Exception {
      ArchiveFilenameFilter filter = new ArchiveFilenameFilter();
      assertTrue(filter.accept(null, "toto.war"));
      assertTrue(filter.accept(null, "toto.WAR"));
      assertTrue(filter.accept(null, "toto.War"));
      assertTrue(filter.accept(null, "toto.wAr"));
      assertTrue(filter.accept(null, "toto.waR"));
      assertTrue(filter.accept(null, "toto.jar"));
      assertTrue(filter.accept(null, "toto.csqdf.WAR"));

      assertFalse(filter.accept(null, "toto.waj"));
      assertFalse(filter.accept(null, "toto.war.titi"));

    }

}
