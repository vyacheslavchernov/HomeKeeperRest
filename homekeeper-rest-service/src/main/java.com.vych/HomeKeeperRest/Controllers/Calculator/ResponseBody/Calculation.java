package HomeKeeperRest.Controllers.Calculator.ResponseBody;

import com.fasterxml.jackson.annotation.JsonProperty;
import HomeKeeperRest.ApiCore.Payloads.ResponsePayload;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Calculation implements ResponsePayload {
    @JsonProperty("totalWithCommunal")
    private double totalWithCommunal;

    @JsonProperty("totalWithCommunalAndAdditional")
    private double totalWithCommunalAndAdditional;

    @JsonProperty("totalWithCommunalAndNegativeAdditional")
    private double totalWithCommunalAndNegativeAdditional;

    @JsonProperty("communal")
    private Communal communal;
}
