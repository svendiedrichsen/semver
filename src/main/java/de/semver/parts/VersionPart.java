package de.semver.parts;

import java.io.Serializable;

interface VersionPart<U> extends Comparable<VersionPart<U>>, Serializable {
    U getValue();
}
