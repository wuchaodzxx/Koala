package com.wuchao.blog.file.dao.intf;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wuchao.blog.user.po.Iuser;



public interface FileUpLoadDao {
	public String uploadAttachment(CommonsMultipartFile attachment, Iuser userValid);
	
}
