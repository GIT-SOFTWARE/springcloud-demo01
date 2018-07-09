package com.biostime.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;

public class DesUtil {

	// DES编码格式
	private static String DES_FORMAT = "DES/ECB/NoPadding";

	// 内容编码
	private static String DES_ENCODING = "UTF-8";

	// DES加密的私钥，必须是8位长的字符串
	private static final byte[] DESkey = "Asd789%%".getBytes();// 设置密钥

	private static final byte[] DESIV = "Asd789%%".getBytes();// 设置向量,必须是8位长的字符串

	static AlgorithmParameterSpec iv = null;// 加密算法的参数接口，IvParameterSpec是它的一个实现

	private static Key key = null;

	public DesUtil() throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
		DESKeySpec keySpec = new DESKeySpec(DESkey);// 设置密钥参数
		iv = new IvParameterSpec(DESIV);// 设置向量
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
		key = keyFactory.generateSecret(keySpec);// 得到密钥对象
	}

	/**
	 * 加密字符串
	 * 
	 * @author lisheng
	 * @date 2013-8-28 下午4:11:32
	 * @return String 密文
	 * @param content
	 *            明文
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws UnsupportedEncodingException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws Exception
	 */
	public String encrypt(String content) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
		enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
		byte[] pasByte = enCipher.doFinal(content.getBytes("utf-8"));
		// BASE64Encoder base64Encoder = new BASE64Encoder();
		// return base64Encoder.encode(pasByte);
		return new String(Base64.encodeBase64(pasByte));
	}

	/**
	 * 解密字符串
	 * 
	 * @author lisheng
	 * @date 2013-8-28 下午4:08:48
	 * @return String 明文
	 * @param content
	 *            密文
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	public String decrypt(String content) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		deCipher.init(Cipher.DECRYPT_MODE, key, iv);// 设置工作模式为解密模式，给出密钥和向量
		// BASE64Decoder base64Decoder = new BASE64Decoder();
		byte[] recByte = deCipher.doFinal(Base64.decodeBase64(content.getBytes()));
		return new String(recByte, "UTF-8");
	}

	/**
	 * DES加密
	 * 
	 * @param key
	 *            密钥
	 * @param data
	 *            需加密的原始数据
	 * @return 加密并以base64编码的数据
	 * @throws Exception
	 */
	public static String encrypt(String key, String data) throws Exception {

		if (key == null || key.length() == 0) {
			System.out.println("key is null.");
			return null;
		}

		if (data == null || data.length() == 0) {
			System.out.println("data is null.");
			return null;
		}

		if (key.length() % 8 != 0 || key.length() > 64) {
			System.out.println("invalid key.");
			throw new Exception("key 必须为8的倍数。或者大于64");
		}

		byte[] bytes = handleDesData(data.getBytes(DES_ENCODING));
		// byte[] bytes = data.getBytes(DES_ENCODING);

		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key.getBytes("ASCII"));

		// 创建一个密匙工厂，然后用它把DESKeySpec转换成 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES_FORMAT);

		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey);

		// 现在，获取数据并加密
		// 正式执行加密操作
		byte[] result = cipher.doFinal(bytes);

		// base64再次编码
		Base64 base64 = new Base64();
		byte[] encodedBytes = base64.encode(result);

		return new String(encodedBytes);
	}

	/**
	 * DES解密
	 * 
	 * @param key
	 *            密钥
	 * @param encryptedStr
	 *            已加密的数据
	 * @return 解密后的数据
	 * @throws Exception
	 */
	public static String decode(String key, String encryptedStr) throws Exception {

		if (key == null || key.length() == 0) {
			System.out.println("key is null.");
			return null;
		}

		if (encryptedStr == null || encryptedStr.length() == 0) {
			// System.out.println("encryptedStr is null.");
			return null;
		}

		if (key.length() % 8 != 0 || key.length() > 64) {
			System.out.println("invalid key.");
			throw new Exception("key 必须为8的倍数。或者大于64");
		}

		// 先将Base64字串转码为byte[]
		Base64 base64 = new Base64();
		byte[] decodedBytes = base64.decode(encryptedStr.getBytes());

		// 建立解密所需的Key. 因为加密時的key是用ASCII转换, 所以這边也用ASCII做
		DESKeySpec objDesKeySpec = new DESKeySpec(key.getBytes("ASCII"));
		SecretKeyFactory objKeyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey objSecretKey = objKeyFactory.generateSecret(objDesKeySpec);

		// 设定一个DES/ECB/PKCS5Padding的Cipher
		// ECB对应到.Net的CipherMode.ECB
		// 用PKCS5Padding对应到.Net的PaddingMode.PKCS7
		Cipher objCipher = Cipher.getInstance(DES_FORMAT);

		// 设定為解密模式, 并设定解密的key
		objCipher.init(Cipher.DECRYPT_MODE, objSecretKey);

		// 输出解密后的字串. 因为加密时指定PaddingMode.PKCS7, 所以可以不用处理空字元
		// 不过若想保险点, 也是可以用trim()去处理一遍
		String decryptedStr = new String(objCipher.doFinal(decodedBytes), DES_ENCODING).trim();

		return decryptedStr;
	}

	/**
	 * 处理待加密的数据，不足8的倍数需在后面补零
	 * 
	 * @param data
	 *            待加密的数据
	 * @return 处理后的数据
	 */
	private static byte[] handleDesData(byte[] data) {

		int length = data.length;
		byte[] nData;

		int mod = length % 8;

		if (mod != 0) {
			nData = new byte[length + 8 - mod];
		} else {
			nData = new byte[length];
		}

		for (int i = 0; i < nData.length; i++) {
			if (i < length) {
				nData[i] = data[i];
			}
		}

		return nData;
	}

	/**
	 * 随机生成一个DES密钥
	 * 
	 * @param length
	 *            密钥长度
	 * @return DES密钥
	 * @throws InvalidKeyFormatException
	 *             无效的密钥长度异常
	 */
	public static String generateKey(int length) {

		if (length <= 0 || length % 8 != 0 || length > 64) {
			return null;
		}

		String val = "";

		Random random = new Random();
		for (int i = 0; i < length; i++) {

			// 输出字母还是数字
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";

			// 字符串
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 取得大写字母还是小写字母
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
				val += String.valueOf(random.nextInt(10));
			}

		}

		return val.toLowerCase();
	}

	public static void main(String[] args) throws Exception {

		String key = generateKey(8);

		String encodedStr = encrypt(key, "加密测试数据test");
		System.out.println("encodedStr: " + encodedStr);

		String decodedStr = decode(key, encodedStr);
		System.out.println("decodedStr: " + decodedStr);

	}

}
