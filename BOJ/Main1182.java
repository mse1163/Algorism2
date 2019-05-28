import java.util.Arrays;
import java.util.Scanner;

public class Main{

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		s = sc.nextInt();
		
		int[] num = new int[N];
		boolean[] sel = new boolean[N];
		for(int i=0; i<N; i++) {
			num[i]=sc.nextInt();
		}
		
		sum(num, sel, 0);
		if(s==0) {
			count = count-1;
		}
		System.out.println(count);
	}
	
	static int N , s;
	static int count=0;
	static void sum(int[] num, boolean[] sel, int cnt) {
		int sum=0;
		if(cnt==N) {
			
			for(int i=0; i<N; i++) {
				if(sel[i]==true) {
					sum+=num[i];
				}
			}
				if(sum==s) {
					count++;	
				}			
			return;
		}
		
		sel[cnt]=true;
		sum(num, sel, cnt+1);
		sel[cnt] = false;
		sum(num, sel, cnt+1);
	}
}