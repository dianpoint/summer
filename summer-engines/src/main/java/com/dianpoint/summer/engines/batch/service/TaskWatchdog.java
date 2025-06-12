package com.dianpoint.summer.engines.batch.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/6/3 19:31
 */

@Slf4j
@Component
public class TaskWatchdog {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Scheduled(fixedRate = 60000) // 每分钟检查
    public void recoverStuckTasks() {
        // 查找超过30分钟未更新的处理中任务
        String sql = "UPDATE batch_sub_task " +
                "SET task_status = 0, instance_id = NULL, " +
                "execute_count = execute_count + 1 " +
                "WHERE task_status = 1 AND update_time < NOW() - INTERVAL 30 MINUTE";

        int recovered = jdbcTemplate.update(sql);
        if (recovered > 0) {
            log.info("Recovered {} stuck tasks", recovered);
        }
    }
}
