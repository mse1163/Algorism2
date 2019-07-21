import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution1244_2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		StringTokenizer token;
		
		for(int t=1; t<=T; t++) {
			
			token = new StringTokenizer(br.readLine());
			String str = token.nextToken();
			
			int[] arr = new int[str.length()];
			
			for(int i=0; i<str.length(); i++) {
				arr[i] = str.charAt(i)-'0';
			}
			
			int N = Integer.parseInt(token.nextToken());
			
			result = 0;
			change(arr, 0, N);
		
			bw.write("#"+t+" "+result+"\n");
			check.clear();
		}
		bw.flush();
		bw.close();

	}
	
	static int result;
	static HashSet<Integer> check = new HashSet<>();
	static void change(int[] arr, int cnt, int N) {
		int sum = 0;
		for(int i=0; i<arr.length; i++) {
			sum = sum*10 + arr[i];
		}
		
		if(!check.contains(sum+cnt)) {
			check.add(sum+cnt);
		}
		else {
			return;
		}
		
		if(cnt==N) {
			if(result<sum) {
				result = sum;
			}
			return;
		}
		
		for(int i=0; i<arr.length-1; i++) {
			for(int j=i+1; j<arr.length; j++) {
				swap(arr, i, j);
				change(arr, cnt+1, N);
				swap(arr, i, j);
			}
		}
		
	}
	
	
	static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

}
