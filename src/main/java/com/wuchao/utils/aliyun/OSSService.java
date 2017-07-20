package com.wuchao.utils.aliyun;

import java.io.InputStream;

public interface OSSService {
	public String putFile(InputStream inputStream, long length, String fileKey, String type, String folder);
	
	public void deleteDocument(String key);

	public boolean isExsist(String key);

}
