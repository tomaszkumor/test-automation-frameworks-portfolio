package dataProviders.dataProviders;

import dataProviders.dataProvidersModels.DesktopModel;
import org.testng.annotations.DataProvider;

import java.util.concurrent.ThreadLocalRandom;

public class DesktopDP {
    @DataProvider
    Object[][] desktop() {
        String fontSize = getRandomSize();

        return new Object[][]{
                {DesktopModel.builder()
                        .documentScale("100%")
                        .fontSize(fontSize)
                        .fileName("ExampleName")
                        .build()
                }
        };
    }

    public String getRandomSize() {
        String[] values = {"12", "13", "14"};
        int index = ThreadLocalRandom.current().nextInt(values.length);
        return values[index];
    }
}