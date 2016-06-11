package be.witspirit.testfactory.support.valueproviders;

import be.witspirit.testfactory.exampledomain.EngineSpec;

import java.util.Random;
import java.util.function.Supplier;

/**
 * Delivers random enum values
 */
public class EnumProvider<T extends Enum<T>> implements Supplier<T> {
    private static final Random random = new Random();

    private T[] enumValues;

    public EnumProvider(Class<T> enumType) {
        enumValues = enumType.getEnumConstants();
    }

    @Override
    public T get() {
        return enumValues[random.nextInt(enumValues.length)];
    }
}
