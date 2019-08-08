package demo.springboot.community.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    // 导引向首页
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
