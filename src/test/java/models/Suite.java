package models;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Suite {
    //    @Expose
//    int id;
    @Expose
    String title;
    @Expose
    String description;
    @Expose
    String preconditions;
}
