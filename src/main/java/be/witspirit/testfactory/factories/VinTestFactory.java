package be.witspirit.testfactory.factories;

import be.witspirit.testfactory.exampledomain.Vin;
import be.witspirit.testfactory.support.TestFactory;
import be.witspirit.testfactory.support.valueproviders.ValueProviders;

import java.util.function.Supplier;

/**
 * Produces sample VINs
 */
public class VinTestFactory implements TestFactory<Vin> {

    private Supplier<String> rawVin = ValueProviders.randomString(17, 17);

    @Override
    public Vin get() {
        return new Vin(rawVin.get());
    }

    public VinTestFactory setRawVin(Supplier<String> rawVin) {
        this.rawVin = rawVin;
        return this;
    }
}
