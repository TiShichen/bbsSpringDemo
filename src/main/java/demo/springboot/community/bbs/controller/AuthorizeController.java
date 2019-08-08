package demo.springboot.community.bbs.controller;

import demo.springboot.community.bbs.dto.AccessTokenDTO;
import demo.springboot.community.bbs.dto.GithubUser;
import demo.springboot.community.bbs.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;      // 控制反转，spring提前将对象 githubProvider放入container

    @Value("${github.client.id}")
    private String clientId;                    // 与@Autowired类似。可根据application.properties文件方便各个用户读取信息
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")                    // Github将以"/callback"结尾的url请求返回
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        if (user != null) {                     // 如果成功返回了非空user，说明Github登录成功
            // login is successful, proceed to create session and cookie
            request.getSession().setAttribute("user", user);
            return "redirect:index";
        } else {
            // login failed, proceed to re-login
            return "redirect:index";            // 使用"redirect"指令，使返回网站重定向至index URL
        }
    }
}
