package com.vych.HomeKeeperRest.Controllers;

import com.vych.HomeKeeperRest.ApiCore.ApiResponse;
import com.vych.HomeKeeperRest.ApiCore.Status;
import com.vych.HomeKeeperRest.ApiCore.StatusCode;
import com.vych.HomeKeeperRest.ApiCore.StringPayload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Тестовый контроллер для проверки успешности развёртки приложения
 */
@RestController
public class TestController {

    @GetMapping("api/test")
    public ApiResponse testMethod() {
        return new ApiResponse()
                .setStatus(new Status().setCode(StatusCode.SUCCESS))
                .setPayload(new StringPayload().setMessage("All works fine!"));
    }
}
