package demo.springboot.community.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author ShichenLi
 * @Date 8/12/2019 14:21
 */
@Controller
public class PublishController {
    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }
}
