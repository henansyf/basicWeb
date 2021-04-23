package cn.goodata.demo.controller.admin;


import cn.goodata.demo.entity.Role;
import cn.goodata.demo.service.RoleService;
import cn.goodata.demo.util.ReturnVarable;
import cn.goodata.demo.vo.Pager;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.security.RolesAllowed;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




@Controller
@RequestMapping("/aRole")
public class RoleController {
	@Autowired
	private RoleService roleService;


	

	@RequestMapping(value = "/save",method= RequestMethod.POST)
	@ResponseBody
	public String save(Role role){
		List<String> authorityList = role.getAuthorityList();
		if(authorityList==null){
			return ReturnVarable.extSuccessInfo(true, "请选择功能点！");
		}
		if(StringUtils.isEmpty(role.getRoleName())){
			return ReturnVarable.extSuccessInfo(true, "请填写角色名称！");
		}
		QueryWrapper<Role> qw = new QueryWrapper<Role>();
		qw.eq("roleName",role.getRoleName());
		Integer sameCount = roleService.count(qw);
		if(sameCount>0){
			return ReturnVarable.extSuccessInfo(true, "角色名被占用 ！");
		}
		
		//基础数据A,数据管理B,统计分析C,数据报告D
		authorityList.add(Role.ROLE_BASE);
		role.setAuthorityList(authorityList);
		role.setType("3");
		role.setCreateTime(new Date());
		roleService.save(role);
		return ReturnVarable.extSuccessInfo(true,"true");
	}
	
	/**
	 *方法描述:查询角色分页显示
	 *@param:pager  分页对象
	 * **/
	@RequestMapping(value = "/list")
	@ResponseBody
	@PreAuthorize("hasAuthority('ROLE_BB')")
	public String list(Pager pager, Role role){
		Pager returnPager = roleService.searchPager(pager,role);
		return JSONObject.toJSONString(returnPager);
	}
	
	
	/**
	 *方法描述:删除角色
	 *@param:ids  角色的id
	 * **/
	@RequestMapping(value = "/del",method=RequestMethod.POST)
	@ResponseBody
	public String del(Integer id){
		//Integer count = usersService.getCountByProperty("roleId",id);
		Role role  = roleService.getById(id);
		/*if (count> 0) {
			return ReturnVarable.extFailureInfo(true,"删除失败！此角色已分配用户！");
		}*/
		roleService.removeById(id);
		//systemLogService.saveLog("1","角色管理--删除角色="+role.getRoleName());
		return ReturnVarable.extSuccessInfo(true, "true");
	}
	
	/**
	 *方法描述:查看角色
	 *@param:id  角色的id
	 * **/
	@RequestMapping(value = "/get")
	@ResponseBody
	public String get(Integer roleId){
		Role role =  roleService.getById(roleId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", "true");
		map.put("data", role);
		return JSONObject.toJSONString(map);
	}
	
	
	/**
	 *方法描述:更新角色
	 *@param:role 角色实体
	 *@param:moduleInfoIds 功能模块ids数组 数组中0  代表树的根部或者表示要更新功能菜单的表示
	 * **/
	@RequestMapping(value = "/update",method=RequestMethod.POST)
	@ResponseBody
	public String update(Role role){
		List<String> authorityList = role.getAuthorityList();
		if(authorityList==null){
			return ReturnVarable.extSuccessInfo(true, "请选择功能点！");
		}
		Role persistent = roleService.getById(role.getId());
		authorityList.add(Role.ROLE_BASE);
		if("1".equals(persistent.getType())){
			authorityList.add("ROLE_AC");//角色管理
			authorityList.add("ROLE_BB");//群组管理
		}
		role.setAuthorityList(authorityList);
		BeanUtils.copyProperties(role, persistent, new String[] {"id", "createTime", "type"});
		roleService.updateById(persistent);
		//systemLogService.saveLog("3","角色管理--修改角色"+role.getRoleName());
		String result=ReturnVarable.extSuccessInfo(true,"true");
		return result;
	}
	
	
	/**
	 *方法描述:查询出所有角色
	 * **/
	@RequestMapping(value = "/getAll")
	@ResponseBody
	public String getAll(){
		return roleService.getAllRoleStore();
	}
	
	
	
}
