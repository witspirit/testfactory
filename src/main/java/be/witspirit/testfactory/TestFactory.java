package be.witspirit.testfactory;

import java.util.function.Consumer;

/**
 * Minimal interface for a general factory, just to make it easier to define various implementations
 */
public interface TestFactory<T> {

    T create();

    default TestFactory<T> customize(Consumer<T> customizer) {
        return new DelegateTestFactory<>(this).setOverrider(customizer);
    }
}
