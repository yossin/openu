


import static org.junit.Assert.assertEquals;

import org.junit.Test;



/**
 * @author yos
 *
 */
public class PolynomTests {

	private static Polynom createPolynom (double[]...pols){
		Polynom polynom = new Polynom();
		for (double pol[]:pols){
			polynom.addNode(new PolyNode((int)pol[0], pol[1]));
		}
		return polynom;
	}

	private static Polynom createPolynom (int maxPower, double coefficient){
		Polynom polynom = new Polynom();
		for (int i=0;i<=maxPower; i++){
			polynom.addNode(new PolyNode(i, coefficient));
		}
		return polynom;
	}
	
	
	@Test
	public void testAddNode(){
		Polynom polynom1 = createPolynom(5, 4);
		Polynom polynom2 = createPolynom(5, 2);
		
		assertEquals(false, polynom1.equals(polynom2));
		for (int i=0;i<=5;i++){
			polynom2.addNode(new PolyNode(i, 2));
		}
		assertEquals(polynom1, polynom2);
		polynom2.addNode(new PolyNode(5, -4));
		assertEquals("4x^4+4x^3+4x^2+4x+4", polynom2.toString());
		polynom2.addNode(new PolyNode(0, -4));
		assertEquals("4x^4+4x^3+4x^2+4x", polynom2.toString());
		polynom2.addNode(new PolyNode(3, -4));
		assertEquals("4x^4+4x^2+4x", polynom2.toString());
		polynom2.addNode(new PolyNode(2, -5));
		assertEquals("4x^4-x^2+4x", polynom2.toString());
		polynom2.addNode(new PolyNode(3, 1));
		assertEquals("4x^4+x^3-x^2+4x", polynom2.toString());
		polynom2.addNode(new PolyNode(3, -1));
		assertEquals("4x^4-x^2+4x", polynom2.toString());
		polynom2.addNode(new PolyNode(4, -4));
		assertEquals("-x^2+4x", polynom2.toString());
		polynom2.addNode(new PolyNode(2, 1));
		assertEquals("4x", polynom2.toString());
		polynom2.addNode(new PolyNode(1, -4));
		assertEquals("", polynom2.toString());
	}

	@Test
	public void testMultByScalar(){
		Polynom polynom1 = createPolynom(5, 0.2);
		
		assertEquals("0.2x^5+0.2x^4+0.2x^3+0.2x^2+0.2x+0.2", polynom1.toString());
		polynom1.multByScalar(5);
		assertEquals("x^5+x^4+x^3+x^2+x+1", polynom1.toString());
		polynom1.multByScalar(0);
		assertEquals("", polynom1.toString());
	}
	
	@Test
	public void testAddPol(){
		Polynom polynom1 = createPolynom(5, 2);
		
		assertEquals("2x^5+2x^4+2x^3+2x^2+2x+2", polynom1.toString());
		polynom1.addPol(createPolynom(5, -1));
		assertEquals("x^5+x^4+x^3+x^2+x+1", polynom1.toString());
		polynom1.addPol(createPolynom(6, 2));
		assertEquals("2x^6+3x^5+3x^4+3x^3+3x^2+3x+3", polynom1.toString());
		polynom1.addPol(createPolynom(7, -3));
		assertEquals("-3x^7-x^6", polynom1.toString());
	}


	@Test
	public void testMulPol(){
		Polynom p = createPolynom(new double[][]{
			new double[]{3.0,8.0},
			new double[]{2.0,-3.0},
			new double[]{1.0,-1.0},
			new double[]{0.0,7.0},
			});
		Polynom q = createPolynom(new double[][]{
			new double[]{2.0,1.0},
			new double[]{1.0,6.0},
			new double[]{0.0,-15.0},
			});
		
		assertEquals("8x^3-3x^2-x+7", p.toString());
		assertEquals("x^2+6x-15", q.toString());
		assertEquals("8x^5+45x^4-139x^3+46x^2+57x-105", p.multPol(q).toString());
	}

	@Test
	public void testDifferential(){
		Polynom p = createPolynom(new double[][]{
			new double[]{3.0,8.0},
			new double[]{2.0,-3.0},
			new double[]{1.0,-1.0},
			new double[]{0.0,7.0},
			});
		
		assertEquals("8x^3-3x^2-x+7", p.toString());
		assertEquals("24x^2-6x-1", p.differential().toString());
	}
	
}
