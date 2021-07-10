package factories;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import models.Project;

import java.util.Locale;

public class ProjectFactory {
    FakeValuesService fakeValuesService;
    Faker faker;

    public Project getProject() {
        fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        faker = new Faker();
        String accessType = faker.options().option("private", "public");
        return Project.builder()
                .title(fakeValuesService.regexify("[a-z1-9]{10}"))
                .code(fakeValuesService.regexify("[A-Z]{5}"))
                .description(fakeValuesService.regexify("[a-z1-9]{10}"))
                .access(accessType)
                .build();
    }
}
