package de.semver;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author sdiedrichsen
 * @version $Id$
 * @since 31.01.17
 */
public class VersionComparableTest {

    @DataProvider(name = "versions")
    public Object[][] createVersions() {
        return new Object[][]{
                {
                        new Version(new MajorVersion(2), new MinorVersion(3), new PatchVersion(1), null, null),
                        new Version(new MajorVersion(2), new MinorVersion(3), new PatchVersion(1), null, null),
                        0
                },
                {
                        new Version(new MajorVersion(2), new MinorVersion(3), new PatchVersion(2), null, null),
                        new Version(new MajorVersion(2), new MinorVersion(3), new PatchVersion(1), null, null),
                        1
                },
                {
                        new Version(new MajorVersion(2), new MinorVersion(3), new PatchVersion(1), null, null),
                        new Version(new MajorVersion(2), new MinorVersion(3), new PatchVersion(2), null, null),
                        -1
                },
                {
                        new Version(new MajorVersion(2), new MinorVersion(2), new PatchVersion(2), null, null),
                        new Version(new MajorVersion(2), new MinorVersion(3), new PatchVersion(2), null, null),
                        -1
                },
                {
                        new Version(new MajorVersion(2), new MinorVersion(3), new PatchVersion(2), null, null),
                        new Version(new MajorVersion(2), new MinorVersion(2), new PatchVersion(2), null, null),
                        1
                },
                {
                        new Version(new MajorVersion(1), new MinorVersion(3), new PatchVersion(2), null, null),
                        new Version(new MajorVersion(2), new MinorVersion(3), new PatchVersion(2), null, null),
                        -1
                },
                {
                        new Version(new MajorVersion(2), new MinorVersion(3), new PatchVersion(2), null, null),
                        new Version(new MajorVersion(1), new MinorVersion(3), new PatchVersion(2), null, null),
                        1
                }
        };
    }

    @Test(dataProvider = "versions")
    public void testVersionComparable(Version a, Version b, int comparison) throws Exception {
        assertEquals(a.compareTo(b), comparison, "Comparison of version " + a + " with " + b + " is wrong.");
    }

}