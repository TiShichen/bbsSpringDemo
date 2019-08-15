package demo.springboot.community.bbs.service;

import demo.springboot.community.bbs.dto.PaginationDTO;
import demo.springboot.community.bbs.dto.QuestionDTO;
import demo.springboot.community.bbs.mapper.QuestionMapper;
import demo.springboot.community.bbs.mapper.UserMapper;
import demo.springboot.community.bbs.model.Question;
import demo.springboot.community.bbs.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author ShichenLi
 * @Date 8/14/2019 14:55
 */
//service can be used to assemble two objects together, e.g. assemble user and question together
//when a request needs to access contents both in user table and question table, a service is needed to assemble question and user
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount, page, size);

        if (page < 1) {
            page = 1;
        }
        if (page > paginationDTO.getTotalPage()) {
            page = paginationDTO.getTotalPage();
        }

        // 5 * (i - 1) ==> size * (page - 1)
        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
//          questionDTO.setId(user.getId());
            BeanUtils.copyProperties(question, questionDTO);        // copy all the properties from question to questionDTO
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }
}
