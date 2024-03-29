package com.aranseiki.imagehandlejava.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ImagehandlejavaController {
    
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, world!";
    }
}
