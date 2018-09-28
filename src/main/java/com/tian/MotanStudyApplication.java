package com.tian;

import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MotanStudyApplication {

    /**
     * 启动服务的方法
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        SpringApplication.run(MotanStudyApplication.class, args);

        // 改成用配置中心的形式,则要添加一个心跳控制
        MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true);

        System.out.println("server start...");
    }
}
