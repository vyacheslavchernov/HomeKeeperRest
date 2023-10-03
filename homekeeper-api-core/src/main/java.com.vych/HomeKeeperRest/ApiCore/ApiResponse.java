package HomeKeeperRest.ApiCore;

import HomeKeeperRest.ApiCore.Payloads.ResponsePayload;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Базовый класс для ответов API.
 * Любые данные, которые передаются от API должны быть переданы этим классом.
 * Полезная нагрузка ответа обязательно должна имплементировать {@link ResponsePayload}.
 */
@Data
@Accessors(chain = true)
public class ApiResponse {
    @JsonProperty("status")
    private Status status;

    @JsonProperty("payload")
    private ResponsePayload payload;
}
