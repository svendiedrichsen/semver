package de.semver;

import java.io.Serializable;

/**
 * @author sdiedrichsen
 * @version $Id$
 * @since 25.01.17
 */
public class Version implements Serializable {

    private MajorVersion majorVersion;
    private MinorVersion minorVersion;
    private PatchVersion patchVersion;

    public Version(){
        this(new MajorVersion(), new MinorVersion(), new PatchVersion());
    }

    private Version(MajorVersion majorVersion, MinorVersion minorVersion, PatchVersion patchVersion){
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
        this.patchVersion = patchVersion;
    }

    public Version major(long value){
        return new Version(new MajorVersion(value), minorVersion, patchVersion);
    }

    public Version minor(long value) {
        return new Version(majorVersion, new MinorVersion(value), patchVersion);
    }

    public Version patch(long value) {
        return new Version(majorVersion, minorVersion, new PatchVersion(value));
    }

    @Override
    public String toString() {
        return majorVersion.toString() + "." + minorVersion.toString() + "." + patchVersion.toString();
    }

}
