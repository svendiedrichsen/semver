package de.semver.parts;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AlphaNumericalVersionPartTest {

    @Test
    public void testGetValue() throws Exception {
        final String expectedVersionPart = "alpha";
        AlphaNumericalVersionPart objectUnderTest = new AlphaNumericalVersionPart("alpha");
        String actualVersionPart = objectUnderTest.getValue();
        Assert.assertEquals(actualVersionPart, expectedVersionPart, "Value result is wrong.");
    }

    @Test
    public void testToString() throws Exception {
        final String expectedToStringValue = "RC1";
        AlphaNumericalVersionPart objectUnderTest = new AlphaNumericalVersionPart("RC1");
        String actualToStringValue = objectUnderTest.toString();
        Assert.assertEquals(expectedToStringValue, actualToStringValue, "ToString result is wrong.");
    }

    @Test(dataProvider = "comparisonData")
    public void testCompareTo(VersionPart o, VersionPart otherValue, int expectedComparisonResult) throws Exception {
        AlphaNumericalVersionPart objectUnderTest = new AlphaNumericalVersionPart(o.toString());
        int actualComparisonResult = objectUnderTest.compareTo(otherValue);
        Assert.assertEquals(actualComparisonResult, expectedComparisonResult, "Comparison result is wrong.");
    }

    @DataProvider(name = "comparisonData")
    public Object[][] createComparisonData() {
        return new Object[][]{
                { new AlphaNumericalVersionPart("alpha"), new NumericalVersionPart(23), 1},
                { new AlphaNumericalVersionPart("alpha"), new AlphaNumericalVersionPart("beta"), -1},
                { new AlphaNumericalVersionPart("alpha"), new AlphaNumericalVersionPart("alpha"), 0},
                { new AlphaNumericalVersionPart("beta"), new AlphaNumericalVersionPart("alpha"), 1},
        };
    }

}