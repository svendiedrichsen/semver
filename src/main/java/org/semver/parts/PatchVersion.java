package org.semver.parts;

import org.semver.Compatible;

public class PatchVersion extends NumericalVersionPart implements Compatible<PatchVersion> {

    public PatchVersion() {
        this(0);
    }

    public PatchVersion(long value) {
        super(value);
    }

    public PatchVersion inc(long value) {
        return new PatchVersion(this.getValue() + value);
    }

    @Override
    public boolean isCompatibleWith(PatchVersion other) {
        return true;
    }

}
