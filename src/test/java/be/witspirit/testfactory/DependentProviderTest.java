package be.witspirit.testfactory;

import org.junit.Assert;
import org.junit.Test;

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

//    @Test
//    public void singleOutOfOrderTest() {
//        DependentProvider<String, Integer> depProvider = DependentProvider.dependency(() -> "Input", in -> in.hashCode());
//
//        Assert.assertEquals(Integer.valueOf("Input".hashCode()), depProvider.dependent().get());
//        Assert.assertEquals("Input", depProvider.source().get());
//    }
}
