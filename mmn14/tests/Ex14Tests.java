

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

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
		Map<Integer,AtomicInteger> counts = new HashMap<>();
		for (int x : a){
			if (counts.containsKey(x)){
				counts.get(x).incrementAndGet();
			} else {
				counts.put(x, new AtomicInteger(1));
			}
		}
		for (int x:a){
			assertEquals(counts.get(x).get(), Ex14.count(a,x));		
		}
		assertEquals(0, Ex14.count(a,-10));		
		assertEquals(0, Ex14.count(a,100));		
	}
	@Test
	public void testDomino(){
		assertEquals(1, Ex14.domino(2,1));
		assertEquals(1, Ex14.domino(1,2));
		assertEquals(2, Ex14.domino(2,2));
		assertEquals(3, Ex14.domino(2,3));
	}
	
	@Test
	public void testPassword(){
		for (int i=0;i<100;i++){
			Password password = new Password(i);
			String pass = password.getPassword();
			assertEquals(i, pass.length());
			assertEquals(true, pass.matches(String.format("[a-z,A-Z]{%d,%d}",i,i)));
		}
	}

	
	/**
	 * run with -Xss1024m flag
	 * max length tested is 4
	 */
	@Test
	public void testFindPassword(){
		int len=4;
		Password p = new Password(len);
		assertEquals(p.getPassword(), Ex14.findPassword(p, len));
	}

	
}