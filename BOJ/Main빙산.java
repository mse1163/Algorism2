import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 빙산을 주변 바다갯수 모두 확인한 후 한번에 빙산을 녹여줘야함.
// 안그러면 먼저 빙산이 다 녹아서 바다로 변한것도 함께 세짐..

public class Main빙산 {
	static class Point {
		int x, y, height;

		public Point(int x, int y, int height) {
			super();
			this.x = x;
			this.y = y;
			this.height = height;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", height=" + height + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer token = new StringTokenizer(br.readLine());

		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());

		int[][] map = new int[N][M];

		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
				
				// 빙산이면 큐에 저장
				if (map[i][j] != 0) {
					q.add(new Point(i, j, map[i][j]));
				}
			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//
//		System.out.println(q.toString());

		ans = 0;
		bfs(map);
		System.out.println(ans);
	}

	static int N, M, ans;
	static Queue<Point> q = new LinkedList<>();
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	
	static void bfs(int[][] map) {
		int cnt = 0;
		while (!q.isEmpty()) {
			// 빙산 녹은거 저장할 리스트.
			ArrayList<Point> sel = new ArrayList<>();
			// 빙산 덩어리 2개 이상인지 확인.
			isok = false;
			// 덩어리 세는 함수.
			block(map);
			
			// 만약 덩어리가 2개 이상이면 바로 끝
			if (isok) {
				return;
			}
			
			int size = q.size();
			
			// 시간 카운트.
			ans++;
			for (int k = 0; k < size; k++) {

				Point node = q.poll();

				int x = node.x;
				int y = node.y;
				int h = node.height;
				
				// 빙산 주변 바닷물 세기.
				cnt = 0;
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					// 범위 확인
					if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
						continue;
					}
					
					// 주변이 바다이면 cnt++
					if (map[nx][ny] == 0) {
						cnt++;

					}
				}
				// 모든 맵 돌고 한번에 녹여야하므로 sel에 저장해놓음.
				// 빙산높이에서 접한 바닷물 갯수 빼고 저장.
					sel.add(new Point(x, y, h - cnt));
			}
			// 혹시 모르니 큐 비워줌.
			q.clear();
			
			// 녹은 빙산 저장한거 빼서 완전히 녹은건지 확인. 
			for (int i = 0; i < sel.size(); i++) {
				int sh = sel.get(i).height;
				int sx = sel.get(i).x;
				int sy = sel.get(i).y;
				
				// 빙산 높이가 0보다 큰것만 다시 큐에 넣어줌.
				if (sh > 0) {

					q.add(sel.get(i));
					// 맵에 빙산 높이 녹인거 저장
					map[sx][sy] = sh;
				}
				// 만약 빙산이 다 녹았으면 맵에 0으로 표시.
				else {
					map[sx][sy]=0;
				}
			}
		}

		// 큐에 비었는데도 다 못녹았으면 0출력.
		ans = 0;

	}

	static boolean[][] sel;
	static boolean isok;
	
	// 덩어리 세기
	static void block(int[][] map) {
		sel = new boolean[N][M];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 빙산이고, 아직 방문 안함.
				if (map[i][j] != 0 && !sel[i][j]) {
					// 덩어리 갯수 셈.
					cnt++;
					dfs(map, i, j);
				}
				
				// 덩어리가 2개이면 끝
				if (cnt == 2) {
					isok = true;
					return;
				}
			}
		}
	}
	
	// 빙산 덩어리 세기.
	private static void dfs(int[][] map, int x, int y) {

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
				continue;
			}
			
			// 빙산이면서 방문안함.
			if (map[nx][ny] != 0 && !sel[nx][ny]) {
				// 방문체크.
				sel[nx][ny] = true;
				dfs(map, nx, ny);
			}
		}

	}
}
