package com.springboot.bbs.dto;

import lombok.Data;

/**
 * @Author ShichenLi
 * @Date 8/23/2019 11:31
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String comment;
    private Integer type;
    private String content;
}
