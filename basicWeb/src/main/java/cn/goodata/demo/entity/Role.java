package cn.goodata.demo.entity;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.StringUtils;


/**
 * 实体类 - 管理角色
 */

@TableName("t_role")
public class Role{

	public static final String ROLE_BASE = "ROLE_Z";// 基础管理权限
	@TableId(type= IdType.AUTO)
	private Integer id;
	private String roleName;// 角色名称
	private String commons;
	private String roleDesc;// 描述
	private String authorityListStore;// 权限集合存储AUTHORITY_LIST_STORE
	private String type;//角色类型1:系统内置超级管理2:系统内置管理员3:普通企业角色
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date  createTime;
	
	
	public Role(){}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCommons() {
		return commons;
	}

	public void setCommons(String commons) {
		this.commons = commons;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getAuthorityListStore() {
		return authorityListStore;
	}

	public void setAuthorityListStore(String authorityListStore) {
		this.authorityListStore = authorityListStore;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	//private transient  List<String> authorityList;

	@SuppressWarnings("unchecked")
	@Transient
	public List<String> getAuthorityList() {
		if (StringUtils.isEmpty(authorityListStore)) {
			return null;
		}
		return JSONObject.parseArray(authorityListStore,String.class);
	}

	@Transient
	public void setAuthorityList(List<String> roleList) {
		if (roleList == null || roleList.size() == 0) {
			authorityListStore = null;
			return;
		}
		authorityListStore = JSONArray.toJSONString(roleList);
	}
}