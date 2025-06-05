package com.dianpoint.summer.engines.batch.dao;

import com.dianpoint.summer.engines.batch.entity.SubTask;
import com.dianpoint.summer.engines.batch.enums.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/6/3 19:23
 */

// SubTaskDao.java
@Repository
public class SubTaskDao {
    private static final String LOCK_SQL =
            "SELECT * FROM batch_sub_task WHERE task_status = 0 ORDER BY id LIMIT ? FOR UPDATE SKIP LOCKED";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<SubTask> lockTasks(int limit, String instanceId) {
        return jdbcTemplate.query(LOCK_SQL, (rs, rowNum) -> {
            SubTask task = new SubTask();
            // ...字段映射
            task.setTaskStatus(TaskStatus.PROCESSING);
            task.setInstanceId(instanceId);
            return task;
        }, limit);
    }

    @Transactional
    public boolean updateWithVersion(SubTask task) {
        String sql = "UPDATE batch_sub_task SET task_status=?, result=?, "
                + "execute_count=?, instance_id=?, version=version+1 "
                + "WHERE id=? AND version=?";
        int updated = jdbcTemplate.update(sql,
                task.getTaskStatus().getValue(),
                task.getResult(),
                task.getExecuteCount(),
                task.getInstanceId(),
                task.getId(),
                task.getVersion());
        return updated > 0;
    }
}