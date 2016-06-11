package be.witspirit.testfactory.valueproviders;

import org.apache.commons.lang.RandomStringUtils;

import java.util.function.Supplier;

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

    public static Supplier<String> email() {
        return () -> RandomStringUtils.randomAlphanumeric(30)+"@"+RandomStringUtils.randomAlphabetic(30)+"."+RandomStringUtils.randomAlphabetic(3);
    }

    public static Supplier<String> phone() {
        return () -> "+"+ RandomStringUtils.randomNumeric(11);
    }

    public static RandomStringProvider randomString(int minSize, int maxSize) {
        return new RandomStringProvider(minSize, maxSize);
    }
}
