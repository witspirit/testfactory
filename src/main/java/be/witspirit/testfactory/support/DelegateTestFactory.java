package be.witspirit.testfactory.support;

import java.util.function.Consumer;

/**
 * Delegation implementation to make it easy to selectively override
 *
 * You can override through inheritance by override the override method
 * Or you can override by delegating to a Consumer that performs the overrides
 */
public class DelegateTestFactory<T> implements TestFactory<T> {

    private final TestFactory<T> sourceFactory;

    private Consumer<T> overrider = null;

    public DelegateTestFactory(TestFactory<T> sourceFactory) {
        this.sourceFactory = sourceFactory;
    }

    public DelegateTestFactory setOverrider(Consumer<T> overrider) {
        this.overrider = overrider;
        return this;
    }

    @Override
    public T create() {
        T newInstance = sourceFactory.create();
        override(newInstance);
        return newInstance;
    }

    protected void override(T newInstance) {
        if (overrider != null) {
            overrider.accept(newInstance);
        }
    }
}
