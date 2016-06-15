package com.vinoigitare.util;

public class RegexUtil {

	public static String getValidFileName(String fileName) {
		fileName.replace("^\\.+", "").replaceAll("[\\\\/:*?\"<>|]", "");
		return fileName;
	}
}
