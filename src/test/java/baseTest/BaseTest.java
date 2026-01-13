package baseTest;

import org.testng.annotations.BeforeMethod;

public class BaseTest {
    @BeforeMethod
    public void beforeMethod() {
        String framework = getFramework();
        logAll(framework);

        switch(framework) {
            case "web" -> {

            }

            case "mobile" -> {

            }
        }
    }
}
