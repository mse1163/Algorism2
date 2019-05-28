package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 로봇청소기
public class Main14503 {
	static class Point {
		int x, y, loc;

		public Point(int x, int y, int loc) {
			this.x = x;
			this.y = y;
			this.loc = loc;
		}

		@Override
		public String toString() {
			return "[" + x + ", " + y + ", " + loc + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken()); // 세로의 크기
		M = Integer.parseInt(token.nextToken()); // 가로
		map = new int[N][M];

		token = new StringTokenizer(br.readLine());

		int x = Integer.parseInt(token.nextToken());
		int y = Integer.parseInt(token.nextToken());
		int loc = Integer.parseInt(token.nextToken());

		q.add(new Point(x, y, loc));

		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		//test = new int[N][M];
		visited = new boolean[N][M];
		visited[x][y] = true;
		//test[x][y] = 1;
		clean = 1;
		bfs();

		System.out.println(clean);
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(test[i][j] + " ");
//			}
//			System.out.println();
//		}
	}

	static int N, M, clean;
	static int[][] map;

	static int[] dx = { -1, 0, 1, 0 }; // 북 동 남 서
	static int[] dy = { 0, 1, 0, -1 };

	static int[] back_x = { 1, 0, -1, 0 }; // 후진할때 좌표
	static int[] back_y = { 0, -1, 0, 1 };

	static Queue<Point> q = new LinkedList<>();
	static boolean[][] visited;
	//static int[][] test;

	static void bfs() {
		int cnt = 0;

		while (!q.isEmpty()) {
			Point node = q.poll();

			int x = node.x;
			int y = node.y;
			int loc = node.loc;

			// visited[x][y] = true; // 현재 위치 청소

			// 현재방향 기준으로 왼쪽 탐색
			int new_loc = move(loc);

			int nx = x + dx[new_loc];
			int ny = y + dy[new_loc];

			if (nx < 0 || ny < 0 || nx >= N || ny >= M ) {
				q.add(new Point(x, y, new_loc));

			} 
			else 
			{	
				// 청소안한곳
				if (map[nx][ny] == 0 && !visited[nx][ny]) {
					cnt = 0;
					clean++;
					visited[nx][ny] = true; // 현재 위치 청소
					//test[nx][ny] = clean;
					q.add(new Point(nx, ny, new_loc));
					
				}
				// 청소 했던곳이거나 벽인경우
				else if ((map[nx][ny] == 0 && visited[nx][ny]) || map[nx][ny]==1) 
				{
					if (cnt < 4) 
					{
						cnt++;	// 방향 바꾼거 카운트
						q.add(new Point(x, y, new_loc));
					} 
					// 네 방향 모두 청소 되있거나 벽인경우
					else if (cnt == 4 || map[nx][ny]==1) 
					{
						cnt = 0;
						// 후진
						int bx = x + back_x[loc];
						int by = y + back_y[loc];
						
						// 후진이 벽인경우 끝
						if (map[bx][by] == 1) {
							return;
						} 
						else 
						{
							// 후진했는데 그곳이 청소 안한곳이면 청소해줌
							if (!visited[bx][by]) {
								visited[bx][by] = true;
								//test[bx][by]=clean;
								clean++;
							}
							q.add(new Point(bx, by, loc));
						}
					}

				}
			}

		}

	}

	static int move(int loc) {
		// 현재방향 기준으로 왼쪽 탐색
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
