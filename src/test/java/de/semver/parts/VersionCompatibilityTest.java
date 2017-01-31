package de.semver.parts;

import de.semver.Version;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class VersionCompatibilityTest {

    @DataProvider(name = "versions")
    public Object[][] createVersions() {
        return new Object[][]{
                {
                        new Version.Builder(2, 3, 1).build(),
                        new Version.Builder(2, 3, 1).build(),
                        true
                },
                {
                        new Version.Builder(2, 3, 2).build(),
                        new Version.Builder(2, 3, 1).build(),
                        true
                },
                {
                        new Version.Builder(2, 3, 1).build(),
                        new Version.Builder(2, 3, 2).build(),
                        true
                },
                {
                        new Version.Builder(2, 2, 2).build(),
                        new Version.Builder(2, 3, 2).build(),
                        true
                },
                {
                        new Version.Builder(2, 3, 2).build(),
                        new Version.Builder(2, 2, 2).build(),
                        false
                },
                {
                        new Version.Builder(1, 3, 2).build(),
                        new Version.Builder(2, 3, 2).build(),
                        false
                },
                {
                        new Version.Builder(2, 3, 2).build(),
                        new Version.Builder(1, 3, 2).build(),
                        false
                }
        };
    }

    @Test(dataProvider = "versions")
    public void testVersionCompatibility(Version a, Version b, boolean compatible) throws Exception {
        assertEquals(a.isCompatibleWith(b), compatible, "Found version " + a + " compatible with " + b + ": " + compatible);
    }

}