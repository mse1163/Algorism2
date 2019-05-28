

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 토마토1
public class Main_7576 {
	static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		 M = sc.nextInt();	// 가로
		 N = sc.nextInt();	// 세로
		
		int[][] map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j]==1) {
					q.add(new Point(i, j));
				}
			}
		}
				bfs(map);

		q.clear();
		
	}
	
	static Queue<Point> q = new LinkedList<>();
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int nx , ny , M , N;
	
	static void bfs(int[][] map) {
		
		while(!q.isEmpty()) {
			Point d = q.poll();
			
			for(int i=0; i<4; i++) {
				nx = d.x + dx[i];
				ny = d.y + dy[i];
			
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}
				
                // 익은 토마토에 날짜 누적해서 감
				if(map[nx][ny]==0) {
					map[nx][ny]=map[d.x][d.y] + 1;
					q.add(new Point(nx, ny));
				}
			}		
		}
			
		int max = 0;
		boolean isOk = true;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==0) {
					isOk = false;
					
				}
				if(max < map[i][j])
					max = map[i][j];
				
			}
		}
		
		if(isOk==false) {
			System.out.println(-1);
		}else {
			System.out.println(max-1);
		}

	}
}
