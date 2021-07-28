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
    @Expose(serialize = false)
    String preconditions;
    @Expose(serialize = false)
    String postconditions;
    @Expose(serialize = false)
    String severity;
    @Expose(serialize = false)
    String priority;
    @Expose(serialize = false)
    String type;
    @Expose(serialize = false)
    String behavior;
    @Expose(serialize = false)
    @SerializedName("automation")
    String automationStatus;
    @Expose(serialize = false)
    String status;
    @Expose(serialize = false)
    @SerializedName("milestone_id")
    String milestone;
    @Expose(serialize = false)
    @SerializedName("suite_id")
    String suite;
    String layer;
    String isFlaky;
}
