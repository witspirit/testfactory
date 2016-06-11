package be.witspirit.testfactory.support;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Minimal interface for a general factory, just to make it easier to define various implementations
 *
 * This factory can also act as a Supplier<T>
 */
@FunctionalInterface
public interface TestFactory<T> extends Supplier<T> {

    default TestFactory<T> customize(Consumer<T> customizer) {
        return new DelegateTestFactory<>(this).setOverrider(customizer);
    }
}
