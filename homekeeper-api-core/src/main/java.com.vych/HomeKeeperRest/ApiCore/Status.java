package HomeKeeperRest.ApiCore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;


/**
 * Статус выполнения запроса.
 * Значения для {@link #code} брать из {@link StatusCode}
 */
@Data
@Accessors(chain = true)
public class Status {
    @JsonProperty("code")
    private int code;

    @JsonProperty("errors")
    private List<Error> errors;

    public Status setCode(StatusCode code) {
        this.code = code.ordinal();
        return this;
    }

    public Status addError(Error error) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(error);
        return this;
    }
}
