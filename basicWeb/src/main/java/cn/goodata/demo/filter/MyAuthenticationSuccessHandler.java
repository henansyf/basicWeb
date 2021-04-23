package cn.goodata.demo.filter;

import cn.goodata.demo.entity.Users;
import cn.goodata.demo.service.UsersService;
import cn.goodata.demo.util.Constants;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private String successStr="{\"status\":1}";
    @Autowired
    private UsersService usersService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Users admin = (Users) authentication.getPrincipal();
        admin.setLoginDate(new Date());
        if (Constants.isLoginFailureLock == false) {
            return;
        }
        admin.setLoginFailureCount(0);
        usersService.updateById(admin);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().append(successStr);
    }
}
