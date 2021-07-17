package models.api;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Result<T> {
    boolean status;
    @Expose
    T result;
}
