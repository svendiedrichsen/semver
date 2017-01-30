package de.semver;

/**
 * Parses string version representation to a {@link Version} instance.
 *
 * @author sdiedrichsen
 * @version $Id$
 * @since 29.01.17
 */
public class VersionParser {

    private static final String SEGMENT_SEPARATOR = "\\.";
    private static final String PRERELEASE_SEPARATOR = "-";
    private static final String METADATA_SEPARATOR = "+";

    private String version;

    public VersionParser(String version) {
        this.version = version;
    }

    public Version parse() {
        final PreReleaseVersion preReleaseVersion = getPreReleasePart();
        final BuildMetadata buildMetadata = getMetadataPart();
        final String[] versionInfo = getVersionInfo();
        return new Version(
                new MajorVersion(Long.valueOf(versionInfo[0])),
                new MinorVersion(Long.valueOf(versionInfo[1])),
                new PatchVersion(Long.valueOf(versionInfo[2])),
                preReleaseVersion,
                buildMetadata);
    }

    private String[] getVersionInfo() {
        final int preReleaseIndex = getPreReleaseIndex();
        final int metadataIndex = getMetadataIndex();
        int targetIndex = -1;
        if(preReleaseIndex > -1){
            if(metadataIndex > -1){
                targetIndex = Math.min(preReleaseIndex, metadataIndex);
            } else {
                targetIndex = preReleaseIndex;
            }
        } else if(metadataIndex > -1){
            targetIndex = metadataIndex;
        }
        if(targetIndex == -1){
            return split(version);
        }
        return split(version.substring(0, targetIndex));
    }

    private PreReleaseVersion getPreReleasePart() {
        final int preReleaseIndex = getPreReleaseIndex();
        if(preReleaseIndex > -1){
            final int metadataIndex = getMetadataIndex();
            if(metadataIndex > -1){
                return new PreReleaseVersion(toVersionParts(split(version.substring(preReleaseIndex + 1, metadataIndex))));
            }
            return new PreReleaseVersion(toVersionParts(split(version.substring(preReleaseIndex + 1))));
        }
        return null;
    }

    private BuildMetadata getMetadataPart() {
        final int metadataIndex = getMetadataIndex();
        if (metadataIndex > -1) {
            return new BuildMetadata(toVersionParts(split(version.substring(metadataIndex + 1))));
        }
        return null;
    }

    private static VersionPart[] toVersionParts(String... strSegments) {
        VersionPart[] parts = new VersionPart[strSegments.length];
        for (int i = 0; i < strSegments.length; i++) {
            parts[i] = isNumerical(strSegments[i])
                    ? new NumericalVersionPart(Long.valueOf(strSegments[i]))
                    : new AlphaNumericalVersionPart(strSegments[i]);
        }
        return parts;
    }

    private static boolean isNumerical(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private static String[] split(String value){
        return value.split(SEGMENT_SEPARATOR);
    }

    private int getMetadataIndex() {
        return version.indexOf(METADATA_SEPARATOR);
    }

    private int getPreReleaseIndex() {
        return version.indexOf(PRERELEASE_SEPARATOR);
    }


}
