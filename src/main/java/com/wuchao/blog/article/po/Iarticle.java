package com.wuchao.blog.article.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "I_ARTICLE")
public class Iarticle implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "userId")
	private String userId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "createDate")
	private Date createDate;
	
	@Column(name = "modifyDate")
	private Date modifyDate;
	
	//内容
	@Column(name = "content")
	private String content;
	
	//标题
	@Column(name = "title")
	private String title;
	
	//类别
	@Column(name = "category")
	private String category;
	
	//阅读数
	@Column(name = "browsers")
	private int browsers=0;
	
	//评论
	@Column(name = "comments")
	private int comments=0;
	
	
	//类别
	@Column(name = "abstractsDesc")
	private String abstractsDesc;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getBrowsers() {
		return browsers;
	}

	public void setBrowsers(int browsers) {
		this.browsers = browsers;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public String getAbstractsDesc() {
		return abstractsDesc;
	}

	public void setAbstractsDesc(String abstractsDesc) {
		this.abstractsDesc = abstractsDesc;
	}

	
}
