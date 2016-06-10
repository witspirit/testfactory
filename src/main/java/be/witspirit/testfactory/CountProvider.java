package be.witspirit.testfactory;

import java.util.function.Supplier;

/**
 * Just counts up on each delivery
 */
public class CountProvider implements Supplier<Integer> {
    private final int start;
    private final int increment;

    private int count;

    public CountProvider(int start, int increment) {
        this.start = start;
        this.increment = increment;

        this.count = start;
    }

    public void reset() {
        count = start;
    }

    @Override
    public Integer get() {
        int value = count;
        count += increment;
        return value;
    }
}
