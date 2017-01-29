package de.semver;

import java.io.Serializable;

class AlphaNumericalVersionPart implements Serializable {

    private String value;

    protected AlphaNumericalVersionPart(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

}
