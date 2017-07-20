package com.wuchao.blog.file.bo.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.wuchao.blog.file.bo.intf.FileUpLoadService;
import com.wuchao.blog.file.dao.intf.FileUpLoadDao;
import com.wuchao.blog.user.po.Iuser;

@Service("fileUpLoadService")
public class FileUpLoadServiceImpl implements FileUpLoadService {
	@Resource
	private FileUpLoadDao fileUpLoadDao;

	@Transactional
	@Override
	public String uploadAttachment(CommonsMultipartFile attachment, Iuser userValid) {
		// TODO Auto-generated method stub
		return fileUpLoadDao.uploadAttachment(attachment,userValid);
	}
	

}
