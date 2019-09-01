import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 최단거리 찾는 문제이므로 dfs로 접근
// bfs로 하면 터짐..
public class Main파이프옮기기1 {
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

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}

		cnt = 0;
		move(0, 1, 0);
		System.out.println(cnt);
	}

	static int N, cnt;
	static int[][] map;

	static void move(int x, int y, int loc) {
		// 도착점에 오면 cnt+1 하고 끝
		if (x == N - 1 && y == N - 1) {
			cnt++;
			return;
		}
		
		// 오른쪽
		if (loc == 0) {
			right(x, y);
			deagac(x, y);
		}
		// 아래
		else if (loc == 1) {
			down(x, y);
			deagac(x, y);
		}
		// 대각선
		else if (loc == 2) {
			right(x, y);
			down(x, y);
			deagac(x, y);
		}
	}
	
	// 오른쪽이동
	private static void right(int x, int y) {
		y += 1;

		if (y >= N) {
			return;
		}

		if (map[x][y] == 0) {
			move(x, y, 0);
		}
	}
	
	// 아래 이동
	private static void down(int x, int y) {
		x += 1;

		if (x >= N) {
			return;
		}

		if (map[x][y] == 0) {
			move(x, y, 1);
		}

	}

	static int[] dx = { 0, 1, 1 };
	static int[] dy = { 1, 0, 1 };
	
	// 대각 이동
	private static void deagac(int x, int y) {
		// 오른, 아래, 대각 모두 갈 수 있어야 함.
		for (int i = 0; i < 3; i++) {

			int nx = x + dx[i];
			int ny = y + dy[i];
			
			// 범위 벗어나거나 세 방향 중 벽있으면 바로 끝.
			if (nx >= N || ny >= N || map[nx][ny] == 1) {
				return;
			}
		}

		x += 1;
		y += 1;

		move(x, y, 2);
	}

}
