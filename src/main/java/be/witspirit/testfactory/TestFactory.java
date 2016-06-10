package be.witspirit.testfactory;

/**
 * Minimal interface for a general factory, just to make it easier to define various implementations
 */
public interface TestFactory<T> {

    T create();
}
