package com.dianpoint.summer.engines.batch.service;

import com.dianpoint.summer.engines.batch.common.AcceptResponse;
import com.dianpoint.summer.engines.batch.dao.MainTaskDao;
import com.dianpoint.summer.engines.batch.entity.MainTask;
import com.dianpoint.summer.engines.batch.enums.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/6/3 19:24
 */

// TaskAcceptService.java
@Service
public class TaskAcceptService {
    @Autowired
    private MainTaskDao mainTaskDao;
    @Autowired
    private TaskCoordinator coordinator;

    // 非阻塞模式
    public Long acceptAsync(String bizType, String params) {
        MainTask task = createMainTask(bizType, params);
        return task.getId();
    }

    // 阻塞模式（带超时）
    public AcceptResponse acceptSync(String bizType, String params, Duration timeout) {
        MainTask task = createMainTask(bizType, params);
        String token = UUID.randomUUID().toString();
        mainTaskDao.setCompletionToken(task.getId(), token, LocalDateTime.now().plus(timeout));

        boolean completed = coordinator.waitForCompletion(token, timeout);
        TaskStatus status = mainTaskDao.getStatus(task.getId());

        return new AcceptResponse(task.getId(),
                completed ? status.name() : "TIMEOUT"
        );
    }

    private MainTask createMainTask(String bizType, String params) {
        // 创建主任务和子任务逻辑
        return null;
    }
}