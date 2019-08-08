package demo.springboot.community.bbs.provider;

import com.alibaba.fastjson.JSON;
import demo.springboot.community.bbs.dto.AccessTokenDTO;
import demo.springboot.community.bbs.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        // OkHttp POST request, 向Github提供指定用户的两项信息及网页相关的三项信息（包含在AccessTokenDTO中的五项String）
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")     // Github提供的接受token请求的POST路径
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];  // 将Github返回的String内容以此方式分割，以得到返还的access_token
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 使用从AuthorizeController中得到的accessToken向Github获取user
    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);     // 使用FastJSON的parseObject功能将获得的String转化为一个GithubUser对象
            return githubUser;
        } catch (IOException e) {
        }
        return null;
    }

}
