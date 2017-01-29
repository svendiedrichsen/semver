package de.semver;

class PatchVersion extends NumericalVersionPart implements Compatible<PatchVersion> {

    PatchVersion() {
        this(0);
    }

    PatchVersion(long value) {
        super(value);
    }

    PatchVersion inc(long value) {
        return new PatchVersion(this.getValue() + value);
    }

    @Override
    public boolean isCompatibleWith(PatchVersion other) {
        return true;
    }

}
