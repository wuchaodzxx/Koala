package com.wuchao.utils.aliyun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.stereotype.Repository;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

@Repository("ossDao")
public class OSSDaoImpl implements OSSDao{
	static String accessKeyId = "dawdwddwadw";
	static String accessKeySecret = "hPC3Y4aefasefesafase0VAFdikIsCd";//已改成虚假的id和key，防止github被窃取
	// 以杭州为例
	//static String endpoint = "http://oss-cn-qingdao-internal.aliyuncs.com"; //内网址
	static String endpoint = "http://oss-cn-qingdao.aliyuncs.com";  //外网址
	static String bucketName = "documents-oss";
	//static String folder1 = "Documents";//一级文件夹
	//static String folder2 = "user_id";//二级文件夹
	public void createBucket(String bucketName) {
		// 初始化OSSClient
		OSSClient client = new OSSClient(endpoint,accessKeyId, accessKeySecret);
		// 新建一个Bucket
		client.createBucket(bucketName);
		
	}
	@Override
	public String putFile(InputStream inputStream,long length, String fileKey, String type,String folder) {
		// TODO Auto-generated method stub
		OSSClient client = new OSSClient(endpoint,accessKeyId, accessKeySecret);
		// 创建上传Object的Metadata
		ObjectMetadata meta = new ObjectMetadata();
		// 设置文件类型
		meta.setContentType(type);
		// 必须设置ContentLength
		meta.setContentLength(length);
		// 上传Object.
		PutObjectResult result = client.putObject(bucketName,folder+"/"+fileKey, inputStream, meta);
		// 打印ETag
		System.out.println(result.getETag());
		return result.getETag();
	}
	
	public void deleteDocument(String key){
		OSSClient client = new OSSClient(endpoint,accessKeyId, accessKeySecret);
		
		client.deleteObject(bucketName, key);
	}
	@Override
	public boolean isExsist(String key) {
		// TODO Auto-generated method stub
		OSSClient client = new OSSClient(endpoint,accessKeyId, accessKeySecret);
		OSSObject object = client.getObject(bucketName, key);
		if(object==null){
			return false;
		}
		return true;
	}

}
