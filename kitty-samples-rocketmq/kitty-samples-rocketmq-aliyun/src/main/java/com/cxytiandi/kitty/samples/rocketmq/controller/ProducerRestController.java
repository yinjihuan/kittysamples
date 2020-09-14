package com.cxytiandi.kitty.samples.rocketmq.controller;

import com.cxytiandi.kitty.rocketmq.RocketMqProducer;
import com.cxytiandi.kitty.samples.rocketmq.message.OrderMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class ProducerRestController {

    @Autowired
    private RocketMqProducer rocketMQProducer;

    @GetMapping("/send")
    public String send() {
        rocketMQProducer.sendTransactionMessage("trade", "beta", "1001");
        return "success";
    }

    @GetMapping("/send2")
    public String send2() {
        rocketMQProducer.sendTransactionMessage("trade", "beta", "1001","1001");
        return "success";
    }

    @GetMapping("/send3")
    public String send3() {
        rocketMQProducer.sendTransactionMessage("trade", "beta", "1001","1001");
        return "success";
    }

    @GetMapping("/send4")
    public String send4() {
        rocketMQProducer.sendTransactionMessage("trade", "beta", "1001", new OrderMessage("1001"));
        return "success";
    }

    @GetMapping("/send5")
    public String send5() {
        rocketMQProducer.sendTransactionDelayMessage("trade", "beta", "1001", 10, TimeUnit.HOURS);
        return "success";
    }

}
