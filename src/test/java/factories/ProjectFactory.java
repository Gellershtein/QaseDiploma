package factories;

import com.github.javafaker.Faker;
import factories.base.BaseFactory;
import lombok.extern.log4j.Log4j2;
import models.Project;

@Log4j2
public class ProjectFactory extends BaseFactory {
    Faker faker;

    public Project getProject() {
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
