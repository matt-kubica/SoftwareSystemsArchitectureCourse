package com.pmapper.test.task2;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pmapper.ParamMapper;
import com.pmapper.exceptions.ParameterReadingException;

public class Task2Tests {

	@Test
	public void simpleTextParam() throws ParameterReadingException {
		ParamMapper mapper = new ParamMapper();
		String[] args = "-A text".split(" ");
		ParamClassTask2 param = mapper.map(args , ParamClassTask2.class);
		
		assertTrue(param.isaPresent());
		assertEquals("text", param.getaValue());
	}
	
	@Test
	public void compositeTextParam() throws ParameterReadingException {
		ParamMapper mapper = new ParamMapper();
		String[] args = "-A composite text -B other text".split(" ");;
		ParamClassTask2 param = mapper.map(args , ParamClassTask2.class);
		
		assertEquals("composite text", param.getaValue());
		assertEquals("other text", param.getbValue());
	}
	
	@Test
	public void emptyString() throws ParameterReadingException {
		ParamMapper mapper = new ParamMapper();
		String[] args = "-A -B text".split(" ");
		ParamClassTask2 param = mapper.map(args , ParamClassTask2.class);
		
		assertEquals("", param.getaValue());
		assertEquals("text", param.getbValue());
	}
	
	@Test
	public void nullValue() throws ParameterReadingException {
		ParamMapper mapper = new ParamMapper();
		String[] args = "-B text".split(" ");
		ParamClassTask2 param = mapper.map(args , ParamClassTask2.class);
		
		assertNull(param.getaValue());
		assertEquals("text", param.getbValue());
	}
	
	@Test(expected=ParameterReadingException.class)
	public void commandLineStartingWithValue() throws ParameterReadingException {
		ParamMapper mapper = new ParamMapper();
		String[] args = "text -B text".split(" ");;
		ParamClassTask2 param = mapper.map(args , ParamClassTask2.class);
	}
	
	@Test(expected=ParameterReadingException.class)
	public void classWithInvalidPropertyType() throws ParameterReadingException {
		ParamMapper mapper = new ParamMapper();
		String[] args = "-A text".split(" ");
		InvalidParamClassTask2 param = mapper.map(args , InvalidParamClassTask2.class);
	}

}
