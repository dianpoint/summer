package com.dianpoint.summer.engines.batch.common;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/6/3 19:35
 */

public class AcceptResponse {

    private Long taskId;
    private String taskStatus;

    public AcceptResponse(Long taskId, String taskStatus) {
        this.taskId = taskId;
        this.taskStatus = taskStatus;
    }
}
