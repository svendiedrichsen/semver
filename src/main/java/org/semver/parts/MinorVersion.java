package org.semver.parts;

import org.semver.Compatible;

public class MinorVersion extends NumericalVersionPart implements Compatible<MinorVersion> {

    public MinorVersion() {
        this(1);
    }

    public MinorVersion(long value) {
        super(value);
    }

    public MinorVersion inc(long value) {
        return new MinorVersion(this.getValue() + value);
    }

    @Override
    public boolean isCompatibleWith(MinorVersion other) {
        return getValue() <= other.getValue();
    }
}
