package net.lax1dude.eaglercraft.v1_8;
/**
 * Copyright (c) 2022-2023 LAX1DUDE. All Rights Reserved.
 *
 * WITH THE EXCEPTION OF PATCH FILES, MINIFIED JAVASCRIPT, AND ALL FILES
 * NORMALLY FOUND IN AN UNMODIFIED MINECRAFT RESOURCE PACK, YOU ARE NOT ALLOWED
 * TO SHARE, DISTRIBUTE, OR REPURPOSE ANY FILE USED BY OR PRODUCED BY THE
 * SOFTWARE IN THIS REPOSITORY WITHOUT PRIOR PERMISSION FROM THE PROJECT AUTHOR.
 *
 * NOT FOR COMMERCIAL OR MALICIOUS USE
 *
 * (please read the 'LICENSE' file this repo's root directory for more info)
 *
 */
public class ArrayUtils {


	public static String[] subarray(String[] stackTrace, int i, int j) {
		String[] ret = new String[j - i];
		System.arraycopy(stackTrace, i, ret, 0, j - i);
		return ret;
	}

	public static String asciiString(byte[] bytes) {
		char[] str = new char[bytes.length];
		for(int i = 0; i < bytes.length; ++i) {
			str[i] = (char)((int) bytes[i] & 0xFF);
		}
		return new String(str);
	}

	public static byte[] asciiString(String string) {
		byte[] str = new byte[string.length()];
		for(int i = 0; i < str.length; ++i) {
			str[i] = (byte)string.charAt(i);
		}
		return str;
	}

}
