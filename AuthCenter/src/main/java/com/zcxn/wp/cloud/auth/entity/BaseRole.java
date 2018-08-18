/**
 * 
 */
package com.zcxn.wp.cloud.auth.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 角色对象  Created on 2016-6-24
 * <p>Title: 统一报表平台</p>
 * <p>Copyright: Copyright (c) 2015-2025</p>
 * <p>Company: 北京思特奇信息技术股份有限公司</p>
 * <p>Department: PRM-PSD</p>
 * @author 14K  sunyz@si-tech.com.cn
 * @version 1.0
 * @update 修改日期 修改描述
 */
@Entity
@Table(name = "IEASE_BASE_ROLE_T")
public class BaseRole implements Serializable{

	private static final long serialVersionUID = -6761060976326124891L;

	@Id
	@Column(name = "ROLE_ID", unique = true, nullable = false, length = 32)
	private String roleId;

	@Column(name = "ROLE_NAME",length = 64)
	private String roleName;

	// @Column(name = "ROLE_CODE",length = 64)
	// private String ROLE_CODE;

	@Column(name = "ROLE_DESCRIPTION",length = 128)
	private String roleDescription;

	@Column(name = "ROLE_STATUS", length = 4, updatable=true)
	private String roleStatus = "Y";

	@Transient
	private String roleStatusName;

	@Column(name = "CREATER", unique = false, nullable = false, length = 32, updatable=false)
	private String creater;

	//@Temporal(TemporalType.TIME)
	@Column(name = "CREATE_TIME", unique = false, nullable = false, length = 32,updatable=false)
	private Date createTime;

	@Column(name = "LAST_MODIFIER", unique = false, nullable = false)
	private String lastModifier;

	//@Temporal(TemporalType.TIME)
	@Column(name = "LAST_MODIFY_TIME", unique = false, nullable = false)
	private Date lastModifyTime;

	public BaseRole() {
		super();
	}

	public BaseRole(String roleName, String roleDescription) {
		super();
		this.roleName = roleName;
		this.roleDescription = roleDescription;
	}

	public BaseRole(String roleId, String roleName, String roleDescription,
			String roleStatus) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDescription = roleDescription;
		this.roleStatus = roleStatus;
	}


	public String getRoleStatusName() {
		return roleStatusName;
	}

	public void setRoleStatusName(String roleStatusName) {
		this.roleStatusName = roleStatusName;
	}

	public String getRoleId() {
		return roleId;
	}

	
	public void setRoleId(String roleId) {
		this.roleId = roleId;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	// public String getROLE_CODE() {
	// 	return ROLE_CODE;
	// }
  //
	// public void setROLE_CODE(String ROLE_CODE) {
	// 	this.ROLE_CODE = ROLE_CODE;
	// }

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName
				+ ", roleDescription=" + roleDescription + ", roleStatus="
				+ roleStatus + "]";
	}
	
	
}
