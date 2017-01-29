package de.semver;

/**
 * Parses string version representation to a {@link Version} instance.
 *
 * @author sdiedrichsen
 * @version $Id$
 * @since 29.01.17
 */
public class VersionParser {

    private static final String[] EMPTY_ARRAY = {};
    private static final String SEGMENT_SEPARATOR = "\\.";
    private static final String PRERELEASE_SEPARATOR = "-";
    private static final String METADATA_SEPARATOR = "+";

    private String version;

    public VersionParser(String version) {
        this.version = version;
    }

    public Version parse() {
        final String[] versionParts = getVersionPart();
        final String[] preReleaseParts = getPreReleasePart();
        PreReleaseVersion preReleaseVersion = preReleaseParts.length > 0 ? new PreReleaseVersion(preReleaseParts) : null;
        final String[] metadataParts = getMetadataPart();
        BuildMetadata buildMetadata = metadataParts.length > 0 ? new BuildMetadata(metadataParts) : null;
        return new Version(
                new MajorVersion(Long.valueOf(versionParts[0])),
                new MinorVersion(Long.valueOf(versionParts[1])),
                new PatchVersion(Long.valueOf(versionParts[2])),
                preReleaseVersion, buildMetadata
        );
    }

    private String[] getVersionPart() {
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

    private String[] getPreReleasePart() {
        final int preReleaseIndex = getPreReleaseIndex();
        if(preReleaseIndex > -1){
            final int metadataIndex = getMetadataIndex();
            if(metadataIndex > -1){
                return split(version.substring(preReleaseIndex + 1, metadataIndex));
            }
            return split(version.substring(preReleaseIndex + 1));
        }
        return EMPTY_ARRAY;
    }

    private String[] getMetadataPart() {
        final int metadataIndex = getMetadataIndex();
        if (metadataIndex > -1) {
            return split(version.substring(metadataIndex + 1));
        }
        return EMPTY_ARRAY;
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
