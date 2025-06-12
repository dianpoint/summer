package com.dianpoint.summer.engines.batch.service;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.*;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/6/3 19:26
 */

// TaskCoordinator.java
@Component
public class TaskCoordinator {
    private final ConcurrentMap<String, CompletableFuture<Void>> futures =
            new ConcurrentHashMap<>();

    public boolean waitForCompletion(String token, Duration timeout) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        futures.put(token, future);

        try {
            future.get(timeout.toMillis(), TimeUnit.MILLISECONDS);
            return true;
        } catch (TimeoutException e) {
            futures.remove(token);
            return false;
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public void notifyCompletion(String token) {
        CompletableFuture<Void> future = futures.remove(token);
        if (future != null) {
            future.complete(null);
        }
    }
}