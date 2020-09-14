package com.cxytiandi.kitty.samples.threadpool.nacos.controller;

import com.cxytiandi.kitty.threadpool.DynamicThreadPoolManager;
import com.cxytiandi.kitty.threadpool.KittyThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 线程池测试
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
    private DynamicThreadPoolManager dynamicThreadPoolManager;

    @GetMapping("/test")
    public String test() {
        KittyThreadPoolExecutor threadPoolExecutor = dynamicThreadPoolManager.getThreadPoolExecutor("TestThreadPoolExecutor");
        threadPoolExecutor.execute(() -> {
            System.out.println("牛逼的线程池:" + Thread.currentThread().getName());
        }, "TestTask");

        return "success";
    }
}
