package com.springboot.bbs.controller;

import com.springboot.bbs.dto.QuestionDTO;
import com.springboot.bbs.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author ShichenLi
 * @Date 8/19/2019 8:52
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model) {
        QuestionDTO questionDTO = questionService.getById(id);

        // increment view count
        questionService.incView(id);
        model.addAttribute("question", questionDTO);
        return "question";
    }
}
