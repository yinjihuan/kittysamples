package com.cxytiandi.kitty.samples.lock.service;

import com.cxytiandi.kitty.lock.DistributedLock;
import com.cxytiandi.kitty.lock.DistributedLockMysql;
import com.cxytiandi.kitty.lock.idempotent.DistributedIdempotent;
import com.cxytiandi.kitty.lock.idempotent.aspect.Idempotent;
import com.cxytiandi.kitty.lock.idempotent.enums.ReadWriteTypeEnum;
import com.cxytiandi.kitty.lock.idempotent.exception.IdempotentException;
import com.cxytiandi.kitty.samples.lock.handler.IdempotentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

/**
 * @作者 尹吉欢
 * @个人微信 jihuan900
 * @微信公众号 猿天地
 * @GitHub https://github.com/yinjihuan
 * @作者介绍 http://cxytiandi.com/about
 * @时间 2020-07-17 22:38
 */
@Service
public class TestService {

    /**
     * 优先Redis锁，失败会降级为Mysql锁
     */
    @Autowired
    private DistributedLock distributedLock;

    @Autowired
    private DistributedLockMysql distributedLockMysql;

    @Lazy
    @Autowired
    private DistributedIdempotent distributedIdempotent;

    /**
     * 加锁不了快速失败
     */
    public void lockFailFast() {
        distributedLock.lock("1001", 1000, () -> {
            System.out.println("进来了。。。。");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, () -> {
            System.out.println("加锁失败。。。。");
        });
    }

    /**
     * 加锁不了，等待加锁成功，超过waitTime就不等待
     */
    public void lockByWait() {
        distributedLock.lock("1001", 10000, 1000, () -> {
            System.out.println("进来了。。。。");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, () -> {
            System.out.println("加锁失败。。。。");
        });
    }

    /**
     * 直接使用Mysql锁
     */
    public void lockMysql() {
        distributedLockMysql.lock("1001", 10000, 1000, () -> {
            System.out.println("进来了。。。。");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, () -> {
            System.out.println("加锁失败。。。。");
        });
    }

    /**
     * 幂等只提供短时间内重复请求的防止幂等操作
     * 核心业务还是要在数据表中做幂等
     * 幂等的二级存储还没实现，理论上二级存储支持更长的时间存储，防止超长时间的幂等，能在业务中直接做的建议在业务中，比如订单的支付，根据支付记录做
     */
    @Idempotent(idempotentHandler = "idempotentHandler", idempotentHandlerClass = IdempotentHandler.class)
    public void idempotent() {
        System.out.println("进来了。。。。");
    }

    /**
     * 注解方式幂等-指定幂等规则触发后执行的方法
     * @param key
     */
    @Idempotent(spelKey = "#key", idempotentHandler = "idempotentHandler", readWriteType = ReadWriteTypeEnum.PARALLEL, secondLevelExpireTime = 60)
    public void idempotent(String key) {
        System.out.println("进来了。。。。");
    }

    public void idempotentHandler(String key, IdempotentException e) {
        System.out.println(key + ":idempotentHandler已经执行过了。。。。");
    }

    /**
     * 代码方式幂等-有返回值
     * @param key
     * @return
     */
    public String idempotentCode(String key) {
        return distributedIdempotent.execute(key, 10, 10, 50, TimeUnit.SECONDS, ReadWriteTypeEnum.ORDER, () -> {
            System.out.println("进来了。。。。");
            return "success";
        }, () -> {
            System.out.println("重复了。。。。");
            return "fail";
        });
    }

    /**
     * 代码方式幂等-无返回值
     * @param key
     */
    public void idempotentCode2(String key) {
        distributedIdempotent.execute(key, 10, 10, 50, TimeUnit.SECONDS, () -> {
            System.out.println("进来了。。。。"+Thread.currentThread().getName());
        }, () -> {
            System.out.println("重复了。。。。"+Thread.currentThread().getName());
        });
    }
}
