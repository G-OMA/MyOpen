package com.xxx.planeap.domain;

import java.util.Date;
import java.util.List;

public class User {

	private int id;
	private String code;
	private String name;
	private String userName;
	private String password;
	private String email;
	private int status;
	private Date expire;
	private List<Role> roles;
	private List<OrgUnit> orgUnits;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getExpire() {
		return expire;
	}
	public void setExpire(Date expire) {
		this.expire = expire;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public List<OrgUnit> getOrgUnits() {
		return orgUnits;
	}
	public void setOrgUnits(List<OrgUnit> orgUnits) {
		this.orgUnits = orgUnits;
	}
}
