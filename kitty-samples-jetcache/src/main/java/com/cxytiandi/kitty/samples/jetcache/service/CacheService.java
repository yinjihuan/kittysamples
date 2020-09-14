package com.cxytiandi.kitty.samples.jetcache.service;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.cxytiandi.kitty.jetcache.CachePlus;
import com.cxytiandi.kitty.jetcache.Closure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @作者 尹吉欢
 * @个人微信 jihuan900
 * @微信公众号 猿天地
 * @GitHub https://github.com/yinjihuan
 * @作者介绍 http://cxytiandi.com/about
 * @时间 2020-08-05 21:18
 */
@Service
public class CacheService {

    @Autowired
    private CachePlus cachePlus;

    @Cached(name = "user:getName:", key = "#id", expire = 60)
    public String getName(int id) {
        System.out.println("开始查询了，ID=" + id);
        return "yinjihuan";
    }

    @Cached(name = "user:getName:", key = "#id", expire = 60, cacheType = CacheType.LOCAL)
    public String getNameByLocalCache(int id) {
        System.out.println("开始查询了，ID=" + id);
        return "yinjihuan";
    }

    public String getName2(int id) {
        return cachePlus.getCache("user:getName2:", id, inputId -> {
            System.out.println("开始查询了，ID=" + inputId);
            return "yinjihuan";
        }, 60, TimeUnit.SECONDS);
    }

    public String getName3(int id) {
        return cachePlus.getLocalCache("user:getName3:", id, inputId -> {
            System.out.println("开始查询了，ID=" + inputId);
            return "yinjihuan";
        }, 60, TimeUnit.SECONDS);
    }

}
