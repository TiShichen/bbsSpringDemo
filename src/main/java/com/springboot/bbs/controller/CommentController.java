package com.springboot.bbs.controller;

import com.springboot.bbs.dto.CommentCreateDTO;
import com.springboot.bbs.dto.ResultDTO;
import com.springboot.bbs.exception.CustomizeErrorCode;
import com.springboot.bbs.model.Comment;
import com.springboot.bbs.model.User;
import com.springboot.bbs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ShichenLi
 * @Date 8/23/2019 11:10
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody       // @ResponseBody can parse an object to JSON then send to frontend
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request) {        // @RequestBody can parse JSON to an object

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NOT_LOGGED_IN);
        }

        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
