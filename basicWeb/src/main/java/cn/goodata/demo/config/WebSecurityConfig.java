package cn.goodata.demo.config;

import cn.goodata.demo.filter.ImageFilter;
import cn.goodata.demo.filter.MyAuthenticationFailureHandler;
import cn.goodata.demo.filter.MyAuthenticationSuccessHandler;
import cn.goodata.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static  String loginPage="/admin.jsp";
    public static String loginProcessingUrl="/loginCheck";

    @Autowired
    private UsersService userDatailService;
   // @Autowired
   // ImageFilter imageFilter;
    @Autowired
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().maximumSessions(1).expiredUrl("/expired.jsp");
        http.headers().frameOptions().sameOrigin()
                .and()
                .addFilterBefore(new ImageFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin().loginPage(loginPage).usernameParameter("username").passwordParameter("password").permitAll()// ???????????????????????????
                //.defaultSuccessUrl("/alogin/index")
                //.failureForwardUrl(loginPage)
                .failureUrl(loginPage)
                .failureHandler(myAuthenticationFailureHandler)
                .successHandler(myAuthenticationSuccessHandler)
                //.antMatchers(HttpMethod.POST, "/add-user").permitAll() // ??????post??????/add-user??????????????????
               // .anyRequest().authenticated() // ???????????????????????????
                .loginProcessingUrl(loginProcessingUrl)//????????????url
                .and()
                 .logout().logoutUrl("/logout").logoutSuccessUrl(loginPage)
                .and()
                .authorizeRequests()
                .mvcMatchers("/alogin/getImage").permitAll()//??????????????????  ???????????????
                .mvcMatchers("/a**/**").hasAuthority ("ROLE_Z") //???????????????????????????  ??????
                .and()
                .csrf().disable();// post???????????????csrf??????,?????????????????????????????????????????????????????????????????????????????????

    }


    /**
     * ??????????????????
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDatailService).passwordEncoder(passwordEncoder());
    }
}

