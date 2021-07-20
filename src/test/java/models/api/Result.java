package models.api;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Result<T> {
    @Expose
    boolean status;
    @Expose
    T result;
}
