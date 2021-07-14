package factories;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import models.TestPlan;

import java.util.Locale;

public class TestPlanFactory {

    FakeValuesService fakeValuesService;

    public TestPlan getTestPlan() {
        fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        return TestPlan.builder()
                .testPlanTitle(fakeValuesService.regexify("[a-z1-9]{10}"))
                .description(fakeValuesService.regexify("[a-z1-9]{10}"))
                .build();
    }
}
