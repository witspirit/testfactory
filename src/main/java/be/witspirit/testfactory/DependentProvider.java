package be.witspirit.testfactory;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Allows to create values that are dependent on values supplied by another
 */
public class DependentProvider<SourceT, DependentT> {

    private Supplier<SourceT> sourceSupplier;
    private Function<SourceT, DependentT> dependencyFunction;

    private SourceT lastSourceValue = null;

    private DependentProvider(Supplier<SourceT> sourceSupplier, Function<SourceT, DependentT> dependencyFunction) {
        this.sourceSupplier = sourceSupplier;
        this.dependencyFunction = dependencyFunction;
    }

    public static <Source, Dependent> DependentProvider<Source, Dependent> dependency(Supplier<Source> input, Function<Source, Dependent> dependency) {
        return new DependentProvider<>(input, dependency);
    }

    public Supplier<SourceT> source() {
        return () -> {
            lastSourceValue = sourceSupplier.get();
            return lastSourceValue;
        };
    }

    public Supplier<DependentT> dependent() {
        return () -> dependencyFunction.apply(lastSourceValue);
    }

}
