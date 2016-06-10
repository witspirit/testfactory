package be.witspirit.testfactory;

import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * Basic verification of the UserTestFactory
 */
public class TestUserTestFactory {

    @Test
    public void verifySizes() {
        UserTestFactory factory = new UserTestFactory();
        for (int i=0; i < 100; i++) {
            User user = factory.newUser();
            Assert.assertEquals(20, user.getFirstName().length());
            Assert.assertEquals(60, user.getLastName().length());
            Assert.assertEquals(65, user.getEmail().length());
            Assert.assertEquals(12, user.getPhone().length());
        }
    }

    @Test
    public void selectedName() {
        UserTestFactory factory = new UserTestFactory().setFirstName(new NameProvider());
        boolean bertSeen = false;
        boolean sawSomethingElse = false;
        for (int i=0; i < 100; i++) {
            if (factory.newUser().getFirstName().equals("Bert")) {
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
                .newUser();

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

    }
}
