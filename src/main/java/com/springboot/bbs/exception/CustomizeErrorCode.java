package com.springboot.bbs.exception;

/**
 * @Author ShichenLi
 * @Date 8/22/2019 10:57
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND("Looks like the question you are looking for does not exist, it did not survive the nuke!!!. Try look for another question?");

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
