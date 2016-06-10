package be.witspirit.testfactory;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Random;
import java.util.function.Supplier;

/**
 * Generates random strings
 */
public class RandomStringProvider implements Supplier<String> {
    private static Random random = new Random();

    private int minSize;
    private int maxSize;

    public RandomStringProvider(int minSize, int maxSize) {
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    @Override
    public String get() {
        int size = minSize;
        if (maxSize > minSize) {
            size += random.nextInt(maxSize-minSize);
        }
        return RandomStringUtils.random(size);
    }
}
