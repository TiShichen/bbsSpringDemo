package com.springboot.bbs.dto;

import com.springboot.bbs.model.User;
import lombok.Data;

/**
 * @Author ShichenLi
 * @Date 8/28/2019 17:01
 */
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private Long commentCount;
    private User user;
}
