package com.sy.forum.utils;

import com.sy.forum.security.Coder;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.nio.charset.Charset;
import java.security.MessageDigest;

@Component("SecurityUtil")
public class SecurityUtil {
	
	private final static int	ITERATIONS	= 20;
	@Value("#{conf.security.key}")
	private static String		keyString;
	
	/**
	 * @Title: encrypt @Description:加密 @param @param key @param @param
	 *         plainText @param @return @param @throws Exception 设定文件 @return
	 *         String 返回类型 @throws
	 */
	public static String encrypt(String plainText) throws Exception {
		try {
			byte[] salt = new byte[8];
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(keyString.getBytes(Charset.forName("UTF-8")));
			byte[] digest = md.digest();
			for (int i = 0; i < 8; i++) {
				salt[i] = digest[i];
			}
			PBEKeySpec pbeKeySpec = new PBEKeySpec(keyString.toCharArray());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			SecretKey skey = keyFactory.generateSecret(pbeKeySpec);
			PBEParameterSpec paramSpec = new PBEParameterSpec(salt, ITERATIONS);
			Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
			cipher.init(Cipher.ENCRYPT_MODE, skey, paramSpec);
			byte[] cipherText = cipher.doFinal(plainText.getBytes(Charset.forName("UTF-8")));
			String saltString = new String(Base64.encodeBase64(salt), "UTF-8");
			String ciphertextString = new String(Base64.encodeBase64(cipherText), "UTF-8");
			return saltString + ciphertextString;
		} catch (Exception e) {
			throw new Exception("Encrypt Text Error:" + e.getMessage(), e);
		}
	}
	
	/**
	 * @Title: decrypt @Description: 解密 @param @param key @param @param
	 *         encryptTxt @param @return @param @throws Exception 设定文件 @return
	 *         String 返回类型 @throws
	 */
	public static String decrypt(String encryptTxt) throws Exception {
		int saltLength = 12;
		try {
			String salt = encryptTxt.substring(0, saltLength);
			String ciphertext = encryptTxt.substring(saltLength, encryptTxt.length());
			byte[] saltarray = Base64.decodeBase64(salt.getBytes(Charset.forName("UTF-8")));
			byte[] ciphertextArray = Base64
					.decodeBase64(ciphertext.getBytes(Charset.forName("UTF-8")));
			PBEKeySpec keySpec = new PBEKeySpec(keyString.toCharArray());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
			SecretKey skey = keyFactory.generateSecret(keySpec);
			PBEParameterSpec paramSpec = new PBEParameterSpec(saltarray, ITERATIONS);
			Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
			cipher.init(Cipher.DECRYPT_MODE, skey, paramSpec);
			byte[] plaintextArray = cipher.doFinal(ciphertextArray);
			return new String(plaintextArray, "UTF-8");
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	/**
	 * 三层加密
	 */
	public static String base64MultiEncrypt(String plainText) throws Exception {
		plainText = Coder.encryptBASE64(plainText.getBytes(Charset.forName("UTF-8")));
		plainText = Coder.encryptBASE64(plainText.getBytes(Charset.forName("UTF-8")));
		plainText = Coder.encryptBASE64(plainText.getBytes(Charset.forName("UTF-8")));
		return plainText;
	}
	
	/**
	 * 三层解密
	 */
	public static String base64MultiDecrypt(String encryptTxt) throws Exception {
		encryptTxt = new String(Coder.decryptBASE64(encryptTxt), "UTF-8");
		encryptTxt = new String(Coder.decryptBASE64(encryptTxt), "UTF-8");
		encryptTxt = new String(Coder.decryptBASE64(encryptTxt), "UTF-8");
		return encryptTxt;
	}
}
