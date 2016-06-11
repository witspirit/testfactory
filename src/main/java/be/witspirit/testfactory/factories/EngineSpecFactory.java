package be.witspirit.testfactory.factories;

import be.witspirit.testfactory.exampledomain.EngineSpec;
import be.witspirit.testfactory.support.TestFactory;
import be.witspirit.testfactory.support.valueproviders.ValueProviders;

import java.util.function.Supplier;

/**
 * Produces EngineSpecs
 */
public class EngineSpecFactory implements TestFactory<EngineSpec> {

    private Supplier<EngineSpec.Fuel> fuel = ValueProviders.randomEnum(EngineSpec.Fuel.class);
    private Supplier<Integer> displacement = ValueProviders.intWithin(1500, 2500);

    @Override
    public EngineSpec create() {
        return new EngineSpec(fuel.get(), displacement.get());
    }

    public EngineSpecFactory setFuel(Supplier<EngineSpec.Fuel> fuel) {
        this.fuel = fuel;
        return this;
    }

    public EngineSpecFactory setDisplacement(Supplier<Integer> displacement) {
        this.displacement = displacement;
        return this;
    }
}
