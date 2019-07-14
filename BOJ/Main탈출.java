import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 큐 사이즈 이용하여 동시에 퍼지는거 알면 금방 풀 수 있음
// 무난무난한 문제.
public class Main3055 {
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

		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());

		map = new char[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				
				// 비버 위치
				if (map[i][j] == 'S') {
					biber.add(new Point(i, j));
				} 
				// 물 위치
				else if (map[i][j] == '*') {
					water.add(new Point(i, j));
				}
				// 동굴 위치.
				else if (map[i][j] == 'D') {
					ex = i;
					ey = j;
				}
			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}

		ans = 0;
		move();
		
		// 움직일 비버가 없어..동굴 도착 못함..
		if (ans == -1) {
			bw.write("KAKTUS");
		} 
		// 동굴 도착함.
		else {
			bw.write(ans + "");
		}
		bw.flush();
		bw.close();

	}

	static int N, M, ex, ey, ans;
	static char[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Queue<Point> biber = new LinkedList<>();
	static Queue<Point> water = new LinkedList<>();

	// 비버 움직임.
	static void move() {

		while (!biber.isEmpty()) {
			// 동시에 퍼쳐야 하므로 사이즈만큼 반복함.
			int b_size = biber.size();
			
			for (int s = 0; s < b_size; s++) {
				Point node = biber.poll();

				int bx = node.x;
				int by = node.y;

				// 비버위치가 동굴위치이면 종료
				if (bx == ex && by == ey) {
					return;
				}
				
				// 비버있는 위치가 물이 퍼져있으면 다음 비버로 넘어감.
				if (map[bx][by] == '*') {
					continue;
				}

				for (int i = 0; i < 4; i++) {
					int nx = bx + dx[i];
					int ny = by + dy[i];

					if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
						continue;
					}
					
					// 빈땅이거나 동굴이면 비버 이동
					if (map[nx][ny] == '.' || map[nx][ny] == 'D') {
						biber.add(new Point(nx, ny));
						// 비버 이동 찍음.
						map[nx][ny] = 'S';
					}
				}
			}
			
			// 아직 동굴 도착안했는데 움직일 비버 없으면 종료..
			if (biber.isEmpty()) {
				ans = -1;
				return;
			}
			
			// 물 퍼짐.
			water_move(map);
			
			// 시간 카운트
			ans++;
		}

	}

	static void water_move(char[][] map) {
		// 물이 동시에 퍼져야 하므로 크기만큼 반복
		int w_size = water.size();

		for (int s = 0; s < w_size; s++) {
			Point node = water.poll();

			int x = node.x;
			int y = node.y;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}
				
				// 빈땅이거나 비버있으면 물 퍼짐.
				if (map[nx][ny] == '.' || map[nx][ny] == 'S') {
					// 물 퍼짐 찍음
					map[nx][ny] = '*';
					water.add(new Point(nx, ny));
				}

			}
		}

	}

}
