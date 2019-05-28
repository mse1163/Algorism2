import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main14503 {
	static class Point {
		int x, y, loc;

		public Point(int x, int y, int loc) {
			super();
			this.x = x;
			this.y = y;
			this.loc = loc;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", loc=" + loc + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());

		token = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(token.nextToken());
		int y = Integer.parseInt(token.nextToken());
		int loc = Integer.parseInt(token.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}

		visited = new boolean[N][M];
		q.add(new Point(x, y, loc));
		visited[x][y] = true;
		clean = 1;

		bfs();
		System.out.println(clean);

	}

	static int N, M, clean;
	static int[][] map;
	static boolean[][] visited;
	static Queue<Point> q = new LinkedList<>();
	static int[] dx = { -1, 0, 1, 0 }; // 북 동 남 서
	static int[] dy = { 0, 1, 0, -1 };

	static int[] back_x = { 1, 0, -1, 0 }; // 후진 이동
	static int[] back_y = { 0, -1, 0, 1 };

	static void bfs() {
		int cnt = 0;
		while (!q.isEmpty()) {
			Point node = q.poll();
			int x = node.x;
			int y = node.y;
			int loc = node.loc;

			int new_loc = location(loc);
			int nx = x + dx[new_loc];
			int ny = y + dy[new_loc];

			if (cnt < 4) {
				if (map[nx][ny] == 0 && !visited[nx][ny]) {
					cnt = 0;
					visited[nx][ny] = true;
					clean++;
					q.add(new Point(nx, ny, new_loc));
				} else if ((map[nx][ny] == 0 && visited[nx][ny]) || map[nx][ny] == 1) {
					q.add(new Point(x, y, new_loc));
					cnt++;
				}
			} else if (cnt == 4) {
				cnt=0;
				int bx = x + back_x[loc];
				int by = y + back_y[loc];

				if (map[bx][by] == 1) {
					return;
				}

				else {
					q.add(new Point(bx, by, loc));
				}
			}

		}
	}

	static int location(int loc) {
		if (loc == 0) {
			loc = 3;
		} else if (loc == 1) {
			loc = 0;
		} else if (loc == 2) {
			loc = 1;
		} else if (loc == 3) {
			loc = 2;
		}

		return loc;
	}
}
