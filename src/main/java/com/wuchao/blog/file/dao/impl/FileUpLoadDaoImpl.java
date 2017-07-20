package com.wuchao.blog.file.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.wuchao.blog.file.dao.intf.FileUpLoadDao;
import com.wuchao.blog.user.po.Iuser;
import com.wuchao.utils.aliyun.OSSService;
import com.wuchao.utils.string.GeneratorId;

@Repository("fileUpLoadDao")
public class FileUpLoadDaoImpl implements FileUpLoadDao {
	@Resource
	OSSService ossService;

	@Override
	public String uploadAttachment(CommonsMultipartFile attachment, Iuser userValid) {
		// TODO Auto-generated method stub
		String uuid = GeneratorId.getUUID();
		String md5=null;
		Date date = new Date();
		try {
			md5 = ossService.putFile(attachment.getInputStream(), attachment.getSize(),"attachments/"+uuid+"_"+attachment.getOriginalFilename(), attachment.getContentType(), (date.getMonth()+1)+"");
		} catch (IOException e) {
			e.printStackTrace();
		}

		String url  = "http://documents-oss.oss-cn-qingdao.aliyuncs.com/"+(date.getMonth()+1)+"/"+"attachments/"+uuid+"_"+attachment.getOriginalFilename();

		return url; 
	}
	

}
