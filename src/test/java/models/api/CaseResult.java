package models.api;

import models.Case;

public class CaseResult extends Result<Case> {

    CaseResult(boolean status, Case result) {
        super(status, result);
    }
}
