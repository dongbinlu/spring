package com.only.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JSONController {

    @GetMapping("/json")
    public String json() {
        return "hello json";
    }

}
