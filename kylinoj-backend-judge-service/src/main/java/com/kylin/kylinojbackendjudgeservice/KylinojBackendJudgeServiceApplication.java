package com.kylin.kylinojbackendjudgeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.kylin")
public class KylinojBackendJudgeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KylinojBackendJudgeServiceApplication.class, args);
    }

}
