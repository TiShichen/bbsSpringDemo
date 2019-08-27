package com.springboot.bbs.mapper;

import com.springboot.bbs.model.Question;

public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
}