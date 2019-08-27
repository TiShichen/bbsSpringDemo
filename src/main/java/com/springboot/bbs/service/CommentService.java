package com.springboot.bbs.service;

import com.springboot.bbs.enums.CommentTypeEnum;
import com.springboot.bbs.exception.CustomizeErrorCode;
import com.springboot.bbs.exception.CustomizeException;
import com.springboot.bbs.mapper.CommentMapper;
import com.springboot.bbs.mapper.QuestionExtMapper;
import com.springboot.bbs.mapper.QuestionMapper;
import com.springboot.bbs.model.Comment;
import com.springboot.bbs.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author ShichenLi
 * @Date 8/23/2019 16:54
 */
@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Transactional      // Put the whole method into one Transaction(事务), so that the transaction only finish when all tasks finished successfully. It will rollback if any error or uncatched exception occurs.
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            // Reply to comment
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        } else {
            // Reply to question
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }

    }
}
