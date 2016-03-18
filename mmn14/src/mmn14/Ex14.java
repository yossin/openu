package mmn14;

import java.util.Random;

public class Ex14 {

	/**
	 * @param a
	 * @return sum of a's dividers
	 */
	static int sumDividers(int a){
		int sum=0;
		for (int i=1; i<(a/2+1);i++){
			if (a % i == 0){
				sum +=i;
			}
		}
		return sum;
	}
	/**
	 * @param a
	 * @param b
	 * @return true if a & b are friends
	 */
	static boolean areFriends(int a, int b){
		return sumDividers(a) == b 
				&& sumDividers(b)==a;
	}
	
	/**
	 * @param n number
	 * @return the number of friends exists from 1 to n-1
	 */
	public static int countFriends(int n){
		int countArr[]=new int[n];
		// prepare an array that holds the sum of dividers 
		// for every number between 1 to n-1
		for (int i=1;i<n;i++){
			countArr[i]=sumDividers(i);
		}
		int count=0;
		// count friends for every 2 numbers in 1 to n-1
		// count if x,y (x!=y) and  x is friend of y
		for (int i=1; i<n; i++){
			for (int j=0;j<n;j++){
				if (i!=j && countArr[i]== j && countArr[j]==i){
					count++;
				}
			}
		}
		return count/2;
	}
	
	/**
	 * perform a binary search to find x in a. 
	 * then count the number of times x is in a.
	 * @param a sorted array 
	 * @param x number
	 * @return the times x is in a
	 */
	public static int count (int a[], int x){
		int len=a.length;
		int index=len/2, delta=index/2, log=index>0?1:0, count=0;
		// perform a binary search
		for (;delta>0; delta/=2, log++){ 
			if (x>a[index]){ // go forward in case x > a[index]
				index+=delta;
			} else if (x<a[index]){ // go backward - in case x < a[index] 
				index-=delta;
			} else { // we found x - stop searching
				break;
			}
		}
		// in case we haven't found x yet complete the search 
		// by searching a window of log(a.length)
		if (a[index]<x){ // search forward 
			for (int limit=index+log;index<limit && index<len && a[index]<x; index++);
		} else if (a[index]>x){ // search backward
			for (int limit=index-log;index>limit && index>0 && a[index]>x; index--);
		}
		// in case we found x
		for (int i=index;i<len && a[i]==x; i++){ //count the times and move forward
			count++;
		}
		for (int i=index-1;i>-1 && a[i]==x; i--){//count the times and move backward
			count++;
		}
		return count;
	}
	
	/**
	 *  let's assume m is even (n or m must be even, otherwise it is impossible)
	 *  <ul>
	 *  <li>if m==2 and n>2 we can cover it by 2 options:  2x(n-1)  + 2x(n-2):
	 *  <table><tr>
	 *    <td>x???..<br>x???..</td>
	 *    <td>+<td>
	 *    <td>xx???..<br>yy???..</td>
	 *  </tr><table>
	 *  </li><li>if m==2 and n>1 there is only one way:
	 *  <table><tr>
	 *   <td>x</br>x</td>
	 *  </tr></table>
	 *  </li><li>if m==2 and n==2 there are 2 ways:
	 *  <table><tr>
	 *    <td>xx<br>yy</td>
	 *    <td>+<td>
	 *    <td>xy<br>xy</td>
	 *  </tr><table>
	 *  </li><li>if m>2 we can cover 2 boards of: 2xn, (m-2)xn
	 *  </li>
	 * @param m
	 * @param n
	 * @return number of options to fill a Domino board
	 */
	public static int domino(int m, int n){
		if (m==0 || n==0)  {
			return 0;
		} else  if (n%2 ==0){ // n is even
			if (n>2){
				return domino (m,n-2) + domino(m, 2);
			} else { //n==2
				if (m<3){
					return m;
				} else {
					return domino (m-1,2) + domino(m-2,2);
				}
			}
		} else if (m%2 ==0){ // m is even
			return domino (n,m);
		} else {
			throw new IllegalArgumentException(String.format("unable to cover %d x %d board", m,n));
		}
	}
	
	
	public static class Password{
		private final static int AlphabetLen ='z'-'a';
		private final String password;
		public Password(int length){
			Random random = new Random();
			char pass[]=new char[length]; 
			for (int i=0;i<length; i++){
				char b=random.nextBoolean()?'A':'a';
				pass[i]=(char)(b+(Math.abs(random.nextInt())%AlphabetLen)); 
			}
			this.password=new String(pass);
		}
		public String getPassword() {
			return password;
		}
		public boolean isPassword(String pass){
			return password.equals(pass);
		}
	}
	
	/**
	 * @param text
	 * @param index
	 * @return rotate char at index 
	 * Ex: 
	 * <ul>
	 * <li>text:'abc' with index 1 becomes 'acc'</li> 
	 * <li>text:'zaa' with index 0 becomes 'Aba' </li>
	 * <li>text:'Zaa' with index 0 becomes 'aaa' </li>
	 * </ul>
	 * 
	 */
	private static String rotateChar(String text, int index){
		char nextChar=text.charAt(index);
		if (nextChar == 'Z'){ 	// done with UPPER case
			nextChar='a';		// continue into LOWER
		} else if (nextChar=='z'){ 	// done with LOWER case
			nextChar='A';			// continue to UPPER
		} else {
			nextChar++;
		}
		// create a new string with changed char
		if (index+1<text.length()){
			return text.substring(0,index)+nextChar+text.substring(index+1);
		} else {
			return text.substring(0,index)+nextChar;			
		}
	}
	
	/**
	 * @param password
	 * @param pass
	 * @param length
	 * @param index
	 * @return recursive password search 
	 * check if pass (text) matches a given password (by length)
	 * if there is a match - return found pass (text)
	 * otherwise - rotate pass, at the given index
	 *  proceed recursively forward (if all options for index have already been tried)
	 *  otherwise rotate recursively from the beginning (for every index>0)
	 */
	private static String findPassword(Password password, String pass, int length, int index){
		//make sure generated password is in the required length
		//by adding 'A' to the password string
		if (pass.length()<length){
			return findPassword(password, pass+"A",length, 0);
		}
		
		// is that the password
		if (password.isPassword(pass)){
			return pass;
		}
		
		if (index==length){
			throw new IllegalArgumentException("index cannot be equal to len");
		}
		
		pass = rotateChar(pass, index);
		if (pass.charAt(index)=='A'){ // finished rotating  index, go next
			return findPassword(password, pass, length, index+1);
		} else { // rotate whole pass (from index 0)
			return findPassword(password, pass, length, 0);
		}
	}
	
	/**
	 * @param password
	 * @param length
	 * @return find password text for a given Password, length 
	 */
	public static String findPassword(Password password, int length){
		return findPassword(password, "", length, 0);
	}
	
}
