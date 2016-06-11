package be.witspirit.testfactory.support;

import java.util.function.Consumer;

/**
 * Minimal interface for a general factory, just to make it easier to define various implementations
 *
 * This factory can also act as a Supplier<T>
 */
@FunctionalInterface
public interface TestFactory<T> {

    T create();

    default TestFactory<T> customize(Consumer<T> customizer) {
        return new DelegateTestFactory<>(this).setOverrider(customizer);
    }
}
