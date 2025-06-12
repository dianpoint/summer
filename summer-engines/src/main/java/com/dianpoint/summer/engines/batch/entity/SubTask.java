package com.dianpoint.summer.engines.batch.entity;

import com.dianpoint.summer.engines.batch.enums.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/6/3 19:22
 */
@Data
public class SubTask {

    private Long id;
    private Long mainTaskId;
    private Integer shardIndex;
    private String shardKey;
    private TaskStatus taskStatus;
    private Integer executeCount;
    private String result;
    private String instanceId;
    private Integer version;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
