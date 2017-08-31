package it.finsoft.util;

import java.security.*;
import java.math.*;

/**
 * Calculate MD5 hash
 * 
 * @see https://dzone.com/articles/get-md5-hash-few-lines-java
 *
 */
public class MD5 {

	/**
	 * Return MD5 hash in HEX form, lowercase.
	 */
	public static String digest(String s) {

		if (s == null)
			return null;

		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// Quite unlikely
			throw new RuntimeException(e);
		}
		m.update(s.getBytes(), 0, s.length());
		return new BigInteger(1, m.digest()).toString(16);
	}
}