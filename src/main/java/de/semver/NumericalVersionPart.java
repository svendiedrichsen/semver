package de.semver;

class NumericalVersionPart implements VersionPart<Long> {

    private static final long serialVersionUID = 1L;

    private long value;

    protected NumericalVersionPart(long value) {
        this.value = value;
    }

    @Override
    public Long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }

    @Override
    public int compareTo(VersionPart o) {
        Object otherValue = o.getValue();
        if (otherValue instanceof Number) {
            return Long.valueOf(value).compareTo(((Number) otherValue).longValue());
        } else if (otherValue instanceof String) {
            return -1;
        }
        throw new IllegalArgumentException("Cannot compare " + o.getClass().getSimpleName() + " value with " + this.getClass().getSimpleName() + " value.");
    }

}
