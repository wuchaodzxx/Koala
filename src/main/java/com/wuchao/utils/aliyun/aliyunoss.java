package com.wuchao.utils.aliyun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.wuchao.blog.user.po.Iuser;

public class aliyunoss {
	static String accessKeyId = "X9L0Y8BrV2V4xkWo";
	static String accessKeySecret = "hPC3Y4sbgkLGMIGDFjJ0VAFdikIsCd";

	// static String endpoint = "http://oss-cn-qingdao-internal.aliyuncs.com";
	static String endpoint = "http://oss-cn-qingdao.aliyuncs.com";
	static String bucketName = "documents-oss";
	static String folder1 = "Documents";
	static String folder2 = "user_id";

	public static void createBucket(String bucketName) {
		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		client.createBucket(bucketName);
	}

	public static void putObject(String bucketName, String key, String filePath) throws FileNotFoundException {

		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

		File file = new File(filePath);
		InputStream content = new FileInputStream(file);

		ObjectMetadata meta = new ObjectMetadata();

		meta.setContentType("image/jpeg");

		meta.setContentLength(file.length());

		PutObjectResult result = client.putObject(bucketName, key, content, meta);

		System.out.println(result.getETag());

	}

	public static void putObjectFile(String bucketName, String key, File file, String ContentType)
			throws FileNotFoundException {

		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

		InputStream content = new FileInputStream(file);

		ObjectMetadata meta = new ObjectMetadata();

		meta.setContentType(ContentType);

		meta.setContentLength(file.length());

		PutObjectResult result = client.putObject(bucketName, key, content, meta);

		System.out.println(result.getETag());

	}

	public static void listObjects(String bucketName) {

		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

		ObjectListing listing = client.listObjects(bucketName);

		for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
			System.out.println(objectSummary.getKey());
		}
	}

	public static void getObject(String bucketName, String key) throws IOException {

		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

		OSSObject object = client.getObject(bucketName, key);

		ObjectMetadata meta = object.getObjectMetadata();
		String contentType = meta.getContentType();
		System.out.println("contentType:" + contentType);

		InputStream objectContent = object.getObjectContent();

		File file = new File("file12");
		OutputStream os = new FileOutputStream(file);
		byte[] b = new byte[1024];
		int len = 0;
		while ((len = objectContent.read(b)) > 0) {
			for (int i = 0; i < len; i++) {
				os.write(b[i]);
			}
		}

		objectContent.close();
	}

	public static File getObjectFile(String bucketName, String key, Iuser user) throws IOException {

		OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

		OSSObject object = client.getObject(bucketName, key);

		ObjectMetadata meta = object.getObjectMetadata();
		String contentType = meta.getContentType();
		System.out.println("contentType:" + contentType);

		InputStream objectContent = object.getObjectContent();

		File file = new File(user.getId() + "");
		OutputStream os = new FileOutputStream(file);
		byte[] b = new byte[1024];
		int len = 0;
		while ((len = objectContent.read(b)) > 0) {
			for (int i = 0; i < len; i++) {
				os.write(b[i]);
			}
		}

		objectContent.close();
		return file;
	}
}
