package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
//RGB 거리
public class Main1149 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N+1][3];
		
		for(int i=1; i<N+1; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		
//		for(int i=0; i<N+1; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		int[][] dp = new int[N+1][3];
		
		for(int i=1; i<N+1; i++) {
			// 빨강선택시
			dp[i][0] = map[i][0]+Math.min(dp[i-1][1], dp[i-1][2]);
			// 초록 선택시
			dp[i][1] = map[i][1]+Math.min(dp[i-1][0], dp[i-1][2]);
			// 파랑 선택시
			dp[i][2] = map[i][2]+Math.min(dp[i-1][0], dp[i-1][1]);
			
		}
		
		for(int i=0; i<N+1; i++) {
		for(int j=0; j<N; j++) {
			System.out.print(dp[i][j]+" ");
		}
		System.out.println();
	}
		
		//System.out.println(dp[N][0]+" "+dp[N][1]+" "+dp[N][2]);
		// 세가지색 선택시 나온 각각의 마지막수 중에서 최솟값 선택
		int result = Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2]));
		System.out.println(result);
	}

}
