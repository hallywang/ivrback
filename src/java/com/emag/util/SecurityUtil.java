package com.emag.util;

import java.security.MessageDigest;

/**
 * viavame All Rights Reserved@2008-2009 作者: 王海利 日期: 2010-1-25 时间: 16:47:54 说明: 该类实现。。。。 修改人:王海利 修改时间: 2010-1-25
 * 16:47:54
 */
public class SecurityUtil {
	/**
	 * @param str:summ 加密算法 mdt/sha-1 str待加密字符串
	 * @return :加密后字符串
	 */

	public static String getMD5String(String summ, String str) {
		try {
			byte[] res = str.getBytes();
			MessageDigest md = MessageDigest.getInstance(summ.toUpperCase());
			for (int i = 0; i < res.length; i++) {
				md.update(res[i]);
			}
			byte[] hash = md.digest();
			StringBuffer d = new StringBuffer("");
			for (int i = 0; i < hash.length; i++) {
				int v = hash[i] & 0xFF;
				if (v < 16)
					d.append("0");
				d.append(Integer.toString(v, 16).toUpperCase() + "");
			}
			return d.toString();
		} catch (Exception e) {
			e.printStackTrace();
            return null;
		}
	}

	public static String getMD5String(String str) {
		return getMD5String("md5", str);
	}

	public static String getSHAString(String str) {
		return getMD5String("SHA-1", str);
	}

	public static void main(String args[]) {
		System.out.println(getMD5String("你好"));
	}
}

