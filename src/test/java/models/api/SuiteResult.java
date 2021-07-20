package models.api;

import models.Suite;

public class SuiteResult extends Result<Suite> {

    SuiteResult(boolean status, Suite result) {
        super(status, result);
    }
}
