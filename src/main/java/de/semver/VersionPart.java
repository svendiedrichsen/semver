package de.semver;

import java.io.Serializable;

interface VersionPart<T, U> extends Comparable<T>, Serializable {
    U getValue();
}
