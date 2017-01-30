package de.semver;

class AlphaNumericalVersionPart implements VersionPart<AlphaNumericalVersionPart, String> {

    private static final long serialVersionUID = 1L;

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

    @Override
    public int compareTo(AlphaNumericalVersionPart o) {
        return value.compareTo(o.getValue());
    }
}
