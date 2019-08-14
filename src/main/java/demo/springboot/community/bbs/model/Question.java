package demo.springboot.community.bbs.model;

import lombok.Data;

/**
 * @Author ShichenLi
 * @Date 8/13/2019 9:19
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
}
