package com.wenlie.chong4;

/**
 * <p>TODO </p>
 * User: jc.tao <tantao@aipa.me>
 * Date: 13-10-29
 * Time: PM3:41
 */
public class Util {
	public static final String EMPTY_STRING = "";

	public static boolean isEmpty(String str) {
		if (str == null || EMPTY_STRING.equals(str)) {
			return true;
		}
		return false;
	}
}
