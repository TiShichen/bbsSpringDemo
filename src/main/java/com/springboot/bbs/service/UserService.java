package com.springboot.bbs.service;

import com.springboot.bbs.mapper.UserMapper;
import com.springboot.bbs.model.User;
import com.springboot.bbs.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ShichenLi
 * @Date 8/19/2019 15:04
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountEqualTo(user.getAccount());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0) {
            // insert
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        } else {
            //update
            User dbUser = users.get(0);

            User updateUser = new User();
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            updateUser.setBio(user.getBio());

            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo(dbUser.getId());
            userMapper.updateByExampleSelective(updateUser, example);
        }
    }
}
