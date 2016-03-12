package mmn11;

import static org.junit.Assert.*;

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
public class TriangleTests {

	InputStream origIn;
	PrintStream origOut;
	double perimeter223;
	@Before
	public void before(){
		origIn  = System.in;
		origOut = System.out;
		double s=Triangle.area(2, 2, 3)/2.0;
		perimeter223=Math.pow(s*(s-2)*(s-2)*(s-3), 0.5);
	}
	@After
	public void after(){
		System.setIn(origIn);
		System.setOut(origOut);
	}
	
	@Test
	public void testEdges() {
		assertFalse(Triangle.validateEdges(1, 1, 3));
		assertTrue(Triangle.validateEdges(1, 2, 3));
		assertTrue(Triangle.validateEdges(2, 2, 3));
	}
	
	@Test
	public void testArea() {
		assertEquals(1+2+3, Triangle.area(1, 2, 3));
		assertEquals(2+2+3, Triangle.area(2, 2, 3));
	}

	@Test
	public void testHeronPerimeter() {
		assertEquals(0.0, Triangle.heronPerimeter(1, 2, 3), 0);
		assertEquals(perimeter223, Triangle.heronPerimeter(2, 2, 3),0);
	}
	
	private String simulateRun(String input){
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(String.format("%s\n",input).getBytes());
		System.setOut(new PrintStream(out));
		System.setIn(in);
		Triangle.main(new String[]{});
		return out.toString();
	}

	private void simulateInvalidInput(String input){
		String out = simulateRun(input);
		assertTrue(String.format("invalid input: %s", input), out.split("\n")[2].startsWith("expected 3 integers"));
	}
	private void simulateInvalidEdgesInput(String input){
		String out = simulateRun(input);
		assertTrue(String.format("invalid edges input: %s", input), out.split("\n")[2].startsWith("please enter a valid edge lengths"));
	}

	private void simulateValidInput(String input, int area, double perimeter){
		String out = simulateRun(input);
		String []lines =out.split("\n");
		assertEquals(String.format("area: %d", area), lines[2]);
		assertEquals(String.format("perimeter: %f", perimeter), lines[3]);
	}
	
	@Test
	public void testInvalidInput() {
		simulateInvalidInput("-1 2 3");
		simulateInvalidInput("1 -2 3");
	}

	@Test
	public void simulateInvalidEdgesInput() {
		simulateInvalidEdgesInput("1 2 0");
		simulateInvalidEdgesInput("1 3 1");
	}

	@Test
	public void simulateValidInput() {
		simulateValidInput("1 2 3", 1+2+3, 0);
		simulateValidInput("2 2 3", 2+2+3, perimeter223);
	}
}
