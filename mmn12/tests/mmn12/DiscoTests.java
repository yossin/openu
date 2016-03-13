package mmn12;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author yos
 *
 */
public class DiscoTests {

	private LightBulb b1,b2;
	@Before
	public void before(){
		b1=new LightBulb(100,100,100);
		b2=new LightBulb(200,200,200);
	}
	
	private Disco createDisco(LightBulb b1, LightBulb b2, LightBulb b3, LightBulb b4){
		return new Disco(
				new LightBulb(b1),
				new LightBulb(b2),
				new LightBulb(b3),
				new LightBulb(b4));
	}
	
	
	@Test
	public void testSwitchBulb1() {
		Disco disco = createDisco(b1,b1,b1,b1);
		assertEquals(false,disco.getFirstBulb().isSwitchedOn());
		assertEquals(false,disco.getSecondBulb().isSwitchedOn());
		assertEquals(false,disco.getThirdBulb().isSwitchedOn());
		assertEquals(false,disco.getFourthBulb().isSwitchedOn());
		disco.switchBulb(1);
		assertEquals(true,disco.getFirstBulb().isSwitchedOn());
		assertEquals(false,disco.getSecondBulb().isSwitchedOn());
		assertEquals(false,disco.getThirdBulb().isSwitchedOn());
		assertEquals(false,disco.getFourthBulb().isSwitchedOn());
	}
	@Test
	public void testSwitchBulb2() {
		Disco disco = createDisco(b1,b1,b1,b1);
		assertEquals(false,disco.getFirstBulb().isSwitchedOn());
		assertEquals(false,disco.getSecondBulb().isSwitchedOn());
		assertEquals(false,disco.getThirdBulb().isSwitchedOn());
		assertEquals(false,disco.getFourthBulb().isSwitchedOn());
		disco.switchBulb(2);
		assertEquals(false,disco.getFirstBulb().isSwitchedOn());
		assertEquals(true,disco.getSecondBulb().isSwitchedOn());
		assertEquals(false,disco.getThirdBulb().isSwitchedOn());
		assertEquals(false,disco.getFourthBulb().isSwitchedOn());
	}
	@Test
	public void testSwitchBulb3() {
		Disco disco = createDisco(b1,b1,b1,b1);
		assertEquals(false,disco.getFirstBulb().isSwitchedOn());
		assertEquals(false,disco.getSecondBulb().isSwitchedOn());
		assertEquals(false,disco.getThirdBulb().isSwitchedOn());
		assertEquals(false,disco.getFourthBulb().isSwitchedOn());
		disco.switchBulb(3);
		assertEquals(false,disco.getFirstBulb().isSwitchedOn());
		assertEquals(false,disco.getSecondBulb().isSwitchedOn());
		assertEquals(true,disco.getThirdBulb().isSwitchedOn());
		assertEquals(false,disco.getFourthBulb().isSwitchedOn());
	}
	@Test
	public void testSwitchBulb4() {
		Disco disco = createDisco(b1,b1,b1,b1);
		assertEquals(false,disco.getFirstBulb().isSwitchedOn());
		assertEquals(false,disco.getSecondBulb().isSwitchedOn());
		assertEquals(false,disco.getThirdBulb().isSwitchedOn());
		assertEquals(false,disco.getFourthBulb().isSwitchedOn());
		disco.switchBulb(4);
		assertEquals(false,disco.getFirstBulb().isSwitchedOn());
		assertEquals(false,disco.getSecondBulb().isSwitchedOn());
		assertEquals(false,disco.getThirdBulb().isSwitchedOn());
		assertEquals(true,disco.getFourthBulb().isSwitchedOn());
	}

	
	@Test
	public void testTurn() {
		Disco disco = createDisco(b1,b1,b1,b1);
		assertEquals(false,disco.getFirstBulb().isSwitchedOn());
		assertEquals(false,disco.getSecondBulb().isSwitchedOn());
		assertEquals(false,disco.getThirdBulb().isSwitchedOn());
		assertEquals(false,disco.getFourthBulb().isSwitchedOn());
		disco.turnAllOn();
		assertEquals(true,disco.getFirstBulb().isSwitchedOn());
		assertEquals(true,disco.getSecondBulb().isSwitchedOn());
		assertEquals(true,disco.getThirdBulb().isSwitchedOn());
		assertEquals(true,disco.getFourthBulb().isSwitchedOn());
		assertEquals(true,disco.areAllOn());
		disco.turnAllOff();
		assertEquals(false,disco.getFirstBulb().isSwitchedOn());
		assertEquals(false,disco.getSecondBulb().isSwitchedOn());
		assertEquals(false,disco.getThirdBulb().isSwitchedOn());
		assertEquals(false,disco.getFourthBulb().isSwitchedOn());
		assertEquals(true,disco.areAllOff());
	}


	@Test
	public void testSameColor() {
		Disco disco = createDisco(b1,b1,b1,b1);
		assertEquals(true,disco.allSameColor());
	}

	@Test
	public void testNotSameColor() {
		Disco disco = createDisco(b2,b1,b1,b1);
		assertEquals(false,disco.allSameColor());
		assertEquals(false, disco.getFirstBulb().getColor().equals(disco.getFourthBulb().getColor()));
		assertEquals(false, disco.getFirstBulb().getColor().equals(disco.getThirdBulb().getColor()));
		assertEquals(false, disco.getFirstBulb().getColor().equals(disco.getSecondBulb().getColor()));

		assertEquals(true, disco.getSecondBulb().getColor().equals(disco.getFourthBulb().getColor()));
		assertEquals(true, disco.getSecondBulb().getColor().equals(disco.getThirdBulb().getColor()));
	}

}
