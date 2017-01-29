package de.semver;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author sdiedrichsen
 * @version $Id$
 * @since 29.01.17
 */
public class VersionParserTest {

    @DataProvider(name = "versions")
    public Object[][] createVersions() {
        return new Object[][]{
                {"3.2.1"},
                {"3.2.1-SNAPSHOT"},
                {"3.2.1+META.2.3"},
                {"3.2.1-BETA.1+12345.23"},
        };
    }

    @Test(dataProvider = "versions")
    public void testReturnsVersion(String v) throws Exception {
        VersionParser p = new VersionParser(v);
        final Version parsed = p.parse();
        assertEquals(parsed.toString(), v, "Parser returned wrong version.");
    }

}