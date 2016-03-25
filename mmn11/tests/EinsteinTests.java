

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author yos
 *
 */
public class EinsteinTests {

	InputStream origIn;
	PrintStream origOut;
	@Before
	public void before(){
		origIn  = System.in;
		origOut = System.out;
	}
	@After
	public void after(){
		System.setIn(origIn);
		System.setOut(origOut);
	}

	
	private String simulateRun(String input){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(String.format("%s\n",input).getBytes());
		System.setOut(new PrintStream(out));
		System.setIn(in);
		Einstein.main(new String[]{});
		return out.toString();
	}

	private void simulateOutput(String input, String expectedMessage, int lineNum){
		String out = simulateRun(input);
		String line = out.split("\n")[lineNum];
		assertEquals(expectedMessage, line);
	}
	
	@Test
	public void testInvalid3DigitInput() {
		simulateOutput("", Einstein.INVALID_3_DIGIT,2);
		simulateOutput("1", Einstein.INVALID_3_DIGIT,2);
		simulateOutput("12", Einstein.INVALID_3_DIGIT,2);
		simulateOutput("1112", Einstein.INVALID_3_DIGIT,2);
		simulateOutput("-112", Einstein.INVALID_3_DIGIT,2);
	}

	@Test
	public void simulateInvalidFirstLast() {
		simulateOutput("111", Einstein.INVALID_FIRST_LAST,2);
		simulateOutput("121", Einstein.INVALID_FIRST_LAST,2);
	}
	
	
	@Test
	public void simulateValidInput() {
		for (int i=100;i<1000;i++){
			if (i%10 != i/100){
				simulateOutput(""+i, Einstein.SUCCESS,5);
			}
		}
	}

	
}
