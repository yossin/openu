package mmn11;

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
	 * @param num - number to validate
	 * @param validationResult - describe the validation result (output parameter)
	 * @return validation append INVALID_3_DIGIT or INVALID_FIRST_LAST in case of a validation error into 
	 */
	static boolean validateNum(int num, StringBuilder validationResult){
		if (num<100 || num>999){
			validationResult.append(INVALID_3_DIGIT);
			return false;
		}
		if (num%10 == num/100){
			validationResult.append(INVALID_FIRST_LAST);
			return false;
		}
		return true;
	}
	/**
	 * @param num
	 * @return reverse num (eg: 123 will resilt 321)
	 */
	static int reverseNum(int num){
		return (num%10*100)
				+ ((num/10)%10)*10
				+ (num/100);
	}

	
	/**
	 * @param args - no args are required
	 */
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome‬‬ ‫‪to‬‬ ‫‪the‬‬ ‫‪Einstein‬‬ ‫‪magic‬‬ ‫‪gam‬‬e.");
		System.out.println("Please‬‬ ‫‪enter‬‬ ‫‪a‬‬ ‫‪3‬‬ ‫‪digit‬‬ ‫‪positive‬‬ ‫‪number‬‬ ‫‪whose‬‬ ‫‪first‬‬ ‫‪and‬‬ ‫‪last‬‬ ‫‪digits‬‬ ‫‪are‬‬ ‫‪different:‬‬");
		int num=scan.nextInt();
		StringBuilder validationResult = new StringBuilder();
		if (validateNum(num,validationResult)){
			System.out.println(String.format("‫‪User‬‬ ‫‪number‬‬ ‫‪is:‬‬ %d", num));
			int diffNum = Math.abs(num-reverseNum(num));
			System.out.println(String.format("‫‪Difference:‬‬ %d", diffNum));
			int reversedDiffNum = reverseNum(diffNum);
			System.out.println(String.format("Reversed difference:‬‬ %d", reversedDiffNum));
			
			if (diffNum+reversedDiffNum == 1089){
				System.out.println(SUCCESS);
			} else {
				System.out.println(FAIL);
			}
		} else {
			System.out.println(validationResult);
		}
	}

}
