package com.dianpoint.summer.test.validator.cases;

import com.dianpoint.summer.validation.annotations.NotNull;
import com.dianpoint.summer.validation.annotations.Pattern;

/**
 * @author: congccoder
 * @email: congccoder@gmail.com | <a href="https://github.com/ccoderJava">github-homepage</a>
 * @date: 2025/5/20 21:52
 */

public class User {
    @NotNull(message = "用户名不能为空")
    private String username;

    @Pattern(regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "邮箱地址不正确")
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
