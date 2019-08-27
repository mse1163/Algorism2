import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 해당 위치에서 직사각형 놓을 수 있는지 확인하는 과정에서 범위 체크 잘해줘야함
// 무난한 bfs 문제.
public class Main직사각형탈출 {
	static class Point{
		int x,y,cnt;

		public Point(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", cnt=" + cnt + "]";
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		map = new int[N+1][M+1];
		
		for(int i=1; i<N+1; i++) {
			token = new StringTokenizer(br.readLine());
			for(int j=1; j<M+1; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		
		token = new StringTokenizer(br.readLine());
		
		// 직사각형 크기
		int xsize = Integer.parseInt(token.nextToken());
		int ysize = Integer.parseInt(token.nextToken());
		
		// 시작점
		sx = Integer.parseInt(token.nextToken());
		sy = Integer.parseInt(token.nextToken());
		
		// 도착점
		ex = Integer.parseInt(token.nextToken());
		ey = Integer.parseInt(token.nextToken());
		
		ans=-1;
		q.add(new Point(sx, sy, 0));
		move(xsize,ysize);
		System.out.println(ans);
		q.clear();
	}
	
	static int N,M,sx,sy,ex,ey,ans;
	static int[][] map;
	static Queue<Point> q = new LinkedList<>();
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static void move(int xsize, int ysize) {
		// 방문체크 배열
		int[][] sel = new int[N+1][M+1];
		// 시작점 체크
		sel[sx][sy]=1;
		
		while(!q.isEmpty()) {
			
			Point node = q.poll();
			int x = node.x;
			int y = node.y;
			int cnt = node.cnt;
			
			// 도착점이면 리턴.
			if(x==ex && y==ey) {
				ans = cnt;
				return;
			}
			
			// 상하좌우 확인
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				// 범위 확인
				if(nx<1 || ny<1 || nx>=N+1|| ny>=M+1) {
					continue;
				}
				
				// 방문아직 안했으면
				if(sel[nx][ny]==0) {
					// 해당위치에서 직사각형범위 체크.
					if(nx+xsize>N+1 || ny+ysize>M+1) {
						continue;
					}
					// 장애물없이 직사각형 놓은수 있는지 확인.
					boolean isok = check(nx,ny,xsize,ysize);
					// 만약 직사각형안에 장애물있으면 넘어감.
					if(isok) {
						continue;
					}
					
					// 직사각형 놓을 수 잇으면 방문체크하고 큐에 위치, cnt+1 저장.
					sel[nx][ny]=1;
					q.add(new Point(nx, ny, cnt+1));
				}
				
			}
			
		}
	}
	
	// 해당 위치에서 직사각형 안에 장애물없이 놓을수 있는지 확인.
	private static boolean check(int nx, int ny, int xsize, int ysize) {
		
		for(int i=nx; i<nx+xsize; i++) {
			for(int j=ny; j<ny+ysize; j++) {
				// 장애물이 하나라도 있으면 종료
				if(map[i][j]==1) {
					return true;
				}
			}
		}
		
		return false;
		
	}
}
