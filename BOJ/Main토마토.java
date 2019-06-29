import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// bfs문제 
// 동시에 토마토가 익어야 하므로 size만큼 반복돌아야함.
public class Main7576 {
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

		StringTokenizer token = new StringTokenizer(br.readLine());

		M = Integer.parseInt(token.nextToken());
		N = Integer.parseInt(token.nextToken());

		map = new int[N][M];
		
		// 아직 안익은 토마토
		tomato = 0;
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
				if (map[i][j] == 0) {
					// 안익은 토마토 갯수
					tomato++;
					
				} else if (map[i][j] == 1) {
					q.add(new Point(i, j));
				}
				
			}

		}
		
		// 안익은 토마토 없으면 0 출력
		if (tomato==0) {
			System.out.println(0);
		} else {
			day=0;
			bfs();
			// 안익은 토마토가 없으면 즉, 토마토가 다 익었으면 며칠인지 출력
			if(tomato==0) {
				System.out.println(day);
			}
			// 안익은 토마토 있는데 끝났으면 -1 출력
			else {
				System.out.println(-1);
			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}

	}

	static int N, M, tomato,day;
	static int[][] map;
	static Queue<Point> q = new LinkedList<>();
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static void bfs() {

		while (!q.isEmpty()) {
			// 익은 토마토 모두 동시에 움직여야 하므로 size만큼 반복
			int size = q.size();

			for (int s = 0; s < size; s++) {

				Point node = q.poll();

				int x = node.x;
				int y = node.y;
				
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];

					if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
						continue;
					}

					// 안익은 토마토면 익혀줌.
					if (map[nx][ny] == 0) {
						map[nx][ny] = 2;
						// 토마토 익었으므로 안익은 토마토 -1 해줌.
						tomato--;
						// 익힌 토마토 위치 저장
						q.add(new Point(nx, ny));
					}
				}
			}
			
			// 만약 안익은 토마토가 없으면 날짜 +1해주고 끝냄.
			// 이 조건 없으면 토마토가 다 익었는데도 queue가 비어있을때 까지 계속 반복함.
			if(tomato==0) {
				day++;
				return;
			}
			// 안익은 토마토 있으면 계속 반복.
			else {
				day++;
			}
		}
	}
}
