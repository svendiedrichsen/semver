package de.semver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BuildMetadata implements Serializable {

    private List<VersionPart> parts;

    BuildMetadata(VersionPart... segments) {
        parts = new ArrayList<>(Arrays.asList(segments));
    }

    public int size() {
        return parts.size();
    }

    @Override
    public String toString() {
        return VersionUtil.toString(parts);
    }

    static class Builder {

        private List<VersionPart> parts = new ArrayList<>();

        public Builder add(String version) {
            parts.addAll(VersionUtil.toVersionParts(version));
            return this;
        }

        public BuildMetadata build() {
            return new BuildMetadata(parts.toArray(new VersionPart[parts.size()]));
        }

    }


}
