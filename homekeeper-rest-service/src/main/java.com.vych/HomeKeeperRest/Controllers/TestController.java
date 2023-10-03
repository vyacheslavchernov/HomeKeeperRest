package HomeKeeperRest.Controllers;

import HomeKeeperRest.ApiCore.ApiResponse;
import HomeKeeperRest.ApiCore.Payloads.StringPayload;
import HomeKeeperRest.ApiCore.Status;
import HomeKeeperRest.ApiCore.StatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Тестовый контроллер для проверки успешности развёртки приложения
 */
@RestController
public class TestController {
    @GetMapping("api/echo")
    public ApiResponse testMethod(@RequestParam String msg) {
        return new ApiResponse()
                .setStatus(new Status().setCode(StatusCode.SUCCESS))
                .setPayload(new StringPayload().setMessage(msg));
    }
}
