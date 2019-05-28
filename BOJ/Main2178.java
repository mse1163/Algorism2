

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 미로찾기

public class Main_2178 {
	static class Point {
		int x;
		int y;
		int cnt;

		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", cnt=" + cnt + "]";
		}

	}

	static Queue<Point> q = new LinkedList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 행의 갯수:R, 열의 개수:C
		R = sc.nextInt();
		C = sc.nextInt();

		int[][] map = new int[R][C];
		boolean[][] visited = new boolean[R][C];
		// 맵이 만들어지고
		for (int i = 0; i < R; i++) {
			String m = sc.next();
			for (int j = 0; j < C; j++) {
				map[i][j] = (m.charAt(j) - '0');
			}
		}

		// 처음 시작점은 0,0

		// 목적 도착점은 R-1,C-1
		// 0은 이동불가, 1은 이동가능/

		dfs(map,visited);
		System.out.println(min);

		q.clear();
		// 출력은 출발점에서 도착점까지 몇개의 1을 밟았는지(몇번만에 이동했는지) 처음 시작점도 포함
		// DFS로 짜보세요
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int nx, ny;
	static int R, C;
	static int min = 1000000;

	static void dfs(int[][] map, boolean[][] visited) {
		q.add(new Point(0,0,1));
		visited[0][0] = true;
		
		while (!q.isEmpty()) {
			Point d = q.poll();
			int cnt = d.cnt;
			
			if(d.x==R-1 && d.y == C-1) {
				if (min > cnt) {
					min = cnt;
				}
			}
			
			for (int i = 0; i < 4; i++) {

				nx = d.x + dx[i];
				ny = d.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
					continue;
				}

				if (!visited[nx][ny] && map[nx][ny] == 1) {

					visited[nx][ny] = true;
					q.add(new Point(nx, ny,d.cnt+1));

				}
			}
		}
	}
}
