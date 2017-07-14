package com.wuchao.utils.security;

import java.security.MessageDigest;

public class Md5Util {
    /**
     * Computes the MD5 fingerprint of a string.
     * 
     * @return the MD5 digest of the input <code>String</code>
     */
    public static String encode(String s) {
        String cryptograph = "";
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();

            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++ ) {
                byte byte0 = md[i];
                str[k++ ] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++ ] = hexDigits[byte0 & 0xf];
            }
            cryptograph = new String(str);// 32位加密

            return cryptograph;
        } catch (Exception e) {
            return null;
        }

    }
	public static void main(String[] args) {
		System.out.println(Md5Util.encode("123"));
	}
}
