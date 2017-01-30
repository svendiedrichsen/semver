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
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < parts.size(); i++) {
            builder.append(parts.get(i).getValue());
            if (i < parts.size() - 1) {
                builder.append(".");
            }
        }
        return builder.toString();
    }

}
