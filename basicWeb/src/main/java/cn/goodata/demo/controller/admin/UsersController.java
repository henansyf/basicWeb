package cn.goodata.demo.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import cn.goodata.demo.entity.Role;
import cn.goodata.demo.entity.Users;
import cn.goodata.demo.service.RoleService;
import cn.goodata.demo.service.UsersService;
import cn.goodata.demo.util.ReturnVarable;
import cn.goodata.demo.util.ValidateTools;
import cn.goodata.demo.vo.Pager;
import cn.goodata.demo.vo.UserBean;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;




@Controller 
@RequestMapping("/aUsers")
public class UsersController {
  @Autowired
  private UsersService usersService;
  @Autowired
  private RoleService roleService;
 // @Autowired
  //private SystemLogService systemLogService;

	/**
	 *方法描述:保存系统用户
	 * 
	 * @param:BmUser 系统用户对象
	 *@param:roleId 角色id
	 * **/
	@RequestMapping(value = "/save",method=RequestMethod.POST)
	@ResponseBody
	public String save(Users user) {
		//validate
		if(user.getUsername()==null&&!ValidateTools.UserName(user.getUsername())){
			return ReturnVarable.extSuccessInfo(true, "用户名不正确！");
		}
		if("admin".equals(user.getUsername())){
			return ReturnVarable.extSuccessInfo(true, "用户名不能用admin！");
		}

		QueryWrapper wrapper = new QueryWrapper();
		wrapper.eq("username",user.getUsername());
		Integer sameCount =usersService.count(wrapper);
		if(sameCount>0){
			return ReturnVarable.extSuccessInfo(true, "用户名已被使用！");
		}
		
		
		if (user.getRoleId()==null) {
			return ReturnVarable.extSuccessInfo(true, "请选择角色！");
		}
		Role role = roleService.getById(user.getRoleId());
		if(role==null||"1".equals(role.getType())){
			return ReturnVarable.extSuccessInfo(true, "请选择角色！");
		}
		
		user.setUsername(user.getUsername().toLowerCase());
		user.setLoginFailureCount(0);
		user.setIsEnabled(true);
		user.setIsAccountNonLocked(true);
		user.setIsAccountNonExpired(true);
		user.setIsCredentialsNonExpired(true);
		user.setPassword(usersService.encodePassword("sdfQ@454!erfgbv346"));
		user.setCreateTime(new Date());
		user.setUserType("1");
		usersService.save(user);
		return ReturnVarable.extSuccessInfo(true,"true");
	}

	

	
	/**
	 *方法描述:查询系统用户分页显示
	 * 
	 * @param:pager 分页对象
	 *@param:tUserInfo 用户条件对象
	 * **/
	@RequestMapping(value = "/list")
	@ResponseBody
	public String list(Pager pager, UserBean bean) {
		Pager returnPager = usersService.searchPager(pager,bean);
		return JSONObject.toJSONString(returnPager);
	}

	/**
	 *方法描述:查询系统用户
	 * 
	 * @param:userId 用户id
	 * **/
	@RequestMapping(value = "/get")
	@ResponseBody
	public String get(Integer id) {
		Users user = usersService.getById(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", "true");
		map.put("data", user);
		return JSONObject.toJSONString(map);
	}


	/**
	 *方法描述:删除
	 * 
	 * @param:ids 用户信息id数组
	 * **/
	@RequestMapping(value = "/del",method=RequestMethod.POST)
	@ResponseBody
	public String del(Integer id) {
		if(id==null){
			return ReturnVarable.extSuccessInfo(true,"删除失败");
		}
		Users user = usersService.getById(id);
		usersService.removeById(id);
		//systemLogService.saveLog("2", "用户管理--删除用户="+user.getUsername());
		return ReturnVarable.extSuccessInfo(true,"true");
	}

	/**
	 *方法描述:更新实体对象
	 * 
	 * @param:BmUser 系统用户对象
	 * **/
	@RequestMapping(value = "/update",method=RequestMethod.POST)
	@ResponseBody
	public String update(Users users){
		if("admin".equals(users.getUsername())){
			return ReturnVarable.extSuccessInfo(true, "用户名不能用admin！");
		}
		if (users.getRoleId()==null) {
			return ReturnVarable.extSuccessInfo(true, "请选择角色！");
		}
		Users persistent = usersService.getById(users.getId());
		
		persistent.setUserEmail(users.getUserEmail());
		persistent.setUserMobileno(users.getUserMobileno());
		persistent.setUserRealname(users.getUserRealname());
		persistent.setRoleId(users.getRoleId());
		usersService.updateById(persistent);
		//systemLogService.saveLog("3","用户管理--修改用户"+persistent.getUsername());
		return ReturnVarable.extSuccessInfo(true,"true");
	}

	/**
	 *方法描述:禁用或者启用用户
	 * 
	 * @param: Users用户实体
	 * **/
	@RequestMapping(value = "/forbid",method=RequestMethod.POST)
	@ResponseBody
	public String forbid(Users users) {
		Users persistent = usersService.getById(users.getId());
		persistent.setIsEnabled(users.isEnabled());// 启用状态
		usersService.updateById(persistent);
		//systemLogService.saveLog("3","用户管理--禁用或启用用户="+persistent.getUsername());
		return ReturnVarable.extSuccessInfo(true,"true");
	}
	
	/**
	 *方法描述:用户登录失败次数超过5次账号被锁后
	 *管理员进入系统解锁功能
	 * @param: tUserInfo用户实体
	 * **/
	@RequestMapping(value = "/reOpen",method=RequestMethod.POST)
	@ResponseBody
	public String reOpen(Users users) {
		Users persistent = usersService.getById(users.getId());
		persistent.setLoginFailureCount(0);
		persistent.setIsAccountNonLocked(true);
		persistent.setLockedDate(null);
		usersService.updateById(persistent);
		//systemLogService.saveLog("3","用户管理--解锁用户="+persistent.getUsername());
		return ReturnVarable.extSuccessInfo(true,"true");
	}
	
	/**
	 *方法描述:重置密码
	 * @param: tUserInfo用户实体
	 * **/
	@RequestMapping(value = "/resetPWD",method=RequestMethod.POST)
	@ResponseBody
	public String resetPWD(Users users) {
		Users persistent = usersService.getById(users.getId());
		persistent.setPassword(usersService.encodePassword("000000"));
		usersService.updateById(persistent);
		//systemLogService.saveLog("3","用户管理--重置密码用户="+persistent.getUsername());
		return ReturnVarable.extSuccessInfo(true,"true");
	}
	
	/**
	 *方法描述:查询出所有角色
	 * **/
	@RequestMapping(value = "/getRoles")
	@ResponseBody
	public String getRoles(){
		return  roleService.getAllRoleStore();
	}
}