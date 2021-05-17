package com.pmapper.test.task2;

import com.pmapper.annotations.IsParameterPresent;
import com.pmapper.annotations.TextValue;

public class ParamClassTask2 {

	@IsParameterPresent(name="A")
	private boolean aPresent;
	
	@TextValue(name="A")
	private String aValue;
	
	@TextValue(name="B")
	private String bValue;

	public boolean isaPresent() {
		return aPresent;
	}

	public void setaPresent(boolean aPresent) {
		this.aPresent = aPresent;
	}

	public String getaValue() {
		return aValue;
	}

	public void setaValue(String aValue) {
		this.aValue = aValue;
	}

	public String getbValue() {
		return bValue;
	}

	public void setbValue(String bValue) {
		this.bValue = bValue;
	}



}
