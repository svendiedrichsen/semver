package de.semver;

import java.io.Serializable;

abstract class NumericalVersionPart implements Serializable {

    private long value;

    protected NumericalVersionPart(long value) {
        this.value = value;
    }

    public long getValue(){
        return value;
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }

}
