package com.cxytiandi.kitty.samples.lock.controller;

import com.cxytiandi.kitty.samples.lock.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @作者 尹吉欢
 * @个人微信 jihuan900
 * @微信公众号 猿天地
 * @GitHub https://github.com/yinjihuan
 * @作者介绍 http://cxytiandi.com/about
 * @时间 2020-07-14 21:42
 */
@RestController
public class TestRestController {

    @Autowired
    private TestService testService;

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @GetMapping("/test")
    public String test() {
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                testService.lockFailFast();
            });
        }
        return "success";
    }

    @GetMapping("/test2")
    public String test2() {
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                testService.lockByWait();
            });
        }
        return "success";
    }

    @GetMapping("/test3")
    public String test3() {
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                testService.lockMysql();
            });
        }
        return "success";
    }

    @GetMapping("/test4")
    public String test4() {
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                testService.idempotent("");
            });
        }
        return "success";
    }

    @GetMapping("/test5")
    public String test5() {
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                testService.idempotentCode2("1001");
            });
        }
        return "success";
    }

}
