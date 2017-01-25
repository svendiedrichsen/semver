package de.semver;

import java.io.Serializable;

/**
 * @author sdiedrichsen
 * @version $Id$
 * @since 25.01.17
 */
abstract class VersionPart implements Serializable {

    private long value;

    protected VersionPart(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }

}
