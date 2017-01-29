package de.semver;

class MajorVersion extends NumericalVersionPart {

    MajorVersion() {
        this(0);
    }

    MajorVersion(long value) {
        super(value);
    }

    MajorVersion inc(long value) {
        return new MajorVersion(this.getValue() + value);
    }

}
