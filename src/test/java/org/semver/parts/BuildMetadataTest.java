package org.semver.parts;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class BuildMetadataTest {

    @Test
    public void testSize() throws Exception {
        final int expectedSize = 2;
        BuildMetadata objectUnderTest = new BuildMetadata(mock(VersionPart.class), mock(VersionPart.class));
        int actualSize = objectUnderTest.size();
        Assert.assertEquals(actualSize, expectedSize, "Size result is wrong.");
    }

    @Test
    public void testToString() throws Exception {
        final String expectedToStringValue = "identifiers1.identifiers2";
        VersionPart versionPart1Mock = mock(VersionPart.class);
        doReturn("identifiers1").when(versionPart1Mock).getValue();
        VersionPart versionPart2Mock = mock(VersionPart.class);
        doReturn("identifiers2").when(versionPart2Mock).getValue();
        BuildMetadata objectUnderTest = new BuildMetadata(versionPart1Mock, versionPart2Mock);
        String actualToStringValue = objectUnderTest.toString();
        Assert.assertEquals(actualToStringValue, expectedToStringValue, "ToString result is wrong.");
        verify(versionPart1Mock, times(1)).getValue();
        verify(versionPart2Mock, times(1)).getValue();
    }
}