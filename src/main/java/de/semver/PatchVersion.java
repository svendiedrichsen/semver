package de.semver;

class PatchVersion extends VersionPart {

    PatchVersion() {
        this(0);
    }

    PatchVersion(long value) {
        super(value);
    }

}
