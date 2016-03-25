

import java.util.Scanner;

/**
 * @author yos
 *
 */
public class Triangle {
	final static String INVALID_EDGES_LENGTHS="please enter a valid edge lengths - for each x,y,z in (Edges:a,b,c) x+y>=z";
	final static String INVALID_EDGES_NUM="expected 3 integers that are greater than 0 (ex: 1 2 3)";
	
	/**
	 * @param args - no args are required
	 */
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("This program calculates the area and the perimeter of a given triangle. ");
		System.out.println("Please enter the three lengths of the triangle's sides");
		int a=scan.nextInt(),b=scan.nextInt(),c=scan.nextInt();
		if (a>-1 && b>-1 && c>-1){
			if (a+b>=c && a+c>=b && b+c>=a){ // validate edges
				final int area = a+b+c; // calculate area
				final double s=area/2.0; // calculate s: area/2
				final double perimeter= Math.pow(s*(s-a)*(s-b)*(s-c),0.5); // calculate heron perimeter
				System.out.println(String.format("area: %d", area));
				System.out.println(String.format("perimeter: %f", perimeter));
			} else {
				System.out.println(INVALID_EDGES_LENGTHS);
			}
		} else {
			System.out.println(INVALID_EDGES_NUM);
		}
	}

}
