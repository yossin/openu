


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import mmn15.PolyNode;


/**
 * @author yos
 *
 */
public class PolyNodeTests {

	@Test
	public void testToString(){
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
