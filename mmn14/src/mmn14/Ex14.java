package mmn14;

public class Ex14 {

	static int sumDevidors(int a){
		int sum=0;
		for (int i=1; i<(a/2+1);i++){
			if (a % i == 0){
				sum +=i;
			}
		}
		return sum;
	}
	static boolean areFriends(int a, int b){
		return sumDevidors(a) == b 
				&& sumDevidors(b)==a;
	}
	
	public static int countFriends(int n){
		int countArr[]=new int[n+1];
		for (int i=1;i<n+1;i++){
			countArr[i]=sumDevidors(i);
		}
		int count=0;
		for (int i=1; i<n+1; i++){
			for (int j=0;j<n+1;j++){
				if (i!=j && countArr[i]== j && countArr[j]==i){
					count++;
				}
			}
		}
		return count/2;
	}
	
	// 4[0 2 4 8]: 1
	public static int count (int a[], int x){
		int len=a.length;
		int index=len/2, delta=index/2, log=index>0?1:0, count=0;
		for (;delta>0; delta/=2, log++){
			if (x>a[index]){
				index+=delta;
			} else if (x<a[index]){
				index-=delta;
			} else {
				break;
			}
		}
		if (a[index]<x){
			for (int limit=index+log;index<limit && index<len && a[index]!=x; index++);
		} else if (a[index]>x){
			for (int limit=index-log;index>limit && index>0 && a[index]!=x; index--);
		}
		for (int i=index;i<len && a[i]==x; i++){
			count++;
		}
		for (int i=index-1;i>-1 && a[i]==x; i--){
			count++;
		}
		return count;
	}
	
}
