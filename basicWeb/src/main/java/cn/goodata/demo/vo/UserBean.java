package cn.goodata.demo.vo;



public class UserBean{
	private String userRealname;//员工姓名
	private String userMobileno;//手机号
	private String isEnabled;//是否启用
	private String isAccountNonLocked;//
	private String username;//登录名
	private Integer  roleId;//用户所属的角色
	private String userType;//用户类型


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

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getIsAccountNonLocked() {
		return isAccountNonLocked;
	}

	public void setIsAccountNonLocked(String isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
