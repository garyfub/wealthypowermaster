/**   
 * @Title: BaseUser.java
 * @Package: com.sitech.prm.iease.base.user.pojo
 * @CopyRright (c)2008-2020: si-tech 
 * @Project: 易企算 iease-base
 * @Description: 用户表实体类
 * @author 甜瓜 wangjwc@si-tech.com.cn
 * @date 2016-7-4 下午04:07:09 
 * @version V2.0   
 */
package com.zcxn.wp.cloud.auth.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * @Title: BaseUser
 * @Description: 用户表实体类
 * @author 甜瓜  wangjwc@si-tech.com.cn
 */
@Entity
@Table(name = "iease_base_user_t")
public class BaseUser {
	
	/**
	 * 用户账号
	 */
	@Id
	@Column(name = "account", unique = true, nullable = false, length = 32)
    private String account;

	/**
	 * 用户密码
	 */
	@Column(name = "password", nullable = false, length = 32)
    private String password;
    
	/**
	 * 部门ID
	 */
	@Column(name = "dept_id", nullable = false, length = 32)
	private String deptId;
	
	/**
	 * 职务ID
	 */
	@Column(name = "position_id", nullable = false, length = 32)
	private String positionId;
	
	/**
	 * 真实姓名
	 */
	@Column(name = "real_name", nullable = false, length = 64)
	private String realName;
	
	/**
	 * 昵称
	 */
	@Column(name = "nick_name", nullable = false, length = 32)
	private String nickname;
	
	/**
	 * 头像路径
	 */
	@Column(name = "img_path")
	private byte[] imgPath;
	/**
	 * 头像
	 */
	@Column(name = "img_data")
	private byte[] imgData;
	
	/**
	 * 电话
	 */
	@Column(name = "phone", nullable = false, length = 16)
	private String phone;
	
	/**
	 * 邮件
	 */
	@Column(name = "email", nullable = false, length = 32)
	private String email;
	
	/**
	 * 状态
	 */
	@Column(name = "status", nullable = false, length = 1)
	private String status;
	
	/**
	 * 创建人
	 */
	@Column(name = "creater", nullable = false, length = 32)
	private String creater;
	
	/**
	 * 创建时间
	 */
	@Column(name = "create_time", nullable = false, length = 32)
	private Date createTime;
	
	/**
	 * 最后一次修改人
	 */
	@Column(name = "last_modifier", nullable = false, length = 32)
	private String lastModifier;
	
	/**
	 * 最后一次修改时间
	 */
	@Column(name = "last_modify_time", nullable = false, length = 32)
	private Date lastModifyTime ;
	
	@Column(name="COMPANY_ID",nullable = false, length = 32)
	private String companyId ;
	
	@Transient
	private String statusName;//显示 状态
	@Transient
	private String deptName;//部门名称
	@Transient
	private String posiName;//职位名称
	@Transient
	private String regionCode;
	@Transient
	private String regionGroupId;
	@Transient
	private String regionName;
	@Transient
	private String groupId;
	@Transient
	private String groupName;
	@Transient
	private String roleId;
	@Transient
	private String roleName;

	
	@Transient
	private String createrName;
	@Transient
	private String companyName;
	@Transient
	private String projectName;
	@Transient
	private String imgDataStr;
	
	
	
	
	
	public String getImgDataStr() {
		String str=(null !=this.imgData?new String(this.imgData):null);
		return str;
	}

	public void setImgDataStr(String imgDataStr) {
		this.imgDataStr = imgDataStr;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getPosiName() {
		return posiName;
	}

	public void setPosiName(String posiName) {
		this.posiName = posiName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public byte[] getImgPath() {
		return imgPath;
	}

	public void setImgPath(byte[] imgPath) {
		this.imgPath = imgPath;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLastModifier() {
		return lastModifier;
	}

	public void setLastModifier(String lastModifier) {
		this.lastModifier = lastModifier;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}


	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public byte[] getImgData() {
		return imgData;
	}

	public void setImgData(byte[] imgData) {
		this.imgData = imgData;
	}


	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionGroupId() {
		return regionGroupId;
	}

	public void setRegionGroupId(String regionGroupId) {
		this.regionGroupId = regionGroupId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}