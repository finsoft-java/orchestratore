package it.finsoft.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class MD5Test {

	@Test
	public void test() {
		String testString = "paperino";
		String targetMD5 = "B54B45B19CA1F1DDC424E6B878A53F2D".toLowerCase();

		assertEquals(targetMD5, MD5.digest(testString));
	}

}
