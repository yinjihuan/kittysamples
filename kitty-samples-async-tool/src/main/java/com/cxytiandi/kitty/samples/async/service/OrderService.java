package com.cxytiandi.kitty.samples.async.service;

import com.cxytiandi.kitty.samples.async.response.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    /**
     * 模拟查询，耗时1s
     * @param id
     * @return
     */
    public OrderResponse getOrder(String id) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        OrderResponse r = new OrderResponse();
        r.setOrderId("100021212121212");
        return r;
    }
}
