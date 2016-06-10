package be.witspirit.testfactory;

import java.util.Random;
import java.util.function.Supplier;

/**
 * Supplies name values randomly from a dictionary of names
 */
public class NameProvider implements Supplier<String> {
    private static final Random random = new Random();

    private String[] dictionary = new String[]{
            "Bert", "Bart", "Tung", "Kristof", "Bram", "Hemm", "Gerd"
    };

    @Override
    public String get() {
        return dictionary[random.nextInt(dictionary.length)];
    }
}
