package cn.goodata.demo.filter;

import cn.goodata.demo.entity.Users;
import cn.goodata.demo.service.UsersService;
import cn.goodata.demo.util.Constants;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static  String  autherMsg ="autherMsg";
    @Autowired
    private UsersService usersService;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",2);
        //这里处理登录失败后就会输出错误信息
        if (exception instanceof BadCredentialsException) {
            String loginUsername = request.getParameter("username");
            if (Constants.isLoginFailureLock == false) {
                return;
            }
            Users admin = usersService.findUserByLoginName(loginUsername);
            if (admin != null) {
                int loginFailureCount = admin.getLoginFailureCount() + 1;
                if (loginFailureCount >= Constants.loginFailureLockCount) {
                    admin.setIsAccountNonLocked(false);
                    admin.setLockedDate(new Date());
                }
                admin.setLoginFailureCount(loginFailureCount);
                usersService.updateById(admin);
                if (Constants.isLoginFailureLock && Constants.loginFailureLockCount - loginFailureCount <= 3) {
                    jsonObject.put("msg", "若连续" + Constants.loginFailureLockCount + "次密码输入错误,您的账号将被锁定!");
                }else{
                    jsonObject.put("msg","密码错误");
                }
            }else{
                jsonObject.put("msg","用户名错误");
            }

        } else if (exception instanceof LockedException) {
            jsonObject.put("msg","您的账号已被锁定,无法登录");
        } else if (exception instanceof AccountExpiredException) {
            jsonObject.put("msg","您的账号已过期,无法登录!");
        } else if (exception instanceof UsernameNotFoundException) {
            jsonObject.put("msg","用户名错误!");
        }
        response.getWriter().append(jsonObject.toJSONString());

    }


}
