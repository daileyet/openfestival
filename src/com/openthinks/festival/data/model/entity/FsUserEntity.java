package com.openthinks.festival.data.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fsusers")
public class FsUserEntity {
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String fuid;
	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String role;
	@Column
	private String islock="Y";

	public String getFuid() {
		return fuid;
	}

	public void setFuid(String fuid) {
		this.fuid = fuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getIslock() {
		return islock;
	}
	
	public void setIslock(String islock) {
		this.islock = islock;
	}

	public void lock() {
		this.islock = "Y";
	}
	
	public void unlock(){
		this.islock = "N";
	}

	@Override
	public String toString() {
		return "FsUserEntity [fuid=" + fuid + ", name=" + name + ", email="
				+ email + ", password=" + password + ", role=" + role
				+ ", islock=" + islock + "]";
	}

}
