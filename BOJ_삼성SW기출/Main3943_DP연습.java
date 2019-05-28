package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
//정수 삼각형
public class Main3943 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		int[][] map = new int[N + 1][N + 1];

		for (int i = 1; i < N + 1; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			for (int j = 1; j < i + 1; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < i+1; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}

		int[][] dp = new int[N + 1][N + 1];
		dp[0][0] = map[0][0];
		
		// 본인 위치보다 바로 위에 값이랑 위에 옆값 중에 큰수를 저장함.
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < i + 1; j++) {

				dp[i][j] = map[i][j] + Math.max(dp[i - 1][j - 1], dp[i - 1][j]);

			}
		}

		int max = Integer.MIN_VALUE;
		// 마지막줄에 제일 큰거 확인하여 뽑아냄
		for (int i = 1; i < N + 1; i++) {
			max = Math.max(dp[N][i], max);
		}
		
		sb.append(max);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
