package models.api;

import models.Project;

public class ProjectResult extends Result<Project> {

    ProjectResult(boolean status, Project result) {
        super(status, result);
    }
}
