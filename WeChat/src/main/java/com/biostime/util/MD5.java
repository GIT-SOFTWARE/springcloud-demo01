
package com.biostime.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;



public class MD5 {
	 
	
	public MD5() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * MD5数据加密函数
	 * @param arg0 要加密的数据
	 * @return 加密后的数据
	 */
	public static String md5(String arg0) {
		MessageDigest msgDigest = null;
		try {
			msgDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("System doesn't support MD5 algorithm.");
		}
		msgDigest.update(arg0.getBytes());
		byte[] bytes = msgDigest.digest();
		byte tb;
		char low;
		char high;
		char tmpChar;
		String md5Str = new String();
		for (int i = 0; i < bytes.length; i++) {
			tb = bytes[i];

			tmpChar = (char) ((tb >>> 4) & 0x000f);

			if (tmpChar >= 10) {
				high = (char) (('a' + tmpChar) - 10);
			} else {
				high = (char) ('0' + tmpChar);
			}
			md5Str += high;
			tmpChar = (char) (tb & 0x000f);
			if (tmpChar >= 10) {
				low = (char) (('a' + tmpChar) - 10);
			} else {
				low = (char) ('0' + tmpChar);
			}
			md5Str += low;
		}
		return md5Str;
	}
	/* MD5数据加密
	 * @param sourceString
	 * @return
	 */
	public static String MD5Encode(String sourceString) {
		String resultString = null;
		try {
			resultString = new String(sourceString);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byte2hexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
		}
		return resultString;
	}

	public static final String byte2hexString(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}
	/**
	 *e10adc3949ba59abbe56e057f20f883e
	 *E10ADC3949BA59ABBE56E057F20F883E
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String a = MD5.md5("123456");
		System.out.println(a);
//		System.out.println("T MD5:  "+MD5.md5("中国"));
//		System.out.println("T+T2 MD5:  "+MD5.md5(t+t2));
//		System.out.println("T Concant T2 MD5:  "+MD5.md5(t.concat(t2)));
//		System.out.println("T Concant null MD5:  "+MD5.md5(t+(null)));
		
//	System.out.println((int)Math.ceil((float)1/3));
//	  Random r=new Random();
//		System.out.println(r.nextInt(100000-10000)+10000);
		
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println(""+System.currentTimeMillis());
		
//		String fileName="AAAAAAAAA.html";
//		String temp=fileName.substring(fileName.lastIndexOf("."));
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println(""+System.currentTimeMillis()+temp);
//		System.out.println(MD5Encode("Fangrn"));  
	}
	
	
	private static String md5Digest(String spPassword, String timeStamp) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(spPassword.getBytes());
			md.update(timeStamp.getBytes());

			byte[] b = md.digest();
			String result = "";
			String temp = "";

			for (int i = 0; i < 16; i++) {
				temp = Integer.toHexString(b[i] & 0xFF);
				if (temp.length() == 1)
					temp = "0" + temp;
				result += temp;
			}

			// MD5
			result = result.toUpperCase();

			System.out.println("MD5 Password : " + result);

			return result;
		} catch (NoSuchAlgorithmException e) {
			 e.printStackTrace();
			//System.out.println(e.toString());
			return null;
		} catch (Exception e) {
			 e.printStackTrace();
			//System.out.println(e.toString());
			return null;
		}
	}
	
public String getClientIp() {
		//MessageContext mc = MessageContext.getCurrentMessageContext();
		//HttpServletRequest request = (HttpServletRequest) mc
			//	.getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);
		// System.out.println("remote ip: " + request.getRemoteAddr());
		//return request.getRemoteAddr();
	
	return "";
	}

}
