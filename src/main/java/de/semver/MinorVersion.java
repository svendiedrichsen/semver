package de.semver;

class MinorVersion extends VersionPart {

    MinorVersion(){
        this(1);
    }

    MinorVersion(long value) {
        super(value);
    }

    MinorVersion inc(long value) {
        return new MinorVersion(this.getValue() + value);
    }

}
