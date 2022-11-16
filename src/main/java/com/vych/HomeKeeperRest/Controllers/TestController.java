package com.vych.HomeKeeperRest.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("api/test")
    public String testMethod() {
        return "All works fine";
    }
}
