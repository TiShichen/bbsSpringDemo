package com.springboot.bbs.mapper;

import com.springboot.bbs.model.Question;
import com.springboot.bbs.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);
}