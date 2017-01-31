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
                        new Version(new MajorVersion(2), new MinorVersion(3), new PatchVersion(1), null, null),
                        new Version(new MajorVersion(2), new MinorVersion(3), new PatchVersion(1), null, null),
                        true
                },
                {
                        new Version(new MajorVersion(2), new MinorVersion(3), new PatchVersion(2), null, null),
                        new Version(new MajorVersion(2), new MinorVersion(3), new PatchVersion(1), null, null),
                        true
                },
                {
                        new Version(new MajorVersion(2), new MinorVersion(3), new PatchVersion(1), null, null),
                        new Version(new MajorVersion(2), new MinorVersion(3), new PatchVersion(2), null, null),
                        true
                },
                {
                        new Version(new MajorVersion(2), new MinorVersion(2), new PatchVersion(2), null, null),
                        new Version(new MajorVersion(2), new MinorVersion(3), new PatchVersion(2), null, null),
                        true
                },
                {
                        new Version(new MajorVersion(2), new MinorVersion(3), new PatchVersion(2), null, null),
                        new Version(new MajorVersion(2), new MinorVersion(2), new PatchVersion(2), null, null),
                        false
                },
                {
                        new Version(new MajorVersion(1), new MinorVersion(3), new PatchVersion(2), null, null),
                        new Version(new MajorVersion(2), new MinorVersion(3), new PatchVersion(2), null, null),
                        false
                },
                {
                        new Version(new MajorVersion(2), new MinorVersion(3), new PatchVersion(2), null, null),
                        new Version(new MajorVersion(1), new MinorVersion(3), new PatchVersion(2), null, null),
                        false
                }
        };
    }

    @Test(dataProvider = "versions")
    public void testVersionCompatibility(Version a, Version b, boolean compatible) throws Exception {
        assertEquals(a.isCompatibleWith(b), compatible, "Found version " + a + " compatible with " + b + ": " + compatible);
    }

}