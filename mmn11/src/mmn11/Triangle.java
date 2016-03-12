package mmn11;

import java.util.Scanner;

/**
 * @author yos
 *
 */
public class Triangle {
	static boolean validateEdges(int a, int b, int c){
		return (a+b>=c &&
			a+c>=b &&
			b+c>=a);
	}
	static int area(int a, int b, int c){
		return a+b+c;
	}
	static double heronPerimeter(int a, int b, int c){
		double s=area(a,b,c)/2.0;
		return Math.pow(s*(s-a)*(s-b)*(s-c),0.5);
	}
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("This program calculates the area and the perimeter of a given triangle. ");
		System.out.println("Please enter the three lengths of the triangle's sides");
		int a=scan.nextInt(),b=scan.nextInt(),c=scan.nextInt();
		if (a>-1 && b>-1 && c>-1){
			if (validateEdges(a,b,c)){
				System.out.println(String.format("area: %d", area(a,b,c)));
				System.out.println(String.format("perimeter: %f", heronPerimeter(a,b,c)));
			} else {
				System.out.println("please enter a valid edge lengths - for each x,y,z in (Edges:a,b,c) x+y>=z");
			}
		} else {
			System.out.println("expected 3 integers that are greater than 0 (ex: 1 2 3)");
		}
	}

}
