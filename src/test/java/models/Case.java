package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Case {
    @Expose
    int id;
    @Expose
    String title;
    @Expose
    String description;
    @Expose
    String preconditions;
    @Expose
    String postconditions;
    @Expose
    String severity;
    @Expose
    String priority;
    @Expose
    String type;
    @Expose
    String behavior;
    @Expose
    @SerializedName("automation")
    String automationStatus;
    @Expose
    String status;
    @Expose
    @SerializedName("milestone_id")
    String milestone;
    @Expose
    @SerializedName("suite_id")
    String suite;
    String layer;
    String isFlaky;
}
