package com.fangrongfu.chuhuoguanli;

import java.text.SimpleDateFormat;

public class CreateId {
	private static long n = 1;

	public CreateId(){

	}

	public static String test(long l) {
		String str = new SimpleDateFormat("yyyyMMDD").format(new java.util.Date());
		long m = Long.parseLong((str)) * 10000;
		long ret = m + l;
		n = l + 1;
		return Long.toString(ret);
	}
}
