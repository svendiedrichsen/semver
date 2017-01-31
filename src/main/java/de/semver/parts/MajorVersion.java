package de.semver.parts;

import de.semver.Compatible;

public class MajorVersion extends NumericalVersionPart implements Compatible<MajorVersion> {

    public MajorVersion() {
        this(0);
    }

    public MajorVersion(long value) {
        super(value);
    }

    public MajorVersion inc(long value) {
        return new MajorVersion(this.getValue() + value);
    }

    @Override
    public boolean isCompatibleWith(MajorVersion other) {
        return getValue() == other.getValue();
    }
}
