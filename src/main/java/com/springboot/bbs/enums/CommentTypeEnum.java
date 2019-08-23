package com.springboot.bbs.enums;

/**
 * @Author ShichenLi
 * @Date 8/23/2019 16:52
 */
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private Integer type;

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
