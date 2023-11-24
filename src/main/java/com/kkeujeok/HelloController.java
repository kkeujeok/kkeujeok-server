package com.kkeujeok;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping
    public String hello() {

        return "CI/CD - Deploy Test";
    }

    @GetMapping("/what")
    public String what() {
        return "2";
    }
}
