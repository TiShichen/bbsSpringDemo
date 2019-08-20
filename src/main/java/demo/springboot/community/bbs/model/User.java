package demo.springboot.community.bbs.model;

import lombok.Data;

/**
 * @Author: ShichenLi
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String account;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
    private String bio;
}
