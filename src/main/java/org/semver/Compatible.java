package org.semver;

@FunctionalInterface
public interface Compatible<T> {
    boolean isCompatibleWith(T other);
}
