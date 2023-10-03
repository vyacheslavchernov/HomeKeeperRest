package HomeKeeperRest.ApiCore.Payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Текстовая полезная нагрузка для ответа.
 */
@Data
@Accessors(chain = true)
public class StringPayload implements ResponsePayload {
    @JsonProperty("message")
    private String message;
}
