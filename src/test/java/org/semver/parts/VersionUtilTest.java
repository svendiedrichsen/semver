package org.semver.parts;

import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertEquals;

public class VersionUtilTest {

    @Test
    public void testSplitSingleSegment() throws Exception {
        assertEquals(VersionUtil.split("123"), new String[]{"123"}, "Wrong split of single segment version string.");
    }

    @Test
    public void testSplitMultipleSegment() throws Exception {
        assertEquals(VersionUtil.split("123.RC"), new String[]{"123", "RC"}, "Wrong split of multiple segment version string.");
    }

    @Test
    public void testToVersionPartSingleSegment() throws Exception {
        assertEquals(VersionUtil.toVersionParts("123"), Arrays.asList(new NumericalVersionPart(123)), "Wrong split of single segment version string.");
    }

    @Test
    public void testToVersionPartMultipleSegment() throws Exception {
        assertEquals(VersionUtil.toVersionParts("123.RC"), Arrays.asList(new NumericalVersionPart(123), new AlphaNumericalVersionPart("RC")), "Wrong split of multiple segment version string.");
    }

}