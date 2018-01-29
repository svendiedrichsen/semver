package org.semver.parts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuildMetadata implements Serializable {

    private List<VersionPart> parts;

    public BuildMetadata(VersionPart... segments) {
        parts = new ArrayList<>(Arrays.asList(segments));
    }

    public int size() {
        return parts.size();
    }

    @Override
    public String toString() {
        return VersionUtil.toString(parts);
    }

    public static class Builder {

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
