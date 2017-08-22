package com.stuart.stuartapp.ndk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.util.Log;

public class NdkManager {

	private static NdkManager ndkManager;
	private static NativeUtil mNativeUtil;
	private static int ENCODE_SIZE = 11;
//	public static final byte[] HEADER = new byte[]{0x5A, 0x23};
//	public static int encodeCount = 0;

	private NdkManager() {
		
	}

	public static NdkManager getNdkManager() {
		if (ndkManager == null){
			ndkManager = new NdkManager();
			mNativeUtil = new NativeUtil();
			mNativeUtil.init();
		}
		return ndkManager;
	}

	/**
	 * 压缩文件
	 * @param bytes 待压缩的字节数组
	 * @return	压缩后的字节数组
	 */
	public List<byte[]> encodeData(byte[] bytes) {
		swapBytes(bytes);
		List<byte[]> subByet = NdkManager.subByet(bytes, 1080);
		List<byte[]> byteList = new ArrayList<byte[]>();
		for (byte[] v : subByet) {
			byte[] setByteArray = mNativeUtil.encode(v);
		/*	byte[] newbyte = new byte[HEADER.length + setByteArray.length];
			System.arraycopy(HEADER, 0, newbyte, 0, HEADER.length);
			System.arraycopy(setByteArray, 0, newbyte, newbyte.length - setByteArray.length, setByteArray.length);
			if (newbyte.length > 0) {
				byteList.add(newbyte);
			}*/
			byteList.add(Arrays.copyOf(setByteArray, 10));
		}
		
		byte[] byte1 = NdkManager.getByte(byteList);
		return byteList;
	}
	
//	public static void resetEncodeCount() {
//		encodeCount = 0;
//	}
	
	public static void closeEncDecObject(){
		if (mNativeUtil != null)
		mNativeUtil.close();
		Log.i("p5dtalkback", "end------NativeUtil.close()");
	}
 
	/**
	 * 顺次将字节数组中偶位和奇位交换：0和1,2和3，.....
	 * @param bytes		待交换的数组
	 */
	private void swapBytes(byte[] bytes) {
		int half = bytes.length/2;
		for(int i = 0;i<half;i++){
			bytes[i*2] ^= bytes[i*2+1];
			bytes[i*2+1] ^= bytes[i*2];
			bytes[i*2] ^= bytes[i*2+1];
		}
	}


	/**
	 * 解压文件 
	 * @param bytes 解压的源文件路径
	 * @return	解压后的数据
	 */
	public byte[] decodeData(byte[] bytes) {
		List<byte[]> subByet = NdkManager.subByet(bytes, ENCODE_SIZE);
		List<byte[]> byteList = new ArrayList<byte[]>();
		for (byte[] v : subByet) {
			byte[] newbyte = new byte[11];
			System.arraycopy(v, 0, newbyte, 0, v.length);
			byte[] setByteArray = mNativeUtil.decode(newbyte);
			if (setByteArray.length > 0) {
				byteList.add(setByteArray);
			}
		}
		
		byte[] byte1 = NdkManager.getByte(byteList);
		swapBytes(byte1);
		return byte1;
	}

	/**
	 * 将字节数组截取成指定大小的数据集合
	 * 长度不足指定大小的舍去
	 * @param bytes 要截取的数组
	 * @param size 每段数组的长度
	 * @return
	 */
	public static List<byte[]> subByet(byte[] bytes, int size) {
		List<byte[]> bs = new ArrayList<byte[]>();
		if (size > 0 && bytes != null && bytes.length >= size) {
			int subLength = 0;
			while (subLength + size <= bytes.length) {
				byte[] b = Arrays.copyOfRange(bytes, subLength, subLength + size);
				bs.add(b);
				subLength += size;
			}
		}
		return bs;
	}

	private static byte[] getByte(List<byte[]> bytes){
		if (bytes != null && bytes.size() > 0) {
			int totalLength = 0;
			for (int i = 0; i < bytes.size() ; i++) {
				totalLength += bytes.get(i).length;
			}
			byte[] bt= new byte[totalLength];
			int positon = 0;
			for (int i = 0; i < bytes.size() ; i++) {
				byte[] bs = bytes.get(i);
				System.arraycopy(bs, 0, bt, positon, bs.length);
				positon += bs.length;
			}
			return bt;
		}
		return null;
	}
	
	
}
