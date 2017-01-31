package de.semver;

import de.semver.parts.VersionUtil;

/**
 * Parses string version representation to a {@link Version} instance.
 *
 * @author sdiedrichsen
 * @version $Id$
 * @since 29.01.17
 */
public class VersionParser {

    private static final String PRERELEASE_SEPARATOR = "-";
    private static final String METADATA_SEPARATOR = "+";

    private String version;

    public VersionParser(String version) {
        this.version = version;
    }

    public Version parse() {
        final String preReleaseVersion = getPreReleasePart();
        final String buildMetadata = getMetadataPart();
        final String[] versionInfo = getVersionInfo();
        return new Version.Builder(Long.valueOf(versionInfo[0]), Long.valueOf(versionInfo[1]),
                Long.valueOf(versionInfo[2])).preRelease(preReleaseVersion).buildMetadata(buildMetadata).build();
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
            return VersionUtil.split(version);
        }
        return VersionUtil.split(version.substring(0, targetIndex));
    }

    private String getPreReleasePart() {
        final int preReleaseIndex = getPreReleaseIndex();
        if(preReleaseIndex > -1){
            final int metadataIndex = getMetadataIndex();
            if(metadataIndex > -1){
                return version.substring(preReleaseIndex + 1, metadataIndex);
            }
            return version.substring(preReleaseIndex + 1);
        }
        return null;
    }

    private String getMetadataPart() {
        final int metadataIndex = getMetadataIndex();
        if (metadataIndex > -1) {
            return version.substring(metadataIndex + 1);
        }
        return null;
    }

    private int getMetadataIndex() {
        return version.indexOf(METADATA_SEPARATOR);
    }

    private int getPreReleaseIndex() {
        return version.indexOf(PRERELEASE_SEPARATOR);
    }


}
