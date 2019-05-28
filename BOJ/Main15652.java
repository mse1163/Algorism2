package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main15652 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		//int[] arr = new int[N+1];
		int[] sel = new int[M+1];
		
		recur(sel, 1,1);
		
		bw.flush();
		bw.close();
	}
	
	static int N, M;
	
	static void recur(int[] sel, int idx,int cnt) {
		if(idx==M+1) {
			for(int i=1; i<M+1; i++) {
				System.out.print(sel[i]+" ");
			}
			System.out.println();
			return;
		}
		
		for(int i=cnt; i<N+1; i++) {
			sel[idx] = i;
			recur(sel, idx+1, i);
			
		}
	}
}
