package be.witspirit.testfactory.support;

import be.witspirit.testfactory.exampledomain.Car;
import be.witspirit.testfactory.exampledomain.EngineSpec;
import be.witspirit.testfactory.exampledomain.User;
import be.witspirit.testfactory.exampledomain.Vin;
import be.witspirit.testfactory.factories.EngineSpecFactory;
import be.witspirit.testfactory.factories.VinTestFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Explore what is possible with the simple ReflectiveTestFactory
 */
public class ReflectiveTestFactoryTest {

    @Test
    public void reflectiveUser() {
        ReflectiveTestFactory<User> factory = new ReflectiveTestFactory<>(User.class);

        User user = factory.get();

        Assert.assertNotNull(user);
        Assert.assertTrue(user.getFirstName().length() >= 10 && user.getFirstName().length() <= 20);
        Assert.assertTrue(user.getLastName().length() >= 10 && user.getLastName().length() <= 20);
        Assert.assertTrue(user.getEmail().length() >= 10 && user.getEmail().length() <= 20);
        Assert.assertTrue(user.getPhone().length() >= 10 && user.getPhone().length() <= 20);
    }

    @Test(expected = RuntimeException.class) // No default constructor
    public void reflectiveVin() {
        ReflectiveTestFactory<Vin> factory = new ReflectiveTestFactory<>(Vin.class);

        factory.get();
    }

    @Test(expected = RuntimeException.class) // No default constructor
    public void reflectiveEngineSpec() {
        ReflectiveTestFactory<EngineSpec> factory = new ReflectiveTestFactory<>(EngineSpec.class);

        factory.get();
    }

    @Test
    public void reflectiveCar() {
        ReflectiveTestFactory<Car> factory = new ReflectiveTestFactory<>(Car.class)
                .provide(Vin.class, new VinTestFactory())
                .provide(EngineSpec.class, new EngineSpecFactory());

        Car car = factory.get();

        Assert.assertEquals(17, car.getVin().toString().length()); // Horrible test, but not sure how to do better
        Assert.assertNotNull(car.getEngine().getFuel());
        Assert.assertTrue(car.getEngine().getDisplacement() >= 1500 && car.getEngine().getDisplacement() <= 2500);
        Assert.assertTrue(car.getServiceComments().isEmpty());
    }
}
