


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import mmn15.PolyNode;
import mmn15.Polynom;


/**
 * @author yos
 *
 */
public class PolynomTests {

	
	private Polynom polinom;
	
	@Before
	public void before(){
		
	}
	
	@Test
	public void testAddNode(){
		assertEquals("6.5x^2", new PolyNode(2, 6.5).toString());
		assertEquals("-6.5x^2", new PolyNode(2, -6.5).toString());
		assertEquals("6.5", new PolyNode(0, 6.5).toString());
		assertEquals("-6.5", new PolyNode(0, -6.5).toString());
		assertEquals("-1", new PolyNode(0, -1).toString());
		assertEquals("1", new PolyNode(0, 1).toString());
		assertEquals("", new PolyNode(0, 0).toString());
		assertEquals("x^2", new PolyNode(2, 1).toString());
		assertEquals("-x^2", new PolyNode(2, -1).toString());
		assertEquals("3x", new PolyNode(1, 3).toString());
		assertEquals("-3x", new PolyNode(1, -3).toString());
	}

}
