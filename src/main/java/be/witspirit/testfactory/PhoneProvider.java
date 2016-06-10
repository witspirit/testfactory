package be.witspirit.testfactory;

import org.apache.commons.lang.RandomStringUtils;

import java.util.function.Supplier;

/**
 * Generates phone numbers
 */
public class PhoneProvider implements Supplier<String> {

    @Override
    public String get() {
        return "+"+ RandomStringUtils.randomNumeric(11);
    }
}
