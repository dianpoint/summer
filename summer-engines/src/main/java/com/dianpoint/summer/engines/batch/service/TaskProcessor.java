package com.dianpoint.summer.engines.batch.service;

import com.dianpoint.summer.engines.batch.dao.MainTaskDao;
import com.dianpoint.summer.engines.batch.dao.SubTaskDao;
import com.dianpoint.summer.engines.batch.entity.SubTask;
import com.dianpoint.summer.engines.batch.enums.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/6/3 19:36
 */

@Service
public class TaskProcessor {

    @Autowired
    private SubTaskDao subTaskDao;
    @Autowired
    private MainTaskDao mainTaskDao;

    public void process(SubTask task) {

        try {
            // 模拟业务处理
            Thread.sleep(1000);

            // 业务逻辑执行成功

            task.setTaskStatus(TaskStatus.SUCCESS);

            task.setResult("{\"success\":true}");

        } catch (Exception e) {
            handleTaskFailure(task, e);
        } finally {

            // 乐观锁更新

            if (!subTaskDao.updateWithVersion(task)) {

                // 更新失败，记录日志或重试
            }
            // 更新主任务进度
//            mainTaskDao.updateMainTaskProgress(task.getMainTaskId());

        }

    }

    public void handleTaskFailure(SubTask task, Throwable ex) {

        task.setTaskStatus(TaskStatus.FAILED);

        task.setResult("{\"error\":\"" + ex.getMessage() + "\"}");
    }


}
