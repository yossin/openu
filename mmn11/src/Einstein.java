

import java.util.Scanner;

/**
 * @author yos
 *
 */
public class Einstein {
	final static String INVALID_3_DIGIT="‫‪The‬‬ ‫‪number‬‬ ‫‪you‬‬ ‫‪entered‬‬ ‫‪is‬‬ ‫‪not‬‬ ‫‪a‬‬ ‫‪3‬‬ ‫‪digit‬‬ ‫‪positive‬‬ ‫‪number‬‬"; 
	final static String INVALID_FIRST_LAST="fist digit should be different than the last"; 
	final static String SUCCESS="SUCCEEDED‬‬";
	final static String FAIL="FAILED";
	
	/**
	 * @param args - no args are required
	 */
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome‬‬ ‫‪to‬‬ ‫‪the‬‬ ‫‪Einstein‬‬ ‫‪magic‬‬ ‫‪gam‬‬e.");
		System.out.println("Please‬‬ ‫‪enter‬‬ ‫‪a‬‬ ‫‪3‬‬ ‫‪digit‬‬ ‫‪positive‬‬ ‫‪number‬‬ ‫‪whose‬‬ ‫‪first‬‬ ‫‪and‬‬ ‫‪last‬‬ ‫‪digits‬‬ ‫‪are‬‬ ‫‪different:‬‬");
		byte numArr[] = scan.nextLine().trim().getBytes();
		if (numArr.length!=3 || numArr[0]>'9' || numArr[0]<'1'
			|| numArr[1]>'9' || numArr[1]<'0'
			|| numArr[2]>'9' || numArr[2]<'0'){ // validate 3 digit number
			System.out.println(INVALID_3_DIGIT);
			return;
		}
		int num=new Integer(new String(numArr));
		if (numArr[0] == numArr[2]){ // check that first digit is different then last one
			System.out.println(INVALID_FIRST_LAST);
			return;
		}

		System.out.println(String.format("‫‪User‬‬ ‫‪number‬‬ ‫‪is:‬‬ %d", num));
		int reverseNum=(Integer.parseInt(new String(new byte[]{numArr[2],numArr[1],numArr[0]})));
		int diffNum = Math.abs(num-reverseNum);
		System.out.println(String.format("‫‪Difference:‬‬ %d", diffNum));
		int reversedDiffNum = (diffNum%10*100) + ((diffNum/10)%10)*10 + (diffNum/100);
		System.out.println(String.format("Reversed difference:‬‬ %d", reversedDiffNum));
		
		if (diffNum+reversedDiffNum == 1089){
			System.out.println(SUCCESS);
		} else {
			System.out.println(FAIL);
		}
	}

}
