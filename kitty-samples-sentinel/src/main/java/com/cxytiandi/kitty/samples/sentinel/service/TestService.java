package com.cxytiandi.kitty.samples.sentinel.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @SentinelResource(value = "test-resource", blockHandler = "handlerException", blockHandlerClass = TestService.class)
    public void test() {

    }

    public void handlerException(BlockException e) {
        System.out.println("异常了");
    }
}
