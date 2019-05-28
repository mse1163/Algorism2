package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

// 움직이는 미로 탈출
public class Main {
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
		StringBuilder sb = new StringBuilder();

		char[][] map = new char[8][8];

		for (int i = 0; i < 8; i++) {
			String str = br.readLine();
			for (int j = 0; j < 8; j++) {
				map[i][j] = str.charAt(j);

			}
		}

		// 입력확인
//		for (int i = 0; i < 8; i++) {
//			for (int j = 0; j < 8; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}

		q_me.add(new Point(7, 0));
		ans = 0;
		move_me(map);

		System.out.println(ans);

	}

	static int ans;
	static Queue<Point> q_me = new LinkedList<>();
	static int[] dx = { 0, -1, 1, 0, 0, -1, -1, 1, 1 }; // 현재,상하좌우,대각
	static int[] dy = { 0, 0, 0, -1, 1, -1, 1, -1, 1 };

	static void move_me(char[][] map) {
		while (!q_me.isEmpty()) {
			int size = q_me.size();
			// 중복되서 위치 들어가는거 
			boolean[][] sel= new boolean[8][8];
			
			
			for (int s = 0; s < size; s++) {
				Point node = q_me.poll();

				int x = node.x;
				int y = node.y;
				
				// 벽이랑 부딪쳐서 죽음
				if (map[x][y] == '#') {
					continue;
				}
				
				// 위치 도착하면 종료
				if (x == 0 && y == 7) {
					ans = 1;
					return;
				}
				
				for (int i = 0; i < 9; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];

					if (nx < 0 || ny < 0 || nx >= 8 || ny >= 8) {
						continue;
					}

					if (map[nx][ny] == '#') {
						continue;
					}
					
					// 중복해서 들어가는 위치를 제거해줘야 시간초과 안남 -> 9방향이여서 큐에 너무많이 들어감.
					if (map[nx][ny] == '.' && !sel[nx][ny]) {
						sel[nx][ny]=true;
						q_me.add(new Point(nx, ny));
					}
				}
			}
			// 큐가 비어있으면 더이상 갈곳 없으니까 끝
			if (q_me.size() == 0) {
				ans = 0;
				return;
			}

			map = move_wall(map);
		}
	}
	
	// 벽 아래로 내림
	static char[][] move_wall(char[][] map) {
		for (int i = 7; i > 0; i--) {
			for (int j = 0; j < 8; j++) {
				map[i][j] = map[i - 1][j];
			}
		}
		for (int i = 0; i < 8; i++) {
			map[0][i] = '.';
		}

		return map;
	}
}
