package models;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Suite {
    String title;
    String description;
    String preconditions;
    @SerializedName("parent_id")
    int parentId;
}
