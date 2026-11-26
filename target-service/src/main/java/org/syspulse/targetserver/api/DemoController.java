package org.syspulse.targetserver.api;

import org.syspulse.targetserver.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/custom-exception")
    void throwCustomException() {
        demoService.throwCustomException();
    }

    @GetMapping("/error")
    void throwError() {
        demoService.throwError();
    }

    @GetMapping("/exception")
    void throwCheckedException() {
        demoService.throwCheckedException();
    }
}
