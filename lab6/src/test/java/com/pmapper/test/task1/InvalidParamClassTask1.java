package com.pmapper.test.task1;

import com.pmapper.annotations.IsParameterPresent;

public class InvalidParamClassTask1 {

	@IsParameterPresent(name="A")
	private String aPresent;

	public String getaPresent() {
		return aPresent;
	}

	public void setaPresent(String aPresent) {
		this.aPresent = aPresent;
	}
	
}
