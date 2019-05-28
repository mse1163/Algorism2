package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
// 섬의개수
// dfs나 bfs로 돌려서 찾아가면됨
// 유기농배추인가 이 문제랑 같은데 대각선이 추가됨.
public class Main4963 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		while (true) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			M = Integer.parseInt(token.nextToken());
			N = Integer.parseInt(token.nextToken());

			if (M == 0 && N == 0) {
				break;
			}

			int[][] map = new int[N][M];

			for (int i = 0; i < N; i++) {
				token = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
				}
			}

			int cnt = 1;
			boolean[][] sel = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1 && !sel[i][j]) {
						cnt++;
						sel[i][j] = true;
						
						move(map, sel, i, j, cnt);
					}
				}
			}

//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < M; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}

			System.out.println(cnt - 1);

		}

	}

	static int N, M;
	static int[] dx = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dy = { 0, 0, -1, 1, -1, 1, -1, 1 };

	static void move(int[][] map, boolean[][] sel, int x, int y, int cnt) {

		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
				continue;
			}

			if (map[nx][ny] == 1 && !sel[nx][ny]) {
				sel[nx][ny] = true;
			
				move(map, sel, nx, ny, cnt);
			}

		}

	}

}
