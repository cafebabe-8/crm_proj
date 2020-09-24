package org.crm.exception;


// 自定义异常
public class LoginException extends Exception {
    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }
}
