package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 비버 탈출 문제
public class Main3055 {
	static class Point {
		int x, y, cnt;

		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "[" + x + "," + y + "," + cnt + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());

		map = new char[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);

				if (map[i][j] == 'S') {
					q.add(new Point(i, j, 0));
				} else if (map[i][j] == '*') {
					water.add(new Point(i, j, 0));
				}
			}
		}
//
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		ans = -1;
		result = "";

		bfs();

		if (result.equals("")) {
			System.out.println(ans);
		}
		else {
			System.out.println(result);
		}
	}

	static int N, M, ans;
	static char[][] map;
	static String result;
	static Queue<Point> q = new LinkedList<>();
	static Queue<Point> water = new LinkedList<>();
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static void bfs() {

		while (true) {

			water_move();
			int move = biber_move();
			
			// 도착함
			if (move > -1) {
				break;
			} 
			// 도착못하고 끝남
			else if (move == -2) {
				result = "KAKTUS";
				return;
			}
		}

	}

	static void water_move() {

		int size = water.size();
		while (size != 0) {
			Point node = water.poll();

			int x = node.x;
			int y = node.y;
			int cnt = node.cnt;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}

				if (map[nx][ny] == '.') {
					map[nx][ny] = '*';
					water.add(new Point(nx, ny, cnt + 1));
				}

				else if (map[nx][ny] == 'X' || map[nx][ny] == 'D' || map[nx][ny] == '2') {
					continue;
				}

			}

			size--;
		}

	}

	static int biber_move() {
		int size = q.size();
		while (size != 0) {
			Point node = q.poll();

			int x = node.x;
			int y = node.y;
			int cnt = node.cnt;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}

				if (map[nx][ny] == '.') {

					// 비버 이동한거 체크
					map[nx][ny] = '2';
					q.add(new Point(nx, ny, cnt + 1));

				} 
				else if (map[nx][ny] == 'X' || map[nx][ny] == '*' || map[nx][ny] == '2') {
					continue;
				} 
				else if (map[nx][ny] == 'D') {
					ans = cnt + 1;

				}
			}
			size--;
		}
		
		// 큐가 비었는데 도착을 못한 경우
		if (q.isEmpty() && ans == -1) {
			ans = -2;
		}
		return ans;
	}

}
