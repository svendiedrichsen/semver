package de.semver;

import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import static org.testng.Assert.*;

public class VersionTest {

    @Test
    public void testVersionSerializable() throws Exception {
        try(ObjectOutputStream out = new ObjectOutputStream(new ByteArrayOutputStream())){
            out.writeObject(new Version());
        }
    }

    @Test
    public void testInitialVersionString() throws Exception {
        assertEquals(new Version().toString(), "0.1.0", "Found wrong initial version.");
    }

    @Test
    public void testImmutabilityMajor() throws Exception {
        Version v1 = new Version();
        Version v2 = v1.major(1);
        assertEquals(v2.toString(), "1.1.0", "Found wrong version after setting major version.");
        assertNotSame(v1, v2, "After setting major version the version instance is the same.");
    }

    @Test
    public void testImmutabilityMinor() throws Exception {
        Version v1 = new Version();
        Version v2 = v1.minor(2);
        assertEquals(v2.toString(), "0.2.0", "Found wrong version after setting minor version.");
        assertNotSame(v1, v2, "After setting minor version the version instance is the same.");
    }

    @Test
    public void testImmutabilityPatch() throws Exception {
        Version v1 = new Version();
        Version v2 = v1.patch(1);
        assertEquals(v2.toString(), "0.1.1", "Found wrong version after setting patch version.");
        assertNotSame(v1, v2, "After setting patch version the version instance is the same.");
    }

}