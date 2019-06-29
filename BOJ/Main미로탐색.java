import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// bfs정석 문제 
// bfs 기억안날때 상기시키기 좋은 문제.

public class Main미로탐색 {
	
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
			return  x + ", y=" + y + ", cnt=" + cnt;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j)-'0';
			}
//			System.out.println(Arrays.toString(map[i]));
		}
		
		// 큐에 시작점, 카운트 1부터 함.
		q.add(new Point(0, 0, 1));
		bfs();
		

	}
	
	static int N,M;
	static int[][] map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static Queue<Point> q = new LinkedList<>();
	
	// 탐색
	static void bfs() {
		
		while(!q.isEmpty()) {
			
			Point node = q.poll();
			
			int x = node.x;
			int y = node.y;
			int cnt = node.cnt;
			
			// 끝점왔을 때 cnt값 출력.
			if(x==N-1 && y==M-1) {
				
				System.out.println(cnt);
				
				for(int i=0; i<N; i++) {
					System.out.println(Arrays.toString(map[i]));
				}
			}
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx<0 || nx >=N || ny<0 || ny>=M) {
					continue;
				}
				
				if(map[nx][ny]==1) {
					// 방문 체크 하면서 눈으로 발자국 확인가능.
					map[nx][ny]=cnt+1;
					q.add(new Point(nx, ny, cnt+1));
				}
				
			}
		}
	}

}
