package com.mmkholding.controller;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Repository("account")
public class AccountController {

    @GetMapping
    public String hello() {
        return "Hello World";
    }
}
