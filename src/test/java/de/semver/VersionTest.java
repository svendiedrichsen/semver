package de.semver;

import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotSame;

public class VersionTest {

    @Test
    public void testVersionSerializable() throws Exception {
        try(ObjectOutputStream out = new ObjectOutputStream(new ByteArrayOutputStream())){
            out.writeObject(new Version.Builder().build());
        }
    }

    @Test
    public void testInitialVersionString() throws Exception {
        assertEquals(new Version.Builder().build().toString(), "0.1.0", "Found wrong initial version.");
    }

    @Test
    public void testImmutabilityMajor() throws Exception {
        Version v1 = new Version.Builder().build();
        Version v2 = v1.major(1);
        assertEquals(v2.toString(), "1.1.0", "Found wrong version after setting major version.");
        assertNotSame(v1, v2, "After setting major version the version instance is the same.");
    }

    @Test
    public void testImmutabilityMinor() throws Exception {
        Version v1 = new Version.Builder().build();
        Version v2 = v1.minor(2);
        assertEquals(v2.toString(), "0.2.0", "Found wrong version after setting minor version.");
        assertNotSame(v1, v2, "After setting minor version the version instance is the same.");
    }

    @Test
    public void testImmutabilityPatch() throws Exception {
        Version v1 = new Version.Builder().build();
        Version v2 = v1.patch(1);
        assertEquals(v2.toString(), "0.1.1", "Found wrong version after setting patch version.");
        assertNotSame(v1, v2, "After setting patch version the version instance is the same.");
    }

    @Test
    public void testNextMajorVersion() throws Exception {
        Version v1 = new Version.Builder().build().major(1).patch(1).nextMajor();
        assertEquals(v1.toString(), "2.0.0", "Found wrong version increasing major version.");
    }

    @Test
    public void testNextMinorVersion() throws Exception {
        Version v1 = new Version.Builder().build().major(1).patch(1).nextMinor();
        assertEquals(v1.toString(), "1.2.0", "Found wrong version increasing minor version.");
    }

    @Test
    public void testNextPatchVersion() throws Exception {
        Version v1 = new Version.Builder().build().major(1).patch(1).nextPatch();
        assertEquals(v1.toString(), "1.1.2", "Found wrong version increasing patch version.");
    }

}