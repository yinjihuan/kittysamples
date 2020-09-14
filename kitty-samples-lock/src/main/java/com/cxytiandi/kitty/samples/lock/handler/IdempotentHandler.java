package com.cxytiandi.kitty.samples.lock.handler;


import com.cxytiandi.kitty.lock.idempotent.exception.IdempotentException;

/**
 * @作者 尹吉欢
 * @个人微信 jihuan900
 * @微信公众号 猿天地
 * @GitHub https://github.com/yinjihuan
 * @作者介绍 http://cxytiandi.com/about
 * @时间 2020-07-20 22:13
 */
public class IdempotentHandler {

    public static void idempotentHandler(IdempotentException e) {
        System.out.println("idempotentHandler进来了。。。。");
    }

}
