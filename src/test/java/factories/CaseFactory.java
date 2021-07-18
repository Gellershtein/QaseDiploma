package factories;

import com.github.javafaker.Faker;
import factories.base.BaseFactory;
import models.Case;

public class CaseFactory extends BaseFactory {
    Faker faker;

    public Case getCase() {
        faker = new Faker();
        String status = faker.options().option("Actual", "Draft", "Deprecated");
        String severity = faker.options().option("Not set", "Blocker", "Critical", "Major", "Normal", "Minor", "Trivial");
        String priority = faker.options().option("Not set", "High", "Medium", "Low");
        String type = faker.options().option("Other", "Functional", "Smoke", "Regression", "Security", "Usability", "Performance", "Acceptance",
                "Compatibility", "Integration", "Exploratory");
        String layer = faker.options().option("Unknown", "E2E", "API", "Unit");
        String isFlaky = faker.options().option("No", "Yes");
        String behavior = faker.options().option("Not set", "Positive", "Negative", "Destructive");
        String automationStatus = faker.options().option("Not automated", "To be automated", "Automated");
        return Case.builder()
                .title(fakeValuesService.regexify("[a-z1-9]{10}"))
                .description(fakeValuesService.regexify("[A-Z1-9]{5}"))
                .status(status)
                .severity(severity)
                .priority(priority)
                .type(type)
                .layer(layer)
                .isFlaky(isFlaky)
                .behavior(behavior)
                .automationStatus(automationStatus)
                .preconditions(fakeValuesService.regexify("[a-z1-9]{10}"))
                .postconditions(fakeValuesService.regexify("[a-z1-9]{10}"))
                .build();
    }
}
