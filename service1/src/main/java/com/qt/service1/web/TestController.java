package com.qt.service1.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiongyang.wjy
 * @since 2021/6/29
 */
@RestController
public class TestController {

    @GetMapping("test")
    public String test(){
        return "test";
    }
}
