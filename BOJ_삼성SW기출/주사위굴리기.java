package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 주사위굴리기
public class Main14499 {
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		StringTokenizer token = new StringTokenizer(br.readLine());

		N = Integer.parseInt(token.nextToken()); // 세로
		M = Integer.parseInt(token.nextToken()); // 가로

		int x = Integer.parseInt(token.nextToken()); // 시작점
		int y = Integer.parseInt(token.nextToken());

		q.add(new Point(x, y));

		K = Integer.parseInt(token.nextToken()); // 명령어 갯수

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}

		token = new StringTokenizer(br.readLine());
		die = new int[7];
		for (int i = 0; i < K; i++) {
			// 명령어
			int text = Integer.parseInt(token.nextToken());

			Point node = q.poll();
			x = node.x;
			y = node.y;

			move(x, y, text);
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static int N, M, K;
	static StringBuilder sb;
	static int[][] map;
	static int[] die;
	static int[] dx = { 0, 0, 0, -1, 1 };	// 1번부터 동서북남
	static int[] dy = { 0, 1, -1, 0, 0 };

	static Queue<Point> q = new LinkedList<>();

	static void move(int x, int y, int text) {

		int nx = x + dx[text];
		int ny = y + dy[text];

		// 범위 벗어나면 출력없이 그냥 지나감.
		if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
			q.add(new Point(x, y));
			return;
		}

		// 입력받은대로 먼저 주사위 돌려놓음
		if (text == 1) {
			move_right();
		} 
		else if (text == 2) {
			move_left();
		} 
		else if (text == 3) {
			move_up();
		} 
		else if (text == 4) {
			move_down();
		}

		// 돌려놓고 나서 값을 넣어줌
		if (map[nx][ny] == 0) {
			map[nx][ny] = die[6];
			sb.append(die[1]).append("\n");
			q.add(new Point(nx, ny));
			
		} else {
			die[6] = map[nx][ny];
			map[nx][ny] = 0;
			sb.append(die[1]).append("\n");
			q.add(new Point(nx, ny));
		}

	}

	// 오른쪽
	static void move_right() {
		int tmp = die[1];
		die[1] = die[4];
		die[4] = die[6];
		die[6] = die[3];
		die[3] = tmp;
	}

	// 왼쪽
	static void move_left() {
		int tmp = die[6];
		die[6] = die[4];
		die[4] = die[1];
		die[1] = die[3];
		die[3] = tmp;
	}

	// 위
	static void move_up() {
		int tmp = die[2];
		die[2] = die[1];
		die[1] = die[5];
		die[5] = die[6];
		die[6] = tmp;
	}

	// 아래
	static void move_down() {
		int tmp = die[5];
		die[5] = die[1];
		die[1] = die[2];
		die[2] = die[6];
		die[6] = tmp;
	}
}
