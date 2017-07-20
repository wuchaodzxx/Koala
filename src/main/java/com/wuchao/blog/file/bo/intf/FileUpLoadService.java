package com.wuchao.blog.file.bo.intf;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.wuchao.blog.user.po.Iuser;

public interface FileUpLoadService {
	public String uploadAttachment(CommonsMultipartFile attachment, Iuser user);
}
