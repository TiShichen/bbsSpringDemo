package demo.springboot.community.bbs.controller;

import demo.springboot.community.bbs.dto.QuestionDTO;
import demo.springboot.community.bbs.model.Question;
import demo.springboot.community.bbs.model.User;
import demo.springboot.community.bbs.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "id", required = false) Integer id,
            HttpServletRequest request,
            Model model) {

        // Echo back inputted content
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        User user = (User) request.getSession().getAttribute("user");
        // User can only post new question after logging in
        if (user == null) {
            model.addAttribute("error", "User not signed in.");
            return "publish";
        }

        // Title, description and tag fields cannot be empty
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

        // Create a question and insert it into database, table "question"
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(id);

        questionService.createOrUpdate(question);
        return "redirect:/";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                       Model model) {
        QuestionDTO question = questionService.getById(id);
        // Echo back inputted content, in this case, the content being echoed is from the database
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());

        return "publish";
    }
}
