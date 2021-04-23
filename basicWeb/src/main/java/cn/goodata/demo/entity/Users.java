package cn.goodata.demo.entity;



import java.beans.Transient;
import java.util.Date;
import java.util.List;


import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@TableName("t_users")
public class Users  implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8619396863818856155L;
	@TableId(type= IdType.AUTO)
	private Integer id;//用户ID
	private String username;//登录账号
	private String password;//登录密码
	private String userRealname;//真实姓名
	private String userMobileno;//手机号
	private String userEmail;//邮箱
	private String userType;//用户类型 1：管理员 2：普通用户
	private String comments;//备注
	private boolean isEnabled;//账号是否启用 true:启用  false：禁用
	private boolean isAccountNonExpired;//账号是否过期 true 过期  false 未过期
	private boolean isAccountNonLocked;//账号是否锁定 true 锁定 false 未锁定
	private boolean isCredentialsNonExpired;//凭证是否过期  true 过期 false 未过期
	private Date lockedDate;//账号锁定时间
	private Date loginDate;//最后登录日期
	private Integer loginFailureCount;//连续登录失败的次数
	private Integer roleId;//角色id
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date createTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRealname() {
		return userRealname;
	}

	public void setUserRealname(String userRealname) {
		this.userRealname = userRealname;
	}

	public String getUserMobileno() {
		return userMobileno;
	}

	public void setUserMobileno(String userMobileno) {
		this.userMobileno = userMobileno;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}






	public Date getLockedDate() {
		return lockedDate;
	}

	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Integer getLoginFailureCount() {
		return loginFailureCount;
	}

	public void setLoginFailureCount(Integer loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}



	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}







	//private transient String securityStr;
	private transient  List<GrantedAuthority> authorities;


	public List<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	/*public String getSecurityStr() {
		return securityStr;
	}

	public void setSecurityStr(String securityStr) {
		this.securityStr = securityStr;
	}*/

	public boolean getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(boolean enabled) {
		isEnabled = enabled;
	}

	public boolean getIsAccountNonExpired() {
		return isAccountNonExpired;
	}

	public void setIsAccountNonExpired(boolean accountNonExpired) {
		isAccountNonExpired = accountNonExpired;
	}

	public boolean getIsAccountNonLocked() {
		return isAccountNonLocked;
	}
	public void setIsAccountNonLocked(boolean accountNonLocked) {
		isAccountNonLocked = accountNonLocked;
	}

	public boolean getIsCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}
	public void setIsCredentialsNonExpired(boolean credentialsNonExpired) {
		isCredentialsNonExpired = credentialsNonExpired;
	}



	@Override
	public  boolean isAccountNonExpired() {
		return this.isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		//nihao
		return this.isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.isEnabled;
	}


	/*************以下是重写equals方法来实现单用户登录，去掉security则不起作用**************/
	@Override
	public String toString() {
		return this.username;
	}

	@Override
	public int hashCode() {
		return this.username.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}
}
