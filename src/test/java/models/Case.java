package models;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Case {
    @Expose
    String title;
    @Expose
    int id;
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
