package com.dianpoint.summer.validator.utils;

import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/5/22 21:21
 */

public class ClassScanner {

    public Set<Class<?>> scan(String packageName) {

        Set<Class<?>> classes = new HashSet<>();
        String path = packageName.replace('.', '/');
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL resource = classLoader.getResource(path);
            if (resource == null) {
                return classes;
            }
            File directory = new File(resource.getFile());
            if (directory.exists()) {
                for (File file : directory.listFiles()) {
                    if (file.isDirectory()) {
                        classes.addAll(scan(packageName + "." + file.getName()));
                    } else if (file.getName().endsWith(".class")) {
                        String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                        classes.add(Class.forName(className));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("扫描失败", e);
        }
        return classes;
    }
}
