package de.semver;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Tests version parsing of strings.
 *
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
                {"3.2.1-BETA-4.1+12345.23-3"},
        };
    }

    @Test(dataProvider = "versions")
    public void returnsSameVersionAfterParsing(String version) {
        final Version parsed = new Version.Builder(version).build();
        assertEquals(parsed.toString(), version, "Parser returned wrong version.");
    }

}