


import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * @author yos
 *
 */
public class RGBColorTests {

	
	@Test
	public void testBlack() {
		RGBColor color = new RGBColor();
		assertEquals(0,color.getRed());
		assertEquals(0,color.getGreen());
		assertEquals(0,color.getBlue());
	}

	@Test
	public void testColor2() {
		RGBColor color = new RGBColor(100,150, 200);
		assertEquals(100,color.getRed());
		assertEquals(150,color.getGreen());
		assertEquals(200,color.getBlue());
	}

	
	@Test
	public void testInvert() {
		RGBColor color = new RGBColor(100,150, 200);
		color.invert();
		assertEquals(255-100,color.getRed());
		assertEquals(255-150,color.getGreen());
		assertEquals(255-200,color.getBlue());
	}

	
	@Test
	public void testGrayscale() {
		RGBColor color = new RGBColor(100,150, 200);
		double grayscale = color.convertToGrayscale();
		assertEquals(100*0.3+ 150*0.59+200*0.11,grayscale, 0);
	}

	@Test
	public void testMix() {
		RGBColor color1 = new RGBColor(100,150, 200);
		RGBColor color2 = new RGBColor(100,150, 200);
		
		assertEquals(color1, color2);
		color1.mix(color2);
		assertEquals(color1, color2);
	}
	

	@Test
	public void testToString() {
		RGBColor color = new RGBColor(100,150, 200);
		assertEquals("(100,150,200)", color.toString());
	}


	@Test
	public void testInvalidConstractorValues() {
		RGBColor color = new RGBColor(-1,300, 256);
		assertEquals("(0,0,0)", color.toString());
	}

	@Test
	public void testInvalidSetter() {
		RGBColor color = new RGBColor(100,100, 100);
		color.setBlue(555);
		color.setGreen(-1);
		color.setRed(256);
		assertEquals("(100,100,100)", color.toString());
	}
}
