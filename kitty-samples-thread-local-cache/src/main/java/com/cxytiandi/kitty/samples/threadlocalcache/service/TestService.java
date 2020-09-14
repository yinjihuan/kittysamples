package com.cxytiandi.kitty.samples.threadlocalcache.service;

import com.cxytiandi.kitty.threadlocal.cache.ThreadLocalCache;
import org.springframework.stereotype.Service;

/**
 * 测试业务类
 *
 * @作者 尹吉欢
 * @个人微信 jihuan900
 * @微信公众号 猿天地
 * @GitHub https://github.com/yinjihuan
 * @作者介绍 http://cxytiandi.com/about
 * @时间 2020-07-13 22:27
 */
@Service
public class TestService {

    /**
     * ThreadLocalCache 会缓存，只对当前线程有效
     * @return
     */
    @ThreadLocalCache
    public String getName() {
        System.out.println("开始查询了");
        return "yinjihaun";
    }

    /**
     * 支持SPEL表达式
     * @param id
     * @return
     */
    @ThreadLocalCache(key = "#id")
    public String getName(String id) {
        System.out.println("开始查询了");
        return "yinjihaun" + id;
    }
}
