package de.semver;

import java.util.Arrays;
import java.util.List;

public abstract class VersionUtil {

    private static final String SEGMENT_SEPARATOR_REGEX = "\\.";
    private static final String SEGMENT_SEPARATOR = ".";

    public static List<VersionPart> toVersionParts(String version) {
        String[] strParts = version.contains(SEGMENT_SEPARATOR)
                ? split(version)
                : new String[]{version};
        return Arrays.asList(toVersionParts(strParts));
    }

    public static VersionPart[] toVersionParts(String... strSegments) {
        VersionPart[] parts = new VersionPart[strSegments.length];
        for (int i = 0; i < strSegments.length; i++) {
            parts[i] = VersionUtil.isNumerical(strSegments[i])
                    ? new NumericalVersionPart(Long.valueOf(strSegments[i]))
                    : new AlphaNumericalVersionPart(strSegments[i]);
        }
        return parts;
    }

    public static String[] split(String version) {
        return version.split(SEGMENT_SEPARATOR_REGEX);
    }

    private static boolean isNumerical(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static String toString(List<VersionPart> parts) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < parts.size(); i++) {
            builder.append(parts.get(i).getValue());
            if (i < parts.size() - 1) {
                builder.append(SEGMENT_SEPARATOR);
            }
        }
        return builder.toString();
    }

}
