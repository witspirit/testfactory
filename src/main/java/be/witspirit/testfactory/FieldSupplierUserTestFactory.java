package be.witspirit.testfactory;

import be.witspirit.testfactory.exampledomain.User;
import be.witspirit.testfactory.valueproviders.ValueProviders;

import java.util.function.Supplier;

/**
 * Rudimentary start for a test factory
 */
public class FieldSupplierUserTestFactory implements TestFactory<User> {

    private Supplier<String> firstName = ValueProviders.randomString(20,20);
    private Supplier<String> lastName = ValueProviders.randomString(60,60);
    private Supplier<String> email = ValueProviders.email();
    private Supplier<String> phone = ValueProviders.phone();


    @Override
    public User create() {
        User user = new User();
        user.setFirstName(firstName.get());
        user.setLastName(lastName.get());
        user.setEmail(email.get());
        user.setPhone(phone.get());
        return user;
    }

    public FieldSupplierUserTestFactory setFirstName(Supplier<String> firstName) {
        this.firstName = firstName;
        return this;
    }

    public FieldSupplierUserTestFactory setLastName(Supplier<String> lastName) {
        this.lastName = lastName;
        return this;
    }

    public FieldSupplierUserTestFactory setEmail(Supplier<String> email) {
        this.email = email;
        return this;
    }

    public FieldSupplierUserTestFactory setPhone(Supplier<String> phone) {
        this.phone = phone;
        return this;
    }
}
