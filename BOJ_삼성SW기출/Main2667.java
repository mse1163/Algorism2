package baekjun;

import java.util.Arrays;
import java.util.Scanner;
// 단지번호 붙이기 
public class Main2667 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		int[][] map = new int[N][N];
		int[] num = new int[N * N];
		for (int i = 0; i < N; i++) {
			String str = sc.next();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';

			}
		}

		int cnt = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					
					cnt++;
					// 단지 시작점을 찍고 가야함 안해주면 틀림!!
					num[cnt]=1;
					map[i][j]=cnt;
					dfs(map, num, i, j, cnt);

				}
			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}

		Arrays.sort(num);
		
		System.out.println(cnt - 1);
		
		for (int i = 0; i < num.length; i++) {
			if (num[i] != 0) {
				System.out.println(num[i]);
			}
		}
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int N;

	static void dfs(int[][] map, int[] num, int x, int y, int cnt) {

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
				continue;
			}

			if (map[nx][ny] == 1) {
				num[cnt]++;
				map[nx][ny] = cnt;
				dfs(map, num, nx, ny, cnt);

			} 

		}

	}

}
