package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
// 아기상어 
// 시간 터질줄 알았지만 터지지 않앗음...ㄷㄷ
// 초기화를 잘해주자...그 비교하는 초기값 설정을 아무생각없이 0으로 해놔서
// 계속 값이 큐에 들어와...터졌음..
// 최소값을 찾아야되므로 최대인 값으로 초기화 해줘야함!!
public class Main16236 {
	static class Point {
		int x, y, dist;

		public Point(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;

		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", dist=" + dist + "]";
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
				if (map[i][j] == 9) {
					q.add(new Point(i, j, 0));
					
					map[i][j] = 0;
				}
			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		bfs(map);
		System.out.println(ans);

	}

	static int N, size = 2;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Queue<Point> q = new LinkedList<>();
	static boolean[][] sel;
	static List<Point> list;
	static void bfs(int[][] map) {

		list = new ArrayList<>();
		sel = new boolean[N][N];
		
		sel[q.peek().x][q.peek().y] = true;
		while (!q.isEmpty()) {

			Point node = q.poll();

			int x = node.x;
			int y = node.y;
			int dist = node.dist;

			for (int i = 0; i < 4; i++) {

				int nx = x + dx[i];
				int ny = y + dy[i];

				// 나보다 큰애, 이미 방문햇던곳 , 범위벗어나면...
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] > size || sel[nx][ny]) {
					continue;
				}
				// 지나갈수 있음
				if (!sel[nx][ny] && (map[nx][ny] == size || map[nx][ny]==0)) {

					sel[nx][ny] = true;
					q.add(new Point(nx, ny, dist + 1));
				}
				// 먹을수 있음
				else if (!sel[nx][ny] && map[nx][ny] != 0 && map[nx][ny] < size) {
					sel[nx][ny] = true;
					list.add(new Point(nx, ny, dist+1));

				}
			}

		}
		// 먹을게 있으면 반복 없으면 끝
		if(list.size()!=0) {
			eat(list, map);
			bfs(map);
		}

	}

	static int ans = 0, eat = 0;
	// 물고기 먹기
	static void eat(List<Point> list, int[][] map) {

		// 작은 값 구해야 하므로 최대로 값을 넣어줌
		int nx = N+1, ny = N+1, nd = 987654321;
		for (int i = 0; i < list.size(); i++) {
			int x = list.get(i).x;
			int y = list.get(i).y;
			int dist = list.get(i).dist;

			if (nd == dist) {
				if (nx > x) {
					nx = x;
					ny = y;
					nd = dist;
				} else if (nx == x) {
					if (ny > y) {
						nx = x;
						ny = y;
						nd = dist;
					}
				}
			} else if (nd > dist) {
				nx = x;
				ny = y;
				nd = dist;
			}

		}

		eat++;
		// 먹은 물고기수랑 몸 크기랑 같으면...
		if (eat == size) {
			size++;
			eat = 0;
		}

		// 먹을 곳에서 다시 시작.
		map[nx][ny] = 0;
		q.clear();
		q.add(new Point(nx, ny, nd));
		ans = nd;

	}

}
