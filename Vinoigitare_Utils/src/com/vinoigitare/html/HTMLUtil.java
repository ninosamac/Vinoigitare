package com.vinoigitare.html;

public class HTMLUtil {
	
	public static String escape(String s) {
		
		StringBuilder builder = new StringBuilder();
		
		boolean previousWasASpace = false;
		final char[] charArray = s.toCharArray();
		for (char c : charArray) {
			if (c == ' ') {
				if (previousWasASpace) {
					builder.append("&nbsp;");
					previousWasASpace = false;
					continue;
				}
				previousWasASpace = true;
			} else {
				previousWasASpace = false;
			}
			switch (c) {
			case '<':
				builder.append("&lt;");
				break;
			case '>':
				builder.append("&gt;");
				break;
			case '&':
				builder.append("&amp;");
				break;
			case '"':
				builder.append("&quot;");
				break;
			case '\n':
				builder.append("<br>");
				break;
			// We need Tab support here, because we print StackTraces as HTML
			case '\t':
				builder.append("&nbsp; &nbsp; &nbsp;");
				break;
			default:
				if (c < 128) {
					builder.append(c);
				} else {
					builder.append("&#").append((int) c).append(";");
				}
			}
		}
		
		return builder.toString();		
	}
}
