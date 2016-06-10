package be.witspirit.testfactory;

/**
 * Factory for value providers
 */
public class ValueProviders {

    public static NameProvider name() {
        return new NameProvider();
    }

    public static CountProvider count(int start, int increment) {
        return new CountProvider(start, increment);
    }

    public static EmailProvider email() {
        return new EmailProvider();
    }

    public static PhoneProvider phone() {
        return new PhoneProvider();
    }

    public static RandomStringProvider randomString(int minSize, int maxSize) {
        return new RandomStringProvider(minSize, maxSize);
    }
}
