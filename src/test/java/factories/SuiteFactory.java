package factories;

import factories.base.BaseFactory;
import models.Suite;

public class SuiteFactory extends BaseFactory {

    public Suite getSuite() {
        return Suite.builder()
                .title(fakeValuesService.regexify("[a-z1-9]{10}"))
                .description(fakeValuesService.regexify("[a-z1-9]{10}"))
                .preconditions(fakeValuesService.regexify("[a-z1-9]{10}"))
                .build();
    }
}
