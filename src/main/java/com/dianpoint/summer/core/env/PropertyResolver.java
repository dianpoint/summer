package com.dianpoint.summer.core.env;

/**
 * @author: github/ccoderJava
 * @email: congccoder@gmail.com
 * @date: 2023/3/23 22:20
 */
public interface PropertyResolver {

    boolean containsProperty(String key);

    String getProperty(String key);

    String getProperty(String key, String defaultValue);
}
