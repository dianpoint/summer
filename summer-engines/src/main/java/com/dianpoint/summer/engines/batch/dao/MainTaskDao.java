package com.dianpoint.summer.engines.batch.dao;

import com.dianpoint.summer.engines.batch.entity.MainTask;
import com.dianpoint.summer.engines.batch.enums.TaskStatus;
import com.dianpoint.summer.engines.batch.service.TaskCoordinator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/6/3 19:25
 */

@Repository
public class MainTaskDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TaskCoordinator taskCoordinator;

    public void insert(MainTask task) {
        String sql = "INSERT INTO batch_main_task (biz_type, task_status, total_shards, finished_shards, params, completion_token, expire_time) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                task.getBizType(),
                task.getTaskStatus().getValue(),
                task.getTotalShards(),
                task.getFinishedShards(),
                task.getParams(),
                task.getCompletionToken(),
                task.getExpireTime());
        // 设置ID
        Long id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        task.setId(id);
    }

    public void setCompletionToken(Long taskId, String token, LocalDateTime expireTime) {
        String sql = "UPDATE batch_main_task SET completion_token = ?, expire_time = ? WHERE id = ?";
        jdbcTemplate.update(sql, token, expireTime, taskId);
    }

    public void clearCompletionToken(Long taskId) {
        String sql = "UPDATE batch_main_task SET completion_token = NULL, expire_time = NULL WHERE id = ?";
        jdbcTemplate.update(sql, taskId);

    }

    // MainTaskDao.java
    @Transactional
    public void onMainTaskComplete(Long taskId) {
        // 更新主任务状态
        jdbcTemplate.update(
                "UPDATE batch_main_task SET task_status=2, update_time=NOW() " +
                        "WHERE id=? AND finished_shards >= total_shards",
                taskId
        );

        // 触发阻塞调用完成
        MainTask task = getById(taskId);
        if (task.getCompletionToken() != null) {
            taskCoordinator.notifyCompletion(task.getCompletionToken());
            clearCompletionToken(taskId);
        }
    }

    public MainTask getById(Long taskId) {
        String sql = "SELECT * FROM batch_main_task WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, MainTask.class, taskId);
    }

    public TaskStatus getStatus(Long taskId) {
        String sql = "SELECT * FROM batch_main_task WHERE id = ?";
        MainTask mainTask = jdbcTemplate.queryForObject(sql, MainTask.class, taskId);
        return mainTask.getTaskStatus();
    }

}