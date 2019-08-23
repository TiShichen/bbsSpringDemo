package com.springboot.bbs.service;

import com.springboot.bbs.exception.CustomizeErrorCode;
import com.springboot.bbs.exception.CustomizeException;
import com.springboot.bbs.model.Comment;
import org.springframework.stereotype.Service;

/**
 * @Author ShichenLi
 * @Date 8/23/2019 16:54
 */
@Service
public class CommentService {
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
    }
}
