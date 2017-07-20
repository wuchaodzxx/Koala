package com.wuchao.utils.aliyun;

import java.io.InputStream;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
@Service("ossService")
public class OSSServiceImpl implements OSSService{
	@Resource
	OSSDaoImpl ossDao;
	public OSSDaoImpl getOssDao() {
		return ossDao;
	}
	public void setOssDao(OSSDaoImpl ossDao) {
		this.ossDao = ossDao;
	}
	@Override
	public String putFile(InputStream inputStream, long length, String fileKey, String type, String folder) {
		// TODO Auto-generated method stub
		return ossDao.putFile(inputStream, length, fileKey, type, folder);
	}
	@Override
	public void deleteDocument(String key) {
		// TODO Auto-generated method stub
		System.out.println("OSSServiceImpl:删除文件"+key);
		ossDao.deleteDocument(key);
	}
	@Override
	public boolean isExsist(String key) {
		// TODO Auto-generated method stub
		return ossDao.isExsist(key);
	}
	
}
