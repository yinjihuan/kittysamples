package com.cxytiandi.kitty.samples.jetcache;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @作者 尹吉欢
 * @个人微信 jihuan900
 * @微信公众号 猿天地
 * @GitHub https://github.com/yinjihuan
 * @作者介绍 http://cxytiandi.com/about
 * @时间 2020-08-05 21:14
 */
@EnableCreateCacheAnnotation
@EnableMethodCache(basePackages = "com.cxytiandi")
@SpringBootApplication
public class JetCacheApp {
    public static void main(String[] args) {
        SpringApplication.run(JetCacheApp.class);
    }
}
