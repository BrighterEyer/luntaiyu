package com.zhangzhao.app.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Component
public class ScheduleTask {

    @Scheduled(cron = "0 0 0 1/1 * *")
    public void sendRed(){

    }
}
