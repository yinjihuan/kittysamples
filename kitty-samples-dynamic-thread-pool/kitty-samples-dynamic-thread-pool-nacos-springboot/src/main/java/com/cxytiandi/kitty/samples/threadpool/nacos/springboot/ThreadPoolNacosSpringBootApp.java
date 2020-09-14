package com.cxytiandi.kitty.samples.threadpool.nacos.springboot;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @作者 尹吉欢
 * @个人微信 jihuan900
 * @微信公众号 猿天地
 * @GitHub https://github.com/yinjihuan
 * @作者介绍 http://cxytiandi.com/about
 * @时间 2020-07-14 21:35
 */
@NacosPropertySource(dataId = "kitty-cloud-thread-pool.properties", groupId = "BIZ_GROUP", autoRefreshed = true)
@SpringBootApplication
public class ThreadPoolNacosSpringBootApp {

    public static void main(String[] args) {
        SpringApplication.run(ThreadPoolNacosSpringBootApp.class, args);
    }

}
