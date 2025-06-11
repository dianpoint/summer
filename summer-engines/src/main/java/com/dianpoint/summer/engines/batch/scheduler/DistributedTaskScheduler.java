package com.dianpoint.summer.engines.batch.scheduler;

import com.dianpoint.summer.engines.batch.dao.SubTaskDao;
import com.dianpoint.summer.engines.batch.entity.SubTask;
import com.dianpoint.summer.engines.batch.service.TaskProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/6/3 19:28
 */

// DistributedTaskScheduler.java
@Component
public class DistributedTaskScheduler {
    private final String instanceId = UUID.randomUUID().toString();


    @Autowired
    private TaskProcessor taskProcessor;


    @Autowired
    private SubTaskDao subTaskDao;

    @Scheduled(fixedDelay = 3000)
    @Transactional(rollbackFor = Exception.class)
    public void scheduleTasks() {
        List<SubTask> tasks = subTaskDao.lockTasks(20, instanceId);
        tasks.forEach(task -> {
            // 异步处理确保事务及时提交
            CompletableFuture.runAsync(() -> taskProcessor.process(task))
                    .exceptionally(ex -> {
                        taskProcessor.handleTaskFailure(task, ex);
                        return null;
                    });
        });
    }
}