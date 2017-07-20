package com.wuchao.blog.comment.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "I_ARTICLECOMMENT")
public class IarticleComment implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	//文章ID
	@Column(name = "articleId")
	private Integer articleId;
	
	//评论者ID
	@Column(name = "commentUserId")
	private Integer commentUserId;
	
	//评论者用户昵称
	@Column(name = "commentUserNickName")
	private String commentUserNickName;
	
	//回复目标用户ID
	@Column(name = "commentTargetUserId")
	private Integer commentTargetUserId=0;
	
	//回复目标用户昵称
	@Column(name = "commentTargetUserNickName")
	private String commentTargetUserNickName;
	
	//评论时间
	@Column(name = "commentDate")
	private Date commentDate;

	//评论内容
	@Column(name = "content")
	private String content;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getCommentUserId() {
		return commentUserId;
	}

	public void setCommentUserId(Integer commentUserId) {
		this.commentUserId = commentUserId;
	}

	public String getCommentUserNickName() {
		return commentUserNickName;
	}

	public void setCommentUserNickName(String commentUserNickName) {
		this.commentUserNickName = commentUserNickName;
	}

	public Integer getCommentTargetUserId() {
		return commentTargetUserId;
	}

	public void setCommentTargetUserId(Integer commentTargetUserId) {
		this.commentTargetUserId = commentTargetUserId;
	}

	public String getCommentTargetUserNickName() {
		return commentTargetUserNickName;
	}

	public void setCommentTargetUserNickName(String commentTargetUserNickName) {
		this.commentTargetUserNickName = commentTargetUserNickName;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
