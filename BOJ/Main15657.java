package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main15657 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());

		arr = new int[N];
		token = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(token.nextToken());
		}
		
		result = new int[M];
		Arrays.sort(arr);

		recur(0,0);

		bw.flush();
		bw.close();

	}
	
	static int N, M;
	static int[] arr, result;
	
	static void recur(int idx,int cnt) {
		if(idx==M) {
			for (int i = 0; i < M; i++) {			
				System.out.print(result[i] + " ");			
			}
			System.out.println();
			return;
		}
				
		for(int i=cnt; i<N; i++) {		
			result[idx] = arr[i];
			recur(idx+1,i);			
		}
	}
}
