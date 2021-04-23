package cn.goodata.demo.filter;



import cn.goodata.demo.config.WebSecurityConfig;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ImageFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (WebSecurityConfig.loginProcessingUrl.equalsIgnoreCase(request.getRequestURI()) && "post".equalsIgnoreCase(request.getMethod())) {
            String yanzhengm = request.getParameter("validatecode");          //取出表单提交中的验证码信息
            String sessionyanz = (String)request.getSession(true).getAttribute("validatecode");
            if(yanzhengm!=null&&yanzhengm.equalsIgnoreCase(sessionyanz)){
                request.getSession().removeAttribute("validatecode");
                filterChain.doFilter(request, response);
            }else{
                response.setContentType("application/json;charset=utf-8");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("status",2);
                jsonObject.put("msg","验证码输入错误");
                response.getWriter().append(jsonObject.toJSONString());
            }
        }else{
            filterChain.doFilter(request, response);
        }

         //测试
       // filterChain.doFilter(request, response);

    }
}
