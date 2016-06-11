package be.witspirit.testfactory.factories;

import be.witspirit.testfactory.exampledomain.Car;
import be.witspirit.testfactory.exampledomain.EngineSpec;
import be.witspirit.testfactory.exampledomain.Vin;
import be.witspirit.testfactory.support.TestFactory;
import be.witspirit.testfactory.support.valueproviders.ValueProviders;

import java.util.function.Supplier;

/**
 * Produces Cars
 */
public class CarTestFactory implements TestFactory<Car> {

    private Supplier<Vin> vin = new VinTestFactory();
    private Supplier<EngineSpec> engine = new EngineSpecFactory();

    @Override
    public Car get() {
        return new Car()
                .setVin(vin.get())
                .setEngine(engine.get())
                // No service comment for now... Is a more interesting case
                ;
    }

    public CarTestFactory setVin(Supplier<Vin> vin) {
        this.vin = vin;
        return this;
    }

    public CarTestFactory setEngine(Supplier<EngineSpec> engine) {
        this.engine = engine;
        return this;
    }
}
