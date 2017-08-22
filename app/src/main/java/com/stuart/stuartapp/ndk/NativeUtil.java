package com.stuart.stuartapp.ndk;

public class NativeUtil {

	static {
		System.loadLibrary("fxp");
	}
	
	public native byte[] encode(byte[] bt);
	
	
	public native byte[] decode(byte[] bt);
	
	public native void init();
	
	public native void close();
}
