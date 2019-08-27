package com.springboot.bbs.dto;

import com.springboot.bbs.model.User;
import lombok.Data;

/**
 * @Author ShichenLi
 * @Date 8/14/2019 14:52
 */
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
