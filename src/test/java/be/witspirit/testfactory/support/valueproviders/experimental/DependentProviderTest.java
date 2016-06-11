package be.witspirit.testfactory.support.valueproviders.experimental;

import be.witspirit.testfactory.support.valueproviders.CountProvider;
import be.witspirit.testfactory.support.valueproviders.ValueProviders;
import be.witspirit.testfactory.support.valueproviders.experimental.DependentProvider;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * Test to verify the behavior of the rather complex DependentProvider
 */
public class DependentProviderTest {

    @Test
    public void singleInOrderTest() {
        DependentProvider<String, Integer> depProvider = DependentProvider.dependency(() -> "Input", in -> in.hashCode());

        Assert.assertEquals("Input", depProvider.source().get());
        Assert.assertEquals(Integer.valueOf("Input".hashCode()), depProvider.dependent().get());
    }

    @Test
    public void singleOutOfOrderTest() {
        DependentProvider<String, Integer> depProvider = DependentProvider.dependency(() -> "Input", in -> in.hashCode());

        Assert.assertEquals(Integer.valueOf("Input".hashCode()), depProvider.dependent().get());
        Assert.assertEquals("Input", depProvider.source().get());
    }

    @Test
    public void multipleInOrderTest() {
        DependentProvider<Integer, String> depProvider = DependentProvider.dependency(ValueProviders.count(0, 1), i -> "Value " + i);

        Supplier<Integer> source = depProvider.source();
        Supplier<String> dependent = depProvider.dependent();

        IntStream.range(0, 100).boxed().forEach(i -> {
            Assert.assertEquals(i, source.get());
            Assert.assertEquals("Value "+i, dependent.get());
        });
    }

    @Test
    public void multipleOutOfOrderTest() {
        DependentProvider<Integer, String> depProvider = DependentProvider.dependency(ValueProviders.count(0, 1), i -> "Value " + i);

        Supplier<Integer> source = depProvider.source();
        Supplier<String> dependent = depProvider.dependent();

        IntStream.range(0, 100).boxed().forEach(i -> {
            Assert.assertEquals("Value "+i, dependent.get());
            Assert.assertEquals(i, source.get());
        });
    }

    @Test
    public void multipleMixedOrderTest() {
        DependentProvider<Integer, String> depProvider = DependentProvider.dependency(ValueProviders.count(0, 1), i -> "Value " + i);

        Supplier<Integer> source = depProvider.source();
        Supplier<String> dependent = depProvider.dependent();

        IntStream.range(0, 100).boxed().forEach(i -> {
            if (i % 2 == 0) {
                Assert.assertEquals(i, source.get());
                Assert.assertEquals("Value " + i, dependent.get());
            } else {
                Assert.assertEquals("Value " + i, dependent.get());
                Assert.assertEquals(i, source.get());
            }
        });
    }
}
