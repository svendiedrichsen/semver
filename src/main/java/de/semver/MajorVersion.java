package de.semver;

class MajorVersion extends NumericalVersionPart implements Compatible<MajorVersion> {

    MajorVersion() {
        this(0);
    }

    MajorVersion(long value) {
        super(value);
    }

    MajorVersion inc(long value) {
        return new MajorVersion(this.getValue() + value);
    }

    @Override
    public boolean isCompatibleWith(MajorVersion other) {
        return getValue() == other.getValue();
    }
}
