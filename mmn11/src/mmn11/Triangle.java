package mmn11;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Triangle {
	private final static String DELIMITER="\\s*,?\\s*";
	private final static String NUMBER="(\\d+)";
	
	private static int toInt(Matcher matcher, int group){
		return Integer.parseInt(matcher.group(group));
	}
	
	static boolean validateEdges(int a, int b, int c){
		return (a+b>=c &&
			a+c>=b &&
			b+c>=a);
	}
	
	static int area(int a, int b, int c){
		if (!validateEdges(a, b, c)){
			throw new IllegalArgumentException(String.format("invalid edges lengths - for each x,y,z in (Edges:%d,%d,%d) x+y>=z",a,b,c));
		}
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
		Pattern pattern = Pattern.compile(String.format("%s%s%s%s%s", NUMBER,DELIMITER,NUMBER,DELIMITER,NUMBER));
		Matcher matcher = pattern.matcher(scan.nextLine());
		if (matcher.matches()){
			int a=toInt(matcher, 1),b=toInt(matcher, 2),c=toInt(matcher, 3);
			if (validateEdges(a,b,c)){
				System.out.println(String.format("area: %d", area(a,b,c)));
				System.out.println(String.format("perimeter: %f", heronPerimeter(a,b,c)));
			} else {
				System.out.println("please enter a valid edge lengths - for each x,y,z in (Edges:a,b,c) x+y>=z");
			}
		} else {
			System.out.println("expected 3 integers, that are greater than 0 (ex: 1,2,3)");
		}
	}

}
