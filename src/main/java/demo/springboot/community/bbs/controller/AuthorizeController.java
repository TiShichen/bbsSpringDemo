package demo.springboot.community.bbs.controller;

import demo.springboot.community.bbs.dto.AccessTokenDTO;
import demo.springboot.community.bbs.dto.GithubUser;
import demo.springboot.community.bbs.mapper.UserMapper;
import demo.springboot.community.bbs.model.User;
import demo.springboot.community.bbs.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;      // IoC, spring puts githubProvider into container in advance without request

    @Value("${github.client.id}")
    private String clientId;                    // Similar to @Autowired. Makes it easier to get config from application.properties for different apps
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")                    // Github return a URL ending with "/callback"
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null) {                     // If a not null user is returned, the login is successful
            // login is successful, proceed to create session and cookie
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);                    // put user info into database as a session
            response.addCookie(new Cookie("token", token)); //create cookie

//            request.getSession().setAttribute("user", githubUser);
            return "redirect:/";
        } else {
            // login failed, proceed to re-login
            return "redirect:/";            // Go back to index.html using "redirect" command
        }
    }
}
