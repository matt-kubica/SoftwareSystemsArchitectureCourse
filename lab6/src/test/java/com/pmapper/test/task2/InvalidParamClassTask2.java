package com.pmapper.test.task2;

import com.pmapper.annotations.TextValue;

public class InvalidParamClassTask2 {

	@TextValue(name="A")
	private boolean aValue;

	public boolean getaValue() {
		return aValue;
	}

	public void setaValue(boolean aValue) {
		this.aValue = aValue;
	}

}
