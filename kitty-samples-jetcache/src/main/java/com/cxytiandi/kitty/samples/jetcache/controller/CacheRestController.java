package com.cxytiandi.kitty.samples.jetcache.controller;

import com.cxytiandi.kitty.samples.jetcache.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @作者 尹吉欢
 * @个人微信 jihuan900
 * @微信公众号 猿天地
 * @GitHub https://github.com/yinjihuan
 * @作者介绍 http://cxytiandi.com/about
 * @时间 2020-08-05 21:19
 */
@RestController
public class CacheRestController {

    @Autowired
    private CacheService cacheService;

    @GetMapping("/getName")
    public String getName(int id) {
        return cacheService.getName(id);
    }

    @GetMapping("/getNameByLocalCache")
    public String getNameByLocalCache(int id) {
        return cacheService.getNameByLocalCache(id);
    }

    @GetMapping("/getName2")
    public String getName2(int id) {
        return cacheService.getName2(id);
    }

    @GetMapping("/getName3")
    public String getName3(int id) {
        return cacheService.getName3(id);
    }

}
