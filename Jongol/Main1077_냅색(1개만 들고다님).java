package Jungol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
// 냅색 1개씩만 들고다닐수 잇음
public class Main1077 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer token = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(token.nextToken());
		int W = Integer.parseInt(token.nextToken());

        // 맨위에 0으로 한줄 만들어 놓으면 계산할때 편함
		int[][] map = new int[N+1][2];
        // 총 무게 보다 +1 해줘야 아래서 for문 돌릴때 맞춰서 돌릴수 잇음 ( 위에 N+1로 해줬기 때문에)
		int[][] dp = new int[N + 1][W+1];

		for (int i = 1; i < N+1; i++) {
			token = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(token.nextToken());
			map[i][1] = Integer.parseInt(token.nextToken());
		}

//		Arrays.sort(map, new Comparator<int[]>() {
//
//			@Override
//			public int compare(int[] o1, int[] o2) {
//				// TODO Auto-generated method stub
//				return Integer.compare(o1[0], o2[0]);
//			}
//		});

//		for (int i = 0; i < N; i++) {
//
//			System.out.println(Arrays.toString(map[i]));
//		}

		for (int i = 1; i < N+1; i++) {
			for (int j = 0; j < W+1; j++) {
				if (j < map[i][0])
					dp[i][j] = dp[i - 1][j];
				else {
					// 위에 자신의 무게전 값 + 현재 자신, 현재자신 바로 위 값 비교
					dp[i][j] = Math.max(dp[i - 1][j], dp[i-1][j-map[i][0]] + map[i][1]);
				}

			}
		}

//		for (int i = 0; i < N+1; i++) {
//
//			System.out.println(Arrays.toString(dp[i]));
//		}
		
		sb.append(dp[N][W]);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
