package org.semver.parts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreReleaseVersion implements Serializable, Comparable<PreReleaseVersion> {

    private List<VersionPart> parts;

    public PreReleaseVersion(VersionPart... segments) {
        parts = new ArrayList<>(Arrays.asList(segments));
    }

    public int size() {
        return parts.size();
    }

    @Override
    public String toString() {
        return VersionUtil.toString(parts);
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

    public static class Builder {

        private List<VersionPart> parts = new ArrayList<>();

        public Builder add(String version) {
            parts.addAll(VersionUtil.toVersionParts(version));
            return this;
        }

        public PreReleaseVersion build() {
            return new PreReleaseVersion(parts.toArray(new VersionPart[parts.size()]));
        }

    }
}
