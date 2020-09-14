package com.cxytiandi.kitty.samples.sentinel.handler;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@ConditionalOnClass(UrlBlockHandler.class)
@Configuration
public class UrlBlockHandlerAutoConfigure {

    @PostConstruct
    public void init() {
        WebCallbackManager.setUrlBlockHandler(new CustomUrlBlockHandler());
    }

}