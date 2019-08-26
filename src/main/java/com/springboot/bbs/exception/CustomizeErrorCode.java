package com.springboot.bbs.exception;

/**
 * @Author ShichenLi
 * @Date 8/22/2019 10:57
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001,"Looks like the question you are looking for does not exist, it did not survive the nuke!!! Try look for another question?"),
    TARGET_PARAM_NOT_FOUND(2002, "You did not select any question or comment to reply, you need to select a target even you use nukes."),
    NOT_LOGGED_IN(2003, "User haven't logged in, we do not allow users to comment without logging in, for nuclear security reason."),
    SYS_ERROR(2004, "Looks like your requested service was nuked, come back and try again later?"),
    TYPE_PARAM_WRONG(2005, "Comment type wrong or does not exist, to get this error, you must be trying something nuclear, PLEASE DONT'T NUKE US."),
    COMMENT_NOT_FOUND(2006, "Looks like the comment you are replying to does not exist, it did not survive the nuke!!! Try look for another question?"),

    ;


    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


}
