import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 설명이 애매모호하게 나와있어서 이해하는데 시간이 좀 걸렸음.
// 벽일경우 바로 후진하는게 아니라 방향만 바꿈.
// 모든 4방향을 다 확인했는데 청소할 곳이 없으면 후진.
public class Main로봇청소기 {
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

		StringTokenizer token = new StringTokenizer(br.readLine());

		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());

		token = new StringTokenizer(br.readLine());
		q.add(new Point(Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()),
				Integer.parseInt(token.nextToken())));

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}

		ans = 0;
		move();
		q.clear();
		System.out.println(ans);
	}

	static int N, M, ans;
	static int[][] map;
	static Queue<Point> q = new LinkedList<>();
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	
	// 청소기 이동.
	static void move() {
		// 청소여부 체크
		int[][] sel = new int[N][M];
		// 방향 돌려서 확인해본 수
		int cnt = 0;
		
		while (!q.isEmpty()) {
			Point node = q.poll();

			int x = node.x;
			int y = node.y;
			int loc = node.loc;
			
			// 청소안한 곳이면 청소.
			if (sel[x][y] == 0) {
				sel[x][y] = 1;
				ans++;
			}
			
			// 아직 4방향 다 확인 안했음.
			if (cnt < 4) {
				// 왼쪽으로 방향바꿈.
				int new_loc = change_loc(loc);
				
				int nx = x + dx[new_loc];
				int ny = y + dy[new_loc];
				
				// 왼쪽방향이 청소안햇으면 방향바꾸고 이동.
				if (map[nx][ny] == 0 && sel[nx][ny] == 0) {
					// 청소했으므로 cnt=0으로 초기화.
					cnt = 0;
					q.add(new Point(nx, ny, new_loc));
				} 
				// 왼쪽방향 청소햇음 or 벽
				else if ((map[nx][ny] == 0 && sel[nx][ny] == 1) || map[nx][ny] == 1) {
					// 위치는 그대로 방향만 바꿈.
					q.add(new Point(x, y, new_loc));
					// 방향바꿨으니 cnt++
					cnt++;
				}
				
			} 
			// 4방향 모두 확인.
			else if (cnt == 4) {
				// 모두 확인 햇으므로 다시 cnt 초기화.
				cnt = 0;
				// 후진한 위치
				int bx = x + dx[back_loc(loc)];
				int by = y + dy[back_loc(loc)];
				
				// 후진할 수 있으면 방향그대로 후진.
				if (map[bx][by] == 0) {
					q.add(new Point(bx, by, loc));
				} 
				// 후진 할 수 없으면 -> 뒤에가 벽이면 끝.
				else {
					return;
				}
			}
		}
	}
	
	// 왼쪽으로 방향 바꿈.
	static int change_loc(int loc) {
		if (loc == 0) {
			return 3;
		} else if (loc == 1) {
			return 0;
		} else if (loc == 2) {
			return 1;
		} else if (loc == 3) {
			return 2;
		}

		return 0;
	}
	
	// 후진할 방향.
	static int back_loc(int loc) {
		if (loc == 0) {
			return 2;
		} else if (loc == 1) {
			return 3;
		} else if (loc == 2) {
			return 0;
		} else if (loc == 3) {
			return 1;
		}

		return 0;
	}
}
