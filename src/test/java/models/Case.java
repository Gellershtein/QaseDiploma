package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Case {
    String title;
    String description;
    String suite;
    String type;
    String milestone;
    String status;
    String severity;
    String priority;
    String layer;
    String isFlaky;
    String behavior;
    String automationStatus;
    String preConditions;
    String postConditions;
}
