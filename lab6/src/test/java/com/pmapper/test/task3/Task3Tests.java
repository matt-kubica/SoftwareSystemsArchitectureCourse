package com.pmapper.test.task3;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pmapper.ParamMapper;
import com.pmapper.ParameterReadingException;
import com.pmapper.test.task1.ParamClassTask1;

public class Task3Tests {

	@Test(expected=ParameterReadingException.class)
	public void dontHaveMandatory() {
		ParamMapper mapper = new ParamMapper();
		String[] args ="-A text".split(" ");
		ParamClassTask3MandatoryField param = mapper.map(args , ParamClassTask3MandatoryField.class);
	}
	
	@Test(expected=ParameterReadingException.class)
	public void mandatoryEmpty() {
		ParamMapper mapper = new ParamMapper();
		String[] args ="-B -A text".split(" ");
		ParamClassTask3MandatoryField param = mapper.map(args , ParamClassTask3MandatoryField.class);
	}
	
	@Test
	public void hasMandatory() {
		ParamMapper mapper = new ParamMapper();
		String[] args ="-B text".split(" ");
		ParamClassTask3MandatoryField param = mapper.map(args , ParamClassTask3MandatoryField.class);
		
		assertNull(param.getaValue());
		assertEquals("text", param.getbValue());
	}
	
	@Test(expected=ParameterReadingException.class)
	public void allMandatoryMissingB() {
		ParamMapper mapper = new ParamMapper();
		String[] args ="-A text".split(" ");
		ParamClassTask3AllMandatory param = mapper.map(args , ParamClassTask3AllMandatory.class);
	}
	
	@Test(expected=ParameterReadingException.class)
	public void allMandatoryAEmpty() {
		ParamMapper mapper = new ParamMapper();
		String[] args ="-A -B text".split(" ");
		ParamClassTask3AllMandatory param = mapper.map(args , ParamClassTask3AllMandatory.class);
	}
	
	@Test
	public void allMandatoryOK() {
		ParamMapper mapper = new ParamMapper();
		String[] args ="-B textB -A textA".split(" ");
		ParamClassTask3MandatoryField param = mapper.map(args , ParamClassTask3MandatoryField.class);
		
		assertEquals("textA", param.getaValue());
		assertEquals("textB", param.getbValue());
	}

}
