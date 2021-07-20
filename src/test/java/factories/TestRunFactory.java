package factories;

import factories.base.BaseFactory;
import models.TestRun;

public class TestRunFactory extends BaseFactory {
    public TestRun getTestRun() {
        return TestRun.builder()
                .testRunTitle(fakeValuesService.regexify("[a-z1-9]{10}"))
                .description(fakeValuesService.regexify("[a-z1-9]{10}"))
                .plan("")
                .environment("")
                .milestone("")
                .build();
    }
}
