package cn.goodata.demo.service;


import cn.goodata.demo.dao.RoleDao;
import cn.goodata.demo.entity.Role;
import cn.goodata.demo.vo.Pager;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleService extends ServiceImpl<RoleDao, Role> {


  public Pager searchPager(Pager page, Role role) {
      QueryWrapper<Role> qw = new QueryWrapper<Role>();
     // qw.setEntity(role);
      return getBaseMapper().selectPage(page,qw);

  }

  
  public String getAllRoleStore() {
      //待完成
	  List<Role> listRole =getBaseMapper().selectList(null);
	  return JSONArray.toJSONString(listRole);
  }
  
}