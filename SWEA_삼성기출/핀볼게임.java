import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// dfs로 풀 경우 스택오버플로우 걸림.. (5 ≤ N ≤ 100)
// bfs로 해결함..
// 종료조건 주는 위치때문에 헤맸음ㅠㅠ
// 이동하기 전에 바로 종료조건 하려했지만 범위를 벗어날경우 반대방향으로 가야하는 조건때문에 이동후 종료조건 줬음.
public class Solution핀볼게임 {
	static class Point {
		int x, y, loc, cnt;

		public Point(int x, int y, int loc, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.loc = loc;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", loc=" + loc + ", cnt=" + cnt + "]";
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine().trim());
		StringTokenizer token;

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine().trim());
			
			// 전체 맵
			map = new int[N][N];
			// 웜홀 위치 저장
			warmhall = new Point[2][11];

			for (int i = 0; i < N; i++) {
				token = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
					
					// 핀볼이 시작할 수 있는 모든 위치
					if (map[i][j] == 0) {
						list.add(new Point(i, j, 0, 0));
					} 
					// 웜홀 위치 짝 찾아서 저장.
					else if (map[i][j] > 5) {
						if (warmhall[0][map[i][j]] == null) {
							warmhall[0][map[i][j]] = new Point(i, j, 0, 0);
						} 
						else {
							warmhall[1][map[i][j]] = new Point(i, j, 0, 0);
						}
					}
				}
			}
			
			// 웜홀에 들어갔을때 반대쪽 나올 위치 저장
			warmchange();
			
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					System.out.print(warmhall_change[i][j]+" ");
//				}
//				System.out.println();
//			}
			
			// 부딪힌 횟수 최대값초기화.
			ans=-1;
			// 시작가능한 모든 경우 확인
			for (int i = 0; i < list.size(); i++) {
				// 시작위치 저장.
				sx = list.get(i).x; sy = list.get(i).y;
				
				// 상하좌우 4방향 모두 확인.
				for(int j=0; j<4; j++) {
					q.add(new Point(sx, sy, j, 0));
					bfs();
				}
			}
			
			System.out.println("#"+t+" "+ans);
			list.clear();
			q.clear();
		}
	}

	static int N, sx, sy, ans, result;
	static ArrayList<Point> list = new ArrayList<>();
	static Queue<Point> q = new LinkedList<>();
	static Point[][] warmhall;
	static Point[][] warmhall_change;
	static int[][] map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static void bfs() {
			
		while(!q.isEmpty()) {
			Point node = q.poll();
			
			int x = node.x;
			int y = node.y;
			int loc = node.loc;
			int cnt = node.cnt;
			
			// 방향으로 한칸씩 이동
			int nx = x + dx[loc];
			int ny = y + dy[loc];
			
			if(loc==0) {
				up(nx, ny, cnt);
			}
			else if(loc==1) {
				down(nx, ny, cnt);
			}
			else if(loc==2) {
				left(nx, ny, cnt);
			}
			else if(loc==3) {
				right(nx, ny, cnt);
			}
		}
	}
	
	static void up(int x, int y, int cnt) {
		// 반대방향으로 이동
		if(x<0 || map[x][y]==1 || map[x][y]==4 || map[x][y]==5) {
			q.add(new Point(x,y,1,cnt+1));
		}
		// 블랙홀에 빠지거나 시작위치로 돌아오면 끝.
		else if(map[x][y]==-1 || (x==sx && y==sy)) {
			// 부딪힌 횟수 최대값.
			ans = Math.max(ans, cnt);
			q.clear();
			return;
		}
		else if(map[x][y]==0) {
			q.add(new Point(x, y, 0, cnt));
		}
		else if(map[x][y]==2) {
			q.add(new Point(x,y,3,cnt+1));
		}
		else if(map[x][y]==3) {
			q.add(new Point(x,y,2,cnt+1));
		}
		// 웜홀에 빠진경우 
		else if(map[x][y]>5) {
			// 반대쪽 웜홀로 빠져나옴.
			int nx = warmhall_change[x][y].x;
			int ny = warmhall_change[x][y].y;
			
			// 방향, 부딪힌 횟수 그대로..
			q.add(new Point(nx, ny, 0, cnt));
		}
	}
	
	static void down(int x, int y, int cnt) {
		if(x>=N || map[x][y]==2 || map[x][y]==3 || map[x][y]==5) {
			q.add(new Point(x, y, 0, cnt+1));
		}
		else if(map[x][y]==-1 || (x==sx && y==sy)) {
			ans = Math.max(ans, cnt);
			q.clear();
			return;
		}
		else if(map[x][y]==0) {
			
			q.add(new Point(x, y, 1, cnt));
		}
		else if(map[x][y]==1) {
			q.add(new Point(x,y,3,cnt+1));
		}
		else if(map[x][y]==4) {
			q.add(new Point(x,y,2,cnt+1));
		}
		else if(map[x][y]>5) {
			int nx = warmhall_change[x][y].x;
			int ny = warmhall_change[x][y].y;
			
			q.add(new Point(nx, ny, 1, cnt));
		}
	}
	
	static void left(int x, int y, int cnt) {
		if(y<0 || map[x][y]==3 || map[x][y]==4 || map[x][y]==5) {
			q.add(new Point(x,y,3,cnt+1));
		}
		else if(map[x][y]==-1 || (x==sx && y==sy)) {
			ans = Math.max(ans, cnt);
			q.clear();
			return;
		}
		else if(map[x][y]==0) {
			q.add(new Point(x, y, 2, cnt));
		}
		else if(map[x][y]==1) {
			q.add(new Point(x, y, 0, cnt+1));
		}
		else if(map[x][y]==2) {
			q.add(new Point(x,y,1,cnt+1));
		}
		
		else if(map[x][y]>5) {
			int nx = warmhall_change[x][y].x;
			int ny = warmhall_change[x][y].y;
			
			q.add(new Point(nx, ny, 2, cnt));
		}
	}
	
	static void right(int x, int y, int cnt) {
		if(y>=N || map[x][y]==1 || map[x][y]==2 || map[x][y]==5) {
			q.add(new Point(x, y, 2, cnt+1));
		}
		else if(map[x][y]==-1 || (x==sx && y==sy)) {
			ans = Math.max(ans, cnt);
			q.clear();
			return;
		}
		else if(map[x][y]==0) {
			q.add(new Point(x, y, 3, cnt));
		}
		else if(map[x][y]==3) {
			q.add(new Point(x, y, 1, cnt+1));
		}
		else if(map[x][y]==4) {
			q.add(new Point(x, y, 0, cnt+1));
		}
		else if(map[x][y]>5) {
			int nx = warmhall_change[x][y].x;
			int ny = warmhall_change[x][y].y;
			
			q.add(new Point(nx, ny, 3, cnt));
		}
	}
	
	// 웜홀 반대방향으로 이동위치 저장.
	static void warmchange() {
		warmhall_change = new Point[N][N];
		
		for (int i = 6; i < 11; i++) {
			if(warmhall[0][i]==null) {
				continue;
			}
			
			int x = warmhall[0][i].x;
			int y = warmhall[0][i].y;

			warmhall_change[x][y] = warmhall[1][i];

			x = warmhall[1][i].x;
			y = warmhall[1][i].y;

			warmhall_change[x][y] = warmhall[0][i];
		}
	}
}
