package be.witspirit.testfactory.support.valueproviders.experimental;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Allows to create values that are dependent on values supplied by another
 *
 * WARNING: Implementation is not thread-safe ! Each thread should have its own instance
 *
 * NOTE: This was created to illustrate that it could be done, but would be cumbersome. Don't recommend
 * to actually use this approach. But it has lead to the creation of the customizer approach.
 */
public class DependentProvider<SourceT, DependentT> {

    private Supplier<SourceT> sourceSupplier;
    private Function<SourceT, DependentT> dependencyFunction;

    private enum DeliveryStatus {
        NONE, // or Both, so the cycle starts anew
        SOURCE,
        DEPENDENT
    }

    private DeliveryStatus deliveryStatus = DeliveryStatus.NONE;
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
            switch (deliveryStatus) {
                case NONE:
                    lastSourceValue = sourceSupplier.get();
                    deliveryStatus = DeliveryStatus.SOURCE;
                    break;
                case SOURCE:
                    // Delivery status remains the same
                    break;
                case DEPENDENT:
                    deliveryStatus = DeliveryStatus.NONE; // We are ready
                    break;
            }
            return lastSourceValue;
        };
    }

    public Supplier<DependentT> dependent() {
        return () -> {
            switch (deliveryStatus) {
                case NONE:
                    lastSourceValue = sourceSupplier.get();
                    deliveryStatus = DeliveryStatus.DEPENDENT;
                    break;
                case SOURCE:
                    deliveryStatus = DeliveryStatus.NONE; // We are ready
                    break;
                case DEPENDENT:
                    // Delivery status remains the same
                    break;
            }
            return dependencyFunction.apply(lastSourceValue);
        };
    }

}
