package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class TestRun {
    String testRunTitle;
    String description;
    String plan;
    String environment;
    String milestone;
}
