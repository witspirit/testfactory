package be.witspirit.testfactory.factories;

import be.witspirit.testfactory.exampledomain.User;
import be.witspirit.testfactory.support.TestFactory;
import be.witspirit.testfactory.support.valueproviders.ValueProviders;

import java.util.function.Supplier;

/**
 * Rudimentary start for a test factory
 */
public class UserTestFactory implements TestFactory<User> {

    private Supplier<String> firstName = ValueProviders.randomString(20,20);
    private Supplier<String> lastName = ValueProviders.randomString(60,60);
    private Supplier<String> email = ValueProviders.email();
    private Supplier<String> phone = ValueProviders.phone();

    @Override
    public User get() {
        User user = new User();
        user.setFirstName(firstName.get());
        user.setLastName(lastName.get());
        user.setEmail(email.get());
        user.setPhone(phone.get());
        return user;
    }

    public UserTestFactory setFirstName(Supplier<String> firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserTestFactory setLastName(Supplier<String> lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserTestFactory setEmail(Supplier<String> email) {
        this.email = email;
        return this;
    }

    public UserTestFactory setPhone(Supplier<String> phone) {
        this.phone = phone;
        return this;
    }
}
