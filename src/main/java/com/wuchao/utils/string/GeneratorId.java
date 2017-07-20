package com.wuchao.utils.string;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


import sun.misc.BASE64Encoder;

public class GeneratorId {

	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
			"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z" };

	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	public static String getShortUUID() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = getUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();

	}
	public static String md5(String message){//用于密码的md5加密
		MessageDigest md;
		   try {
		    // 生成一个MD5加密计算摘要
		    md = MessageDigest.getInstance("MD5");
		    // 计算md5函数
		    md.update(message.getBytes());
		    // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
		    // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
		    String pwd = new BigInteger(1, md.digest()).toString(16);
		    return pwd;
		   } catch (Exception e) {
		    e.printStackTrace();
		   }
		   return message;
	}
	
}
