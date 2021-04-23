package cn.goodata.demo.service;

import cn.goodata.demo.dao.RoleDao;
import cn.goodata.demo.dao.UsersDao;
import cn.goodata.demo.entity.Question;
import cn.goodata.demo.entity.Role;
import cn.goodata.demo.entity.Users;
import cn.goodata.demo.util.Constants;
import cn.goodata.demo.util.DateUtil;
import cn.goodata.demo.util.SystemUtil;
import cn.goodata.demo.vo.Pager;
import cn.goodata.demo.vo.UserBean;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UsersService extends ServiceImpl<UsersDao, Users> implements UserDetailsService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PasswordEncoder passwordEncoder;



    public Users findUserByLoginName(String username){
        QueryWrapper<Users> queryWrapper = new QueryWrapper<Users>();
        queryWrapper.eq("username",username);
        Users users= getBaseMapper().selectOne(queryWrapper);
        return users;
    }














    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<Users> wrapper = new QueryWrapper<Users>();
        wrapper.eq("username",username);
        Users users = getBaseMapper().selectOne(wrapper);
        if (users == null) {
            throw new UsernameNotFoundException("管理员[" + username + "]不存在!");
        }
        // 解除管理员账户锁定
        if (users.isAccountNonLocked() == false) {
            if (Constants.isLoginFailureLock == true) {
                int loginFailureLockTime = Constants.loginFailureLockTime;
                if (loginFailureLockTime != 0) {
                    Date lockedDate = users.getLockedDate();
                    Date nonLockedTime = DateUtil.addMinutes(lockedDate, loginFailureLockTime);
                    Date now = new Date();
                    if (now.after(nonLockedTime)) {
                        users.setLoginFailureCount(0);
                        users.setIsAccountNonLocked(true);
                        users.setLockedDate(null);
                        getBaseMapper().updateById(users);
                    }
                }
            } else {
                users.setLoginFailureCount(0);
                users.setIsAccountNonLocked(true);
                users.setLoginFailureCount(null);
                users.setLoginDate(null);
                users.setLockedDate(null);
                getBaseMapper().updateById(users);
            }
        }

        Role role = roleDao.selectById(users.getRoleId());
        List<String> authorities=   JSONObject.parseArray(role.getAuthorityListStore(),String.class);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        for(String authority:authorities){
            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
        }
        users.setAuthorities(grantedAuthorities);
        return users;
    }



    public String encodePassword(String pwd){
        return passwordEncoder.encode(pwd);
    }

    public Pager searchPager(Pager pager, UserBean bean) {
        QueryWrapper<Users> wrapper = new QueryWrapper<Users>();
        wrapper.ne("t.username","admin");
        /*Users user= SystemUtil.getUser();
        if(!"admin".equals(user.getUsername())){
            conSql.append(" and tr.type=3");
            conSql.append(" and  tu.user_name!=?");
            pList.add(user.getUsername());
        }*/
        if(bean!=null){
            if(StringUtils.isNotBlank(bean.getUsername())){
                wrapper.like("t.username",bean.getUsername());
            }
            if(StringUtils.isNotBlank(bean.getUserMobileno())){
                wrapper.like("t.userMobileno",bean.getUserMobileno());
            }
            if(StringUtils.isNotBlank(bean.getUserType())&&!"-1".equals(bean.getUserType())){
                wrapper.eq("t.userType",bean.getUserType());
            }
            if(StringUtils.isNotBlank(bean.getIsEnabled())){
                wrapper.eq("t.isEnabled",bean.getIsEnabled());
            }
            if(StringUtils.isNotBlank(bean.getIsAccountNonLocked())){
                wrapper.eq("t.isAccountNonLocked",bean.getIsAccountNonLocked());
            }
            if(StringUtils.isNotBlank(bean.getUserRealname())){
                wrapper.like("t.userRealname",bean.getUserRealname());
            }
            if(bean.getRoleId()!=null&&bean.getRoleId()>0){
                wrapper.eq("tr.id",bean.getRoleId());
            }
        }
        getBaseMapper().searchPager(pager,wrapper);
        return pager;
    }
}
