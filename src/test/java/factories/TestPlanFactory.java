package factories;

import factories.base.BaseFactory;
import models.TestPlan;

public class TestPlanFactory extends BaseFactory {

    public TestPlan getTestPlan() {
        return TestPlan.builder()
                .testPlanTitle(fakeValuesService.regexify("[a-z1-9]{10}"))
                .description(fakeValuesService.regexify("[a-z1-9]{10}"))
                .build();
    }
}
