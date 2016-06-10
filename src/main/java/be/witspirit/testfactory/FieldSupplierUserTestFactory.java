package be.witspirit.testfactory;

import java.util.function.Supplier;

/**
 * Rudimentary start for a test factory
 */
public class FieldSupplierUserTestFactory implements UserTestFactory {

    private static final Supplier<String> FIRST_NAME_DEFAULT = ValueProviders.randomString(20,20);
    private static final Supplier<String> LAST_NAME_DEFAULT = ValueProviders.randomString(60,60);
    private static final Supplier<String> EMAIL_DEFAULT = ValueProviders.email();
    private static final Supplier<String> PHONE_DEFAULT = ValueProviders.phone();

    private Supplier<String> firstName = FIRST_NAME_DEFAULT;
    private Supplier<String> lastName = LAST_NAME_DEFAULT;
    private Supplier<String> email = EMAIL_DEFAULT;
    private Supplier<String> phone = PHONE_DEFAULT;


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
