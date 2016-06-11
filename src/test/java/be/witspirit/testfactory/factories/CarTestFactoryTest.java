package be.witspirit.testfactory.factories;

import be.witspirit.testfactory.exampledomain.Car;
import org.junit.Assert;
import org.junit.Test;

/**
 * Explore the CarTestFactory
 */
public class CarTestFactoryTest {

    @Test
    public void simpleCar() {
        Car car = new CarTestFactory().create();

        Assert.assertEquals(17, car.getVin().toString().length()); // Horrible test, but not sure how to do better
        Assert.assertNotNull(car.getEngine().getFuel());
        Assert.assertTrue(car.getEngine().getDisplacement() >= 1500 && car.getEngine().getDisplacement() <= 2500);
        Assert.assertTrue(car.getServiceComments().isEmpty());
    }
}
