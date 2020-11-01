package com.cxytiandi.kitty.samples.async.controller;

import com.cxytiandi.kitty.async.AsyncCall;
import com.cxytiandi.kitty.async.AsyncTemplate;
import com.cxytiandi.kitty.async.UserQuery;
import com.cxytiandi.kitty.samples.async.response.OrderResponse;
import com.cxytiandi.kitty.samples.async.response.UserResponse;
import com.cxytiandi.kitty.samples.async.service.GoodsService;
import com.cxytiandi.kitty.samples.async.service.OrderService;
import com.cxytiandi.kitty.samples.async.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
public class AsyncRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    public static final ThreadPoolExecutor COMMON_POOL =
            new ThreadPoolExecutor(64, 1024,
                    15L, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(),
                    (ThreadFactory) Thread::new);

    /**
     * 适用于: 比如从ES查出了一批ID,然后根据ID去数据库中查询真实数据
     * @return
     */
    @GetMapping("/agg")
    public Object aggregationApi() {
        long s = System.currentTimeMillis();
        List<String> ids = new ArrayList<>();
        ids.add("1");
        ids.add("2");
        ids.add("3");
        Map<String, UserResponse> callResult = AsyncTemplate.call(ids, id -> {
            return userService.getUser(id);
        }, u -> u.getId(), COMMON_POOL);
        long e = System.currentTimeMillis();
        System.out.println("耗时：" + (e-s) + "ms");
        return "";
    }

    /**
     * 适用于：并发调用多个不同的接口，接口之间没有依赖关系
     * @return
     */
    @GetMapping("/agg2")
    public Object aggregationApi2() {
        long s = System.currentTimeMillis();
        List<AsyncCall> params = new ArrayList<>();
        AsyncCall<Integer, Integer> goodsQuery = new AsyncCall("goodsQuery", 1);
        params.add(goodsQuery);

        AsyncCall<String, OrderResponse> orderQuery = new AsyncCall("orderQuery", "100");
        params.add(orderQuery);

        UserQuery q = new UserQuery();
        q.setAge(18);
        q.setName("yinjihuan");
        AsyncCall<UserQuery, UserResponse> userQuery = new AsyncCall("userQuery", q);
        params.add(userQuery);

        AsyncTemplate.call(params, p -> {
            if (p.getTaskId().equals("goodsQuery")) {
                AsyncCall<Integer, Integer> query = p;
                return goodsService.getGoodsName(query.getParam());
            }

            if (p.getTaskId().equals("orderQuery")) {
                AsyncCall<String, OrderResponse> query = p;
                return orderService.getOrder(query.getParam());
            }

            if (p.getTaskId().equals("userQuery")) {
                AsyncCall<UserQuery, UserResponse> query = p;
                return userService.getUser(query.getParam());
            }
            return null;
        });

        long e = System.currentTimeMillis();
        System.out.println("耗时：" + (e-s) + "ms");
        return params;
    }

}
