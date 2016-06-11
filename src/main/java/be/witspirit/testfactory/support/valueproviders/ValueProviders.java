package be.witspirit.testfactory.support.valueproviders;

import be.witspirit.testfactory.support.TestFactory;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Random;
import java.util.function.Supplier;

/**
 * Factory for value providers
 */
public class ValueProviders {
    private static final Random random = new Random();

    public static Supplier<String> name() {
        return new NameProvider();
    }

    public static Supplier<Integer> count(int start, int increment) {
        return new CountProvider(start, increment);
    }

    public static Supplier<String> email() {
        return () -> RandomStringUtils.randomAlphanumeric(30)+"@"+RandomStringUtils.randomAlphabetic(30)+"."+RandomStringUtils.randomAlphabetic(3);
    }

    public static Supplier<String> phone() {
        return () -> "+"+ RandomStringUtils.randomNumeric(11);
    }

    public static Supplier<String> randomString(int minSize, int maxSize) {
        return new RandomStringProvider(minSize, maxSize);
    }

    public static <T extends Enum<T>> Supplier<T> randomEnum(Class<T> enumType) {
        return new EnumProvider<>(enumType);
    }

    public static Supplier<Integer> intWithin(int min, int max) {
        return () -> min - 1 + random.nextInt(max-min+1); // The -1 and +1 are there to avoid a 0 value as the bound
    }

    public static <T> Supplier<T> factory(TestFactory<T> factory) {
        return factory::create;
    }

}
