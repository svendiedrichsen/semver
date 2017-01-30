package de.semver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PreReleaseVersion implements Serializable, Comparable<PreReleaseVersion> {

    private List<VersionPart> parts;

    PreReleaseVersion(VersionPart... segments) {
        parts = new ArrayList<>(Arrays.asList(segments));
    }

    public int size() {
        return parts.size();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < parts.size(); i++) {
            builder.append(parts.get(i).getValue());
            if (i < parts.size() - 1) {
                builder.append(".");
            }
        }
        return builder.toString();
    }

    @Override
    public int compareTo(PreReleaseVersion o) {
        int result = 0;
        int minNoParts = Math.min(size(), o.size());
        for (int i = 0; i < minNoParts && result == 0; i++) {
            result = parts.get(i).compareTo(o.parts.get(i));
        }
        if (result == 0 && parts.size() != o.parts.size()) {
            result = parts.size() > o.parts.size() ? 1 : -1;
        }
        return result;
    }
}
