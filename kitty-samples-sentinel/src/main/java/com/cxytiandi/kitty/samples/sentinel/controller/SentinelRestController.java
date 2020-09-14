package com.cxytiandi.kitty.samples.sentinel.controller;


import com.cxytiandi.kitty.samples.sentinel.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @作者 尹吉欢
 * @个人微信 jihuan900
 * @微信公众号 猿天地
 * @GitHub https://github.com/yinjihuan
 * @作者介绍 http://cxytiandi.com/about
 * @时间 2020-07-21 22:31
 */
@RestController
@RequestMapping("/tt")
public class SentinelRestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public String test() {
        return "success";
    }

    @GetMapping("/test/{id}")
    public String test(@PathVariable int id) {
        testService.test();
        return "success";
    }

    @PostMapping("/test/{id}")
    public String test2(@PathVariable int id) {
        testService.test();
        return "success";
    }

    @GetMapping("/{name}/test/{id}")
    public String test3(@PathVariable String name, @PathVariable int id) {
        return "success";
    }

}
