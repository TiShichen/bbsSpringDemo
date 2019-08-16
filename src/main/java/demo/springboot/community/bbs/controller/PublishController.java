package demo.springboot.community.bbs.controller;

import demo.springboot.community.bbs.mapper.QuestionMapper;
import demo.springboot.community.bbs.model.Question;
import demo.springboot.community.bbs.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author ShichenLi
 * @Date 8/12/2019 14:21
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            HttpServletRequest request,
            Model model) {

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

/*        if (title == null || title == "") {
            model.addAttribute("error", "Title cannot be empty.");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "Description needs more detail than being empty :)");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "Please add at least one tag, any tag will work.");
            return "publish";
        }*/

        /*User user = null;
        Cookie[] cookies = request.getCookies();
*//*
        if (cookies == null) {
            model.addAttribute("error", "User not yet signed in.");
            return "publish";
        }
*//*
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }*/

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            model.addAttribute("error", "User not signed in.");
            return "publish";
        }

        if (title == null || title == "") {
            model.addAttribute("error", "Title cannot be empty.");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "Description needs to be more detailed than empty :)");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "Please add at least one tag, any tag will work.");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());

        questionMapper.create(question);
        return "redirect:/";
    }
}
