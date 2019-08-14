package demo.springboot.community.bbs.dto;

import demo.springboot.community.bbs.model.User;
import lombok.Data;

/**
 * @Author ShichenLi
 * @Date 8/14/2019 14:52
 */
@Data
public class QuestionDTO {
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
    private User user;
}
