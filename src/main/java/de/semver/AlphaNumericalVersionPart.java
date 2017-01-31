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
            int result = value.compareTo((String) otherValue);
            if (result < 0) {
                result = -1;
            } else if (result > 0) {
                result = 1;
            }
            return result;
        }
        throw new IllegalArgumentException("Cannot compare " + o.getClass().getSimpleName() + " value with " + this.getClass().getSimpleName() + " value.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlphaNumericalVersionPart)) return false;

        AlphaNumericalVersionPart that = (AlphaNumericalVersionPart) o;

        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
