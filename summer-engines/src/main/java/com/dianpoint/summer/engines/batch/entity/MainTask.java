package com.dianpoint.summer.engines.batch.entity;

import com.dianpoint.summer.engines.batch.enums.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/6/3 19:16
 */
@Data
public class MainTask {
    private Long id;
    private String bizType;
    private TaskStatus taskStatus;
    private Integer totalShards;
    private Integer finishedShards;
    private String params;
    private String completionToken;
    private LocalDateTime expireTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
