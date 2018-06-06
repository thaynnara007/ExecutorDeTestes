package testes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SingleTest {
	
	@Before
	public void setUp() {
		
	}
	@Test
	public void test1() {
		
		Assert.assertEquals(2, 1 + 1); 
		
	}
	
	@Test
	public void test2() {
		
		Assert.assertEquals(3, 1 + 1); 
		
	}

}