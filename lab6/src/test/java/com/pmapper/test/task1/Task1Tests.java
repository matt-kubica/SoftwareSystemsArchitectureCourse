package com.pmapper.test.task1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.pmapper.ParamMapper;
import com.pmapper.ParameterReadingException;


public class Task1Tests {

	@Test
	public void allFalse(){
		ParamMapper mapper = new ParamMapper();
		String[] args ={};
		ParamClassTask1 param = mapper.map(args , ParamClassTask1.class);
		
		assertFalse(param.isaPresent());
		assertFalse(param.isbPresent());
	}
	
	@Test
	public void aPresent(){
		ParamMapper mapper = new ParamMapper();
		String[] args ={"-A"};
		ParamClassTask1 param = mapper.map(args , ParamClassTask1.class);
		
		assertTrue(param.isaPresent());
		assertFalse(param.isbPresent());
	}
	
	@Test
	public void bPresent(){
		ParamMapper mapper = new ParamMapper();
		String[] args ={"-B"};
		ParamClassTask1 param = mapper.map(args , ParamClassTask1.class);
		
		assertFalse(param.isaPresent());
		assertTrue(param.isbPresent());
	}
	
	@Test
	public void bothPresent(){
		ParamMapper mapper = new ParamMapper();
		String[] args ={"-A","-B"};
		ParamClassTask1 param = mapper.map(args , ParamClassTask1.class);
		
		assertTrue(param.isaPresent());
		assertTrue(param.isbPresent());
	}
	
	@Test
	public void unmappedParamPresent(){
		ParamMapper mapper = new ParamMapper();
		String[] args ={"-A","-B","-C"};
		ParamClassTask1 param = mapper.map(args , ParamClassTask1.class);
		
		assertTrue(param.isaPresent());
		assertTrue(param.isbPresent());
	}

	@Test(expected=ParameterReadingException.class)
	public void nonBooleanParamMapped(){
		ParamMapper mapper = new ParamMapper();
		String[] args ={"-A"};
		mapper.map(args , InvalidParamClassTask1.class);
	}
	
}
