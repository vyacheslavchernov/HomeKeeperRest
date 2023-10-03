package HomeKeeperRest.ApiCore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Класс-обёртка ошибок.
 */
@Data
@Accessors(chain = true)
public class Error {
    @JsonProperty("description")
    private String description;

    @JsonProperty("trace")
    private String trace;
}
