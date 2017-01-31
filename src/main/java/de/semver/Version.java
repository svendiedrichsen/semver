package de.semver;

import java.io.Serializable;

/**
 * This class represents a semantic version. It conforms with version 2.0.0 of the
 * semantic version spec http://semver.org/ .
 *
 * Instances of this class are immutable and thus thread-safe.
 *
 * @author sdiedrichsen
 * @version $Id$
 * @since 25.01.17
 */
public class Version implements Serializable, Compatible<Version>, Comparable<Version> {

    private static final String SEGMENT_SEPARATOR = ".";
    private static final String PRERELEASE_SEPARATOR = "-";
    private static final String METADATA_SEPARATOR = "+";

    private MajorVersion majorVersion;
    private MinorVersion minorVersion;
    private PatchVersion patchVersion;
    private PreReleaseVersion preReleaseVersion;
    private BuildMetadata buildMetadata;

    public Version() {
        this(new MajorVersion(), new MinorVersion(), new PatchVersion(), null, null);
    }

    public Version(MajorVersion majorVersion, MinorVersion minorVersion, PatchVersion patchVersion, PreReleaseVersion preReleaseVersion, BuildMetadata buildMetadata) {
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
        this.patchVersion = patchVersion;
        this.preReleaseVersion = preReleaseVersion;
        this.buildMetadata = buildMetadata;
    }

    public Version major(long value) {
        return new Version(new MajorVersion(value), minorVersion, patchVersion, preReleaseVersion, buildMetadata);
    }

    public Version minor(long value) {
        return new Version(majorVersion, new MinorVersion(value), patchVersion, preReleaseVersion, buildMetadata);
    }

    public Version patch(long value) {
        return new Version(majorVersion, minorVersion, new PatchVersion(value), preReleaseVersion, buildMetadata);
    }

    public Version nextMajor() {
        return incMajor(1);
    }

    public Version incMajor(long value) {
        return new Version(majorVersion.inc(value), new MinorVersion(0), new PatchVersion(0), null, null);
    }

    public Version nextMinor() {
        return incMinor(1);
    }

    public Version incMinor(long value) {
        return new Version(majorVersion, minorVersion.inc(value), new PatchVersion(0), null, null);
    }

    public Version nextPatch() {
        return incPatch(1);
    }

    public Version incPatch(long value) {
        return new Version(majorVersion, minorVersion, patchVersion.inc(value), null, null);
    }

    @Override
    public String toString() {
        return majorVersion.toString()
                + SEGMENT_SEPARATOR + minorVersion
                + SEGMENT_SEPARATOR + patchVersion
                + ( preReleaseVersion != null ? PRERELEASE_SEPARATOR + preReleaseVersion : "" )
                + ( buildMetadata != null ? METADATA_SEPARATOR + buildMetadata : "");
    }

    @Override
    public boolean isCompatibleWith(Version other) {
        return majorVersion.isCompatibleWith(other.majorVersion)
                && minorVersion.isCompatibleWith(other.minorVersion)
                && patchVersion.isCompatibleWith(other.patchVersion);
    }

    @Override
    public int compareTo(Version o) {
        int result = majorVersion.compareTo(o.majorVersion);
        if (result == 0) {
            result = minorVersion.compareTo(o.minorVersion);
        }
        if (result == 0) {
            result = patchVersion.compareTo(o.patchVersion);
        }
        if (result == 0) {
            result = comparePreReleaseVersions(preReleaseVersion, o.preReleaseVersion);
        }
        return result;
    }

    private static int comparePreReleaseVersions(PreReleaseVersion current, PreReleaseVersion other) {
        int result = 0;
        if (current != null && other != null) {
            result = current.compareTo(other);
            if (result == 0) {
                result = Integer.valueOf(current.size()).compareTo(other.size());
            }
        } else {
            if (current == null && other != null) {
                result = 1;
            } else if (current != null && other == null) {
                result = -1;
            }
        }
        return result;
    }

}
