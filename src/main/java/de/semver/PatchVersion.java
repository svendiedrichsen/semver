package de.semver;

class PatchVersion extends NumericalVersionPart {

    PatchVersion() {
        this(0);
    }

    PatchVersion(long value) {
        super(value);
    }

    PatchVersion inc(long value) {
        return new PatchVersion(this.getValue() + value);
    }

}
