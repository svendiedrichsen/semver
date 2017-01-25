package de.semver;

class MinorVersion extends VersionPart {

    MinorVersion(){
        this(1);
    }

    MinorVersion(long value) {
        super(value);
    }

}
