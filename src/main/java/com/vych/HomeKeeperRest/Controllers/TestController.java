package com.vych.HomeKeeperRest.Controllers;

import com.vych.HomeKeeperRest.ApiCore.ApiResponse;
import com.vych.HomeKeeperRest.ApiCore.Status;
import com.vych.HomeKeeperRest.ApiCore.StatusCode;
import com.vych.HomeKeeperRest.ApiCore.Payloads.StringPayload;
import com.vych.HomeKeeperRest.Aspects.Annotations.NeedLogs;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Тестовый контроллер для проверки успешности развёртки приложения
 */
@RestController
public class TestController {

    @NeedLogs
    @GetMapping("api/echo")
    public ApiResponse testMethod(@RequestParam String msg) {
        return new ApiResponse()
                .setStatus(new Status().setCode(StatusCode.SUCCESS))
                .setPayload(new StringPayload().setMessage(msg));
    }
}
