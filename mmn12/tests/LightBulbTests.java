


import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * @author yos
 *
 */
public class LightBulbTests {

	
	@Test
	public void testValidConstructor1() {
		LightBulb lightBulb = new LightBulb(100,101,102);
		assertEquals(100,lightBulb.getColor().getRed());
		assertEquals(101,lightBulb.getColor().getGreen());
		assertEquals(102,lightBulb.getColor().getBlue());
		assertEquals(false,lightBulb.isSwitchedOn());
	}
	@Test
	public void testInValidConstructor1() {
		LightBulb lightBulb = new LightBulb(-100,-101,-102);
		assertEquals(0,lightBulb.getColor().getRed());
		assertEquals(0,lightBulb.getColor().getGreen());
		assertEquals(0,lightBulb.getColor().getBlue());
		assertEquals(false,lightBulb.isSwitchedOn());
	}

	@Test
	public void testValidConstructor2() {
		RGBColor color = new RGBColor(100,101,102);
		LightBulb lightBulb = new LightBulb(color);
		assertEquals(100,lightBulb.getColor().getRed());
		assertEquals(101,lightBulb.getColor().getGreen());
		assertEquals(102,lightBulb.getColor().getBlue());
		assertEquals(false,lightBulb.isSwitchedOn());
	}

	@Test
	public void testInValidConstructor2() {
		RGBColor color = new RGBColor(-100,101,-102);
		LightBulb lightBulb = new LightBulb(color);
		assertEquals(0,lightBulb.getColor().getRed());
		assertEquals(101,lightBulb.getColor().getGreen());
		assertEquals(0,lightBulb.getColor().getBlue());
		assertEquals(false,lightBulb.isSwitchedOn());
	}

	@Test
	public void testSwitchLight() {
		RGBColor color = new RGBColor(100,101,102);
		LightBulb lightBulb = new LightBulb(color);
		assertEquals(color,lightBulb.getColor());
		assertEquals(false,lightBulb.isSwitchedOn());
		lightBulb.switchLight();
		assertEquals(true,lightBulb.isSwitchedOn());
		lightBulb.switchLight();
		assertEquals(false,lightBulb.isSwitchedOn());
	}

	@Test
	public void testValidCopyConstructor() {
		LightBulb lightBulb1 = new LightBulb(new RGBColor(100,101,102));
		lightBulb1.switchLight();
		
		LightBulb lightBulb2 = new LightBulb(lightBulb1);
		assertEquals(lightBulb1,lightBulb2);		
		assertEquals(lightBulb1.getColor(),lightBulb2.getColor());		
		assertEquals(lightBulb1.isSwitchedOn(),lightBulb2.isSwitchedOn());		
		assertEquals(lightBulb1.toString(),lightBulb2.toString());		
	}

	

	@Test
	public void testToString() {
		LightBulb lightBulb1 = new LightBulb(new RGBColor(100,101,102));
		assertEquals("(100,101,102) Off", lightBulb1.toString());
		lightBulb1.switchLight();
		assertEquals("(100,101,102) On", lightBulb1.toString());
	}

}
