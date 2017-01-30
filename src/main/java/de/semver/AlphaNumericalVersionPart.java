package de.semver;

class AlphaNumericalVersionPart implements VersionPart<String> {

    private static final long serialVersionUID = 1L;

    private String value;

    protected AlphaNumericalVersionPart(String value){
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int compareTo(VersionPart o) {
        Object otherValue = o.getValue();
        if (otherValue instanceof Number) {
            return 1;
        } else if (otherValue instanceof String) {
            return value.compareTo((String) otherValue);
        }
        throw new IllegalArgumentException("Cannot compare " + o.getClass().getSimpleName() + " value with " + this.getClass().getSimpleName() + " value.");
    }
}
