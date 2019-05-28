package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main15649 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		int[]arr = new int[M+1];
		boolean[] sel = new boolean[N+1];
		
		recur(arr, sel, 1);
		
		bw.flush();
		bw.close();
	}
	
	static int N, M;
	
	static void recur(int[] arr, boolean sel[], int idx) {
		if(idx==M+1) {
			for(int i=1; i<M+1; i++) {
				System.out.print(arr[i]+" ");
				
			}
			System.out.println();
			return;
		}
		
		for(int i=1; i<N+1; i++) {
			
			if(!sel[i]) {
				sel[i]=true;
				arr[idx] = i;
				recur(arr, sel, idx+1);
				sel[i] = false;
			}
			
		}
	}

}
