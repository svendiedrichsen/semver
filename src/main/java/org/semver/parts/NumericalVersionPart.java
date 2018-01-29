package org.semver.parts;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NumericalVersionPart)) return false;

        NumericalVersionPart that = (NumericalVersionPart) o;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return (int) (value ^ (value >>> 32));
    }
}
