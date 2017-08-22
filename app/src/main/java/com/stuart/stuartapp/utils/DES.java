package com.stuart.stuartapp.utils;

import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


public class DES {
	/*public DES() {
	}
*/
/*	// 测试
	public static void main(String args[]) {
		// 待加密内容
		String str = "123";
		// 密码，长度要是8的倍数
		String password = "12345678";
		System.out.println( Arrays.toString(password.getBytes()));

		byte[] result = DES.encrypt(str.getBytes()new byte[] {1,2,3,4,5,6,7,8}, password);
		System.out.println("加密后：" + Base64Util.encode(result) +",\n" + Arrays.toString(result));

		// 直接将如上内容解密
		result = Base64Util.decode("hwrZsT4T+n7K4QlpvYpFIvKXaaj2xkSQGnTMs7TfuN9CXF4f85uKOCUr8a3zQfCh");//.getBytes(); 
		
		result = new byte []
				{
				44, 56, -123, 81, -41, -12, -119, -20
				};
		result = Base64Util.decode("cpKarcZ8fNU=");
		result = new byte [] {
				-121, 10, -39, -79, 62, 19, -6, 126, -54, -31, 9, 105, -67, -118, 69, 34, -14, -105, 105, -88, -10, -58, 68, -112, 26, 116, -52, -77, -76, -33, -72, -33, 66, 92, 94, 31, -13, -101, -118, 56, 37, 43, -15, -83, -13, 65, -16, -95
		};
		
		result = Base64Util.decode("LDiFUdf0iew=");
				try {
			byte[] decryResult = DES.decrypt(result, password);
			System.out.println("解密后：" + new String(decryResult, "utf-8"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}*/

	private static final String PASSWORD = "12345678";
	/**
	 * 加密
	 * 
	 * @param datasource
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 */
	public static byte[] encrypt(byte[] datasource) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(PASSWORD.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(datasource);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param src
	 *            byte[]
	 * @param password
	 *            String
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		// 创建一个DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(PASSWORD.getBytes());
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// 真正开始解密操作
		return cipher.doFinal(src);
	}
}
