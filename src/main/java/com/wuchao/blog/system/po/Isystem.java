package com.wuchao.blog.system.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "I_SYSTEM")
public class Isystem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "adminId")
	private String adminId;
	
	@Column(name = "adminName")
	private String adminName = "admin";

	//访问量
	@Column(name = "amountOfAccess")
	private Integer amountOfAccess = 0;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Integer getAmountOfAccess() {
		return amountOfAccess;
	}

	public void setAmountOfAccess(Integer amountOfAccess) {
		this.amountOfAccess = amountOfAccess;
	}
	
	
}
