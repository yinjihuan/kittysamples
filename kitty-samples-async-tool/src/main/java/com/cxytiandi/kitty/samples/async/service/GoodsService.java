package com.cxytiandi.kitty.samples.async.service;

import org.springframework.stereotype.Service;

@Service
public class GoodsService {

    /**
     * 模拟查询，耗时1s
     * @param id
     * @return
     */
    public String getGoodsName(Integer id) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "测试商品";
    }
}
