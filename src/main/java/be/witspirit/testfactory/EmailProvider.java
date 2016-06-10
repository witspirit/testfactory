package be.witspirit.testfactory;

import org.apache.commons.lang.RandomStringUtils;

import java.util.function.Supplier;

/**
 * Generates email addresses
 */
public class EmailProvider implements Supplier<String> {

    @Override
    public String get() {
        return RandomStringUtils.randomAlphanumeric(30)+"@"+RandomStringUtils.randomAlphabetic(30)+"."+RandomStringUtils.randomAlphabetic(3);
    }
}
