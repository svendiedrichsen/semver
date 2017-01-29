package de.semver;

class MinorVersion extends NumericalVersionPart implements Compatible<MinorVersion> {

    MinorVersion(){
        this(1);
    }

    MinorVersion(long value) {
        super(value);
    }

    MinorVersion inc(long value) {
        return new MinorVersion(this.getValue() + value);
    }

    @Override
    public boolean isCompatibleWith(MinorVersion other) {
        return getValue() <= other.getValue();
    }
}
