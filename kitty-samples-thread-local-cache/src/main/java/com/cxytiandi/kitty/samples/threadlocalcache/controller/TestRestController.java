package com.cxytiandi.kitty.samples.threadlocalcache.controller;

import com.cxytiandi.kitty.samples.threadlocalcache.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 *
 * 适用于同一个接口中调用了大量重复的方法或者RPC接口，
 * 不想改动代码，不想用全局缓存，只想对当前线程有效
 *
 * @作者 尹吉欢
 * @个人微信 jihuan900
 * @微信公众号 猿天地
 * @GitHub https://github.com/yinjihuan
 * @作者介绍 http://cxytiandi.com/about
 * @时间 2020-07-13 22:25
 */
@RestController
public class TestRestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public String test() {
        for (int i = 0; i < 10; i++) {
            testService.getName();
        }
        return "success";
    }

    @GetMapping("/test2")
    public String test2() {
        for (int i = 0; i < 10; i++) {
            testService.getName("1001");
        }
        return "success";
    }

}
