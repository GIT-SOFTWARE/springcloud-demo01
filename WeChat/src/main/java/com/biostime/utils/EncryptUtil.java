package com.biostime.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int random=(int)(Math.random()*1000000); 
		System.out.println(random);
		System.out.println(EncryptUtil.md5("888888"));

		int i=1;
		int j=2;
		//System.out.println(hash("aaaa"));
	}
	
	public static Integer hash(Object key) {
		String data = key.toString();
		//默认使用FNV1hash算法
		final int p = 16777619;
		int hash = (int) 2166136261L;
		for (int i = 0; i < data.length(); i++){
			hash = (hash ^ data.charAt(i)) * p;
			System.out.println("hash1 = "+hash);
		}
		System.out.println("hash2 = "+hash);
		hash += hash << 13;
		hash ^= hash >> 7;
		hash += hash << 3;
		hash ^= hash >> 17;
		hash += hash << 5;
		return hash;
	}
	
	public static String md5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	public static String MD5Encode16bit(String plainText) {
		String resultString = null;
		try {

			MessageDigest md = MessageDigest.getInstance("MD5");

			md.update(plainText.getBytes());

			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");

			for (int offset = 0; offset < b.length; offset++) {

				i = b[offset];

				if (i < 0)
					i += 256;

				if (i < 16)

					buf.append("0");

				buf.append(Integer.toHexString(i));

			}

			// System.out.println("result 32 bit : " + buf.toString());// 32位的加密

			// System.out.println("result: 16 bit :" +
			// buf.toString().substring(8, 24));// 16位的加密

			resultString = buf.toString().substring(8, 24);

		} catch (NoSuchAlgorithmException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
		return resultString;
	}
	
}
