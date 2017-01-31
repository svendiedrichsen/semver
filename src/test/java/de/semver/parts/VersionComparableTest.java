package de.semver.parts;

import de.semver.Version;
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
                        new Version.Builder(2, 3, 1).build(),
                        new Version.Builder(2, 3, 1).build(),
                        0
                },
                {
                        new Version.Builder(2, 3, 2).build(),
                        new Version.Builder(2, 3, 1).build(),
                        1
                },
                {
                        new Version.Builder(2, 3, 1).build(),
                        new Version.Builder(2, 3, 2).build(),
                        -1
                },
                {
                        new Version.Builder(2, 2, 2).build(),
                        new Version.Builder(2, 3, 2).build(),
                        -1
                },
                {
                        new Version.Builder(2, 3, 2).build(),
                        new Version.Builder(2, 2, 2).build(),
                        1
                },
                {
                        new Version.Builder(1, 3, 2).build(),
                        new Version.Builder(2, 3, 2).build(),
                        -1
                },
                {
                        new Version.Builder(2, 3, 2).build(),
                        new Version.Builder(1, 3, 2).build(),
                        1
                },
                {
                        new Version.Builder(1, 0, 0).preRelease("alpha").build(),
                        new Version.Builder(1, 0, 0).preRelease("alpha.1").build(),
                        -1
                },
                {
                        new Version.Builder(1, 0, 0).preRelease("alpha.1").build(),
                        new Version.Builder(1, 0, 0).preRelease("alpha.beta").build(),
                        -1
                },
                {
                        new Version.Builder(1, 0, 0).preRelease("alpha.beta").build(),
                        new Version.Builder(1, 0, 0).preRelease("beta").build(),
                        -1
                },
                {
                        new Version.Builder(1, 0, 0).preRelease("beta").build(),
                        new Version.Builder(1, 0, 0).preRelease("beta.2").build(),
                        -1
                },
                {
                        new Version.Builder(1, 0, 0).preRelease("beta.2").build(),
                        new Version.Builder(1, 0, 0).preRelease("beta.11").build(),
                        -1
                },
                {
                        new Version.Builder(1, 0, 0).preRelease("beta.11").build(),
                        new Version.Builder(1, 0, 0).preRelease("rc.1").build(),
                        -1
                },
                {
                        new Version.Builder(1, 0, 0).preRelease("rc.1").build(),
                        new Version.Builder(1, 0, 0).build(),
                        -1
                }
        };
    }

    @Test(dataProvider = "versions")
    public void testVersionComparable(Version a, Version b, int comparison) throws Exception {
        assertEquals(a.compareTo(b), comparison, "Comparison of version " + a + " with " + b + " is wrong.");
    }

}