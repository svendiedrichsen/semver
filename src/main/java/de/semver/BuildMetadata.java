package de.semver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class BuildMetadata implements Serializable {

    private List<AlphaNumericalVersionPart> parts;

    BuildMetadata(String...segments) {
        parts = new ArrayList<>();
        for(String segment : segments){
            parts.add(new AlphaNumericalVersionPart(segment));
        }
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
