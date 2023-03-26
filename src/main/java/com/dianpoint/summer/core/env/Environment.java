package com.dianpoint.summer.core.env;

/**
 * @author: github/ccoderJava
 * @email: congccoder@gmail.com
 * @date: 2023/3/23 22:21
 */
public interface Environment extends PropertyResolver {

    String[] getActiveProperties();

    String[] getDefaultProperties();

    boolean acceptsProperties(String... profiles);

}
