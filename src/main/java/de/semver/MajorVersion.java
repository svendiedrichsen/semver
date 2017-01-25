package de.semver;

class MajorVersion extends VersionPart {

    MajorVersion() {
        this(0);
    }

    MajorVersion(long value) {
        super(value);
    }

}
