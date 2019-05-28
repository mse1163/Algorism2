import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 탈주범 검거 -> 큐 클리어 해주는게 핵심!!
public class Solution1953 {
	static class Point {
		int x, y, cnt, num;

		public Point(int x, int y, int cnt, int num) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.num = num;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", cnt=" + cnt + ", num=" + num + "]";
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= T; t++) {

			StringTokenizer token = new StringTokenizer(br.readLine());

			N = Integer.parseInt(token.nextToken());
			M = Integer.parseInt(token.nextToken());

			int x = Integer.parseInt(token.nextToken());
			int y = Integer.parseInt(token.nextToken());

			L = Integer.parseInt(token.nextToken());

			map = new int[N][M];
			sel = new boolean[N][M];

			for (int i = 0; i < N; i++) {
				token = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
				}
			}

			q.add(new Point(x, y, 1, map[x][y]));

//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < M; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
			ans = 1;
			sel[x][y] = true;
			bfs();

            // 큐가 다 비어있지 않아도 끝날수 있으므로 반드시 클리어 해줘야함!!!!!
			q.clear();

			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

	static int N, M, L, ans;
	static int[][] map;
	static Queue<Point> q = new LinkedList<>();
	static boolean[][] sel;

	static void bfs() {

		while (!q.isEmpty()) {

			Point node = q.poll();

			int x = node.x;
			int y = node.y;
			int cnt = node.cnt;	
			int num = node.num;

			// 시간이 L만큼 되면 끝
			if (cnt == L) {
				return;
			}
			
			// 맨홀이 1일경우..상하좌우
			if (num == 1) {
				up(x, y, cnt);
				down(x, y, cnt);
				left(x, y, cnt);
				right(x, y, cnt);

			} 
			// 맨홀 2인경우.. 상하
			else if (num == 2) {
				up(x, y, cnt);
				down(x, y, cnt);
				
			} 
			// 맨홀 3인경우.. 좌우
			else if (num == 3) {
				left(x, y, cnt);
				right(x, y, cnt);
			}
			// 맨홀이 4인경우..상 우
			else if (num == 4) {
				up(x, y, cnt);
				right(x, y, cnt);
			}
			// 맨홀 5인경우..하 우
			else if (num == 5) {
				down(x, y, cnt);
				right(x, y, cnt);
			} 
			// 맨홀 6인경우.. 하 좌
			else if (num == 6) {
				down(x, y, cnt);
				left(x, y, cnt);
			} 
			// 맨홀 7인경우.. 상 좌
			else if (num == 7) {
				up(x, y, cnt);
				left(x, y, cnt);
			}
		}

	}

	// 위로 갈 경우
	static void up(int x, int y, int cnt) {
		x = x - 1;

		if (x < 0 || y < 0 || x >= N || y >= M) {
			return;
		}
		
		// 위로 가는 방향은 1,2,5,6 맨홀만 갈 수 있음
		if (!sel[x][y] && (map[x][y] == 1 || map[x][y] == 2 || map[x][y] == 5 || map[x][y] == 6)) {
			sel[x][y] = true;
			ans++;
			q.add(new Point(x, y, cnt + 1, map[x][y]));
		}

	}

	// 아래로 갈 경우
	static void down(int x, int y, int cnt) {
		x = x + 1;

		if (x < 0 || y < 0 || x >= N || y >= M) {
			return;
		}
		
		// 아래방향은 1,2,4,7 맨홀만 갈 수 있음
		if (!sel[x][y] && (map[x][y] == 1 || map[x][y] == 2 || map[x][y] == 4 || map[x][y] == 7)) {
			sel[x][y] = true;
			ans++;
			q.add(new Point(x, y, cnt + 1, map[x][y]));
		}

	}

	// 왼쪽으로 갈 경우
	static void left(int x, int y, int cnt) {
		y = y - 1;

		if (x < 0 || y < 0 || x >= N || y >= M) {
			return;
		}

		// 왼쪽 방향은 1,3,4,5 맨홀만 갈 수 있음
		if (!sel[x][y] && (map[x][y] == 1 || map[x][y] == 3 || map[x][y] == 4 || map[x][y] == 5)) {
			sel[x][y] = true;
			ans++;
			q.add(new Point(x, y, cnt + 1, map[x][y]));
		}

	}

	// 오른쪽으로 갈 경우
	static void right(int x, int y, int cnt) {
		y = y + 1;

		if (x < 0 || y < 0 || x >= N || y >= M) {
			return;
		}
		// 오른쪽 방향은 1,3,6,7 맨홀만 갈 수 잇음
		if (!sel[x][y] && (map[x][y] == 1 || map[x][y] == 3 || map[x][y] == 6 || map[x][y] == 7)) {
			sel[x][y] = true;
			ans++;
			q.add(new Point(x, y, cnt + 1, map[x][y]));
		}

	}

}
