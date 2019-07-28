import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 방문체크 잘해주면 풀 수 있음..

public class Main벽부수고이동하기 {
	static class Point {
		int x, y, cnt;
		boolean broken;

		public Point( int x, int y, int cnt, boolean broken) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.broken = broken;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());

		int[][] map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		ans = 987654321;
		q.add(new Point(0, 0, 1, false));
		move(map);

		if (ans == 987654321) {
			ans = -1;
		}

		System.out.println(ans);
	}

	static int N, M, ans;
	static Queue<Point> q = new LinkedList<>();

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static void move(int[][] map) {
		// 벽부순경우 방문체크
		boolean[][] broken_wall = new boolean[N][M];
		
		// 벽 안부순경우 방문체크
		boolean[][] nobroken_wall = new boolean[N][M];
		
		while (!q.isEmpty()) {

			Point node = q.poll();
			
			int x = node.x;
			int y = node.y;
			int cnt = node.cnt;
			boolean broken = node.broken;
			
			if (x == N - 1 && y == M - 1) {
				ans = cnt;
				return;
			}

			for (int i = 0; i < 4; i++) {
				
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}

				// 벽안부숨
				if (!broken) {
					// 벽안부순 배열 방문체크
					if (map[nx][ny] == 0 && !nobroken_wall[nx][ny]) {
						nobroken_wall[nx][ny] = true;
						q.add(new Point(nx, ny, cnt + 1, broken));
					}
					// 벽 부순 배열 방문체크
					else if(map[nx][ny]==1 && !broken_wall[nx][ny]) {
						broken_wall[nx][ny] = true;
						q.add(new Point(nx, ny, cnt+1, true));
					}
				}
				// 벽 부숨
				else {
					if (map[nx][ny] == 0 && !broken_wall[nx][ny]) {
						broken_wall[nx][ny] = true;
						q.add(new Point(nx, ny, cnt + 1, broken));
					}
					
				}
			}
		}
	}
}
