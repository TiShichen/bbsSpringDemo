package com.springboot.bbs.exception;

/**
 * @Author ShichenLi
 * @Date 8/22/2019 10:40
 */
public class CustomizeException extends RuntimeException {      // Why extends RuntimeException: so that we do not need to try/catch in parent directory, we can just try/catch under this class(QuestionController)
    private String message;

    public CustomizeException(String message) {
        this.message = message;
    }

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

}
