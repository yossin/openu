package mmn14;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author yos
 *
 */
public class Ex14Tests {

	@Test
	public void testAreFriends(){
		assertEquals(true, Ex14.areFriends(220, 284));
		assertEquals(true, Ex14.areFriends(284, 220));

		assertEquals(false, Ex14.areFriends(219, 284));
		assertEquals(false, Ex14.areFriends(284, 219));
	}
	
	@Test
	public void testCountFriends(){
		assertEquals(5, Ex14.countFriends(9000));		
	}
	
	
	@Test
	public void testCount(){
		int a[]={-5,-5,1,1,1,1,1,1,1,1,2,2,2,2,2,3,3,3,67,67,99};
		//assertEquals(2, Ex14.count(a,-5));		
		//assertEquals(8, Ex14.count(a,1));		
		//assertEquals(5, Ex14.count(a,2));		
		//assertEquals(3, Ex14.count(a,3));		
		//assertEquals(2, Ex14.count(a,67));		
		assertEquals(1, Ex14.count(a,99));		
		assertEquals(0, Ex14.count(a,5));		
		assertEquals(0, Ex14.count(a,-10));		
		assertEquals(0, Ex14.count(a,100));		
	}

	
}