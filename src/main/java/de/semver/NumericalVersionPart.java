package de.semver;

class NumericalVersionPart implements VersionPart<NumericalVersionPart, Long> {

    private static final long serialVersionUID = 1L;

    private long value;

    protected NumericalVersionPart(long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Long.toString(value);
    }

    @Override
    public int compareTo(NumericalVersionPart o) {
        return Long.valueOf(getValue()).compareTo(o.getValue());
    }

}
