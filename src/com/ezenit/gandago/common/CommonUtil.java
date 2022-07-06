package com.ezenit.gandago.common;

import java.text.DecimalFormat;

public class CommonUtil {
	
	public String intToStringPrice(int input) {
		DecimalFormat formatter = new DecimalFormat("###,###");
		return formatter.format(input);
	}

}
