package be.witspirit.testfactory.factories;

import be.witspirit.testfactory.exampledomain.User;
import be.witspirit.testfactory.factories.UserTestFactory;
import be.witspirit.testfactory.support.TestFactory;
import be.witspirit.testfactory.support.valueproviders.experimental.DependentProvider;
import be.witspirit.testfactory.support.valueproviders.ValueProviders;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * Basic verification of the UserTestFactory
 */
public class UserTestFactoryTest {

    @Test
    public void verifySizes() {
        UserTestFactory factory = new UserTestFactory();
        for (int i=0; i < 100; i++) {
            User user = factory.create();
            Assert.assertEquals(20, user.getFirstName().length());
            Assert.assertEquals(60, user.getLastName().length());
            Assert.assertEquals(65, user.getEmail().length());
            Assert.assertEquals(12, user.getPhone().length());
        }
    }

    @Test
    public void selectedName() {
        UserTestFactory factory = new UserTestFactory().setFirstName(ValueProviders.name());
        boolean bertSeen = false;
        boolean sawSomethingElse = false;
        for (int i=0; i < 100; i++) {
            if (factory.create().getFirstName().equals("Bert")) {
                bertSeen = true;
            } else {
                sawSomethingElse = true;
            }
        }
        // I am just hoping that most of the time this works out
        Assert.assertEquals(true, bertSeen);
        Assert.assertEquals(true, sawSomethingElse);
    }

    @Test
    public void showMeJava8() {
        // A bit overkill, but just showing...
        User user = new UserTestFactory()
                .setFirstName(() -> "Bert")
                .setPhone(() -> "+323" + IntStream.range(1, 8).mapToObj(i -> Integer.toString(i)).reduce("", String::concat))
                .create();

        Assert.assertEquals("Bert", user.getFirstName());
        Assert.assertEquals(60, user.getLastName().length());
        Assert.assertEquals(65, user.getEmail().length());
        Assert.assertEquals("+3231234567", user.getPhone());
    }

    @Test
    public void whatAboutDependentValues() {
        // Can the phone number be the hashcode of the first name ?
        // In the current approach we are not passing in the current status of the object
        // But we can build it on top as needed
        DependentProvider<String, String> nameToPhone = DependentProvider.dependency(ValueProviders.name(), name -> "+" + name.hashCode());

        UserTestFactory factory = new UserTestFactory().setFirstName(nameToPhone.source()).setPhone(nameToPhone.dependent());

        // A bit more overkill Java8 streams, just because we can :-)
        IntStream.range(0, 100)
                .mapToObj(i -> factory.create())
                .forEach(user -> Assert.assertEquals("+"+user.getFirstName().hashCode(), user.getPhone()) );

        // Looks a bit tricky doesn't it with this dependent provider... Probably at the wrong level of abstraction
    }

    @Test
    public void dependentValuesRevisited() {
        TestFactory<User> factory = new UserTestFactory()
                .setFirstName(ValueProviders.name())
                .customize(user -> user.setPhone("+" + user.getFirstName().hashCode()));

        // A bit more overkill Java8 streams, just because we can :-)
        IntStream.range(0, 100)
                .mapToObj(i -> factory.create())
                .forEach(user -> Assert.assertEquals("+"+user.getFirstName().hashCode(), user.getPhone()) );

        // I think this approach is much more manageable. No complex/unsafe dependent provider, which only worked ok for two dependent values
        // While this approach can be easily extended to arbitrary dependencies and still easily build on the supplier model
    }
}
