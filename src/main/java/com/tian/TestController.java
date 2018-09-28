package com.tian;

import com.weibo.api.motan.config.springsupport.annotation.MotanReferer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tom on 2018/9/28.
 */
@RestController
@RequestMapping("test")
public class TestController {

    @MotanReferer(basicReferer = "basicRefererConfigBean")
    private IDemoService demoService;

    @RequestMapping(value = "hello")
    public String hello(String context) throws Exception {
        return demoService.get(context);
    }

}
