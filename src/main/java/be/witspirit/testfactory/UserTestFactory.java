package be.witspirit.testfactory;

import java.util.function.Supplier;

/**
 * Rudimentary start for a test factory
 */
public class UserTestFactory {

    private static final Supplier<String> FIRST_NAME_DEFAULT = new RandomStringProvider(20,20);
    private static final Supplier<String> LAST_NAME_DEFAULT = new RandomStringProvider(60,60);
    private static final Supplier<String> EMAIL_DEFAULT = new EmailProvider();
    private static final Supplier<String> PHONE_DEFAULT = new PhoneProvider();

    private Supplier<String> firstName = FIRST_NAME_DEFAULT;
    private Supplier<String> lastName = LAST_NAME_DEFAULT;
    private Supplier<String> email = EMAIL_DEFAULT;
    private Supplier<String> phone = PHONE_DEFAULT;


    public User newUser() {
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
