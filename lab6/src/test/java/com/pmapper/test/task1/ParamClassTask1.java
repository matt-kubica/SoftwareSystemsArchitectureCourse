package com.pmapper.test.task1;

import com.pmapper.annotations.IsParameterPresent;

public class ParamClassTask1 {

	@IsParameterPresent(name="A")
	private boolean aPresent;
	
	@IsParameterPresent(name="B")
	private Boolean bPresent;

	public boolean isaPresent() {
		return aPresent;
	}

	public void setaPresent(boolean aPresent) {
		this.aPresent = aPresent;
	}

	public Boolean isbPresent() {
		return bPresent;
	}

	public void setbPresent(Boolean bPresent) {
		this.bPresent = bPresent;
	}

}
