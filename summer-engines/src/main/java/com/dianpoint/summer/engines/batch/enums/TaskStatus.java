package com.dianpoint.summer.engines.batch.enums;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/6/3 19:16
 */

public enum TaskStatus {
    PENDING(0),     // 待处理

    PROCESSING(1),  // 处理中

    SUCCESS(2),     // 成功

    FAILED(3);      // 失败

    private final int value;

    TaskStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
