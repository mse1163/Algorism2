import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
// 조합으로 벽3개 세울거 완탐 
// 난이도 무난..
// 조합 + dfs,bfs 완탐
public class Main연구소 {
	static class Point{
		int x,y;

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
		
		int[][] map = new int[N][M];
		
		// 바이러스 리스트
		virus = new ArrayList<>();
		// 안전구역 리스트
		list = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			token = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
				if(map[i][j] == 0) {
					list.add(new Point(i, j));
					
				}
				else if(map[i][j] == 2) {
					virus.add(new Point(i, j));
				}
			}
		}
		
		ans = -1;
		// 벽3개 세울곳 저장
		Point[] safe = new Point[3];
		combi(map,list, safe, 0, 0);
		
		bw.write(ans+"");
		bw.flush();
		bw.close();
	}
	
	static int N,M, ans;
	static ArrayList<Point> virus, list;
	
	// 조합으로 벽3개 세울거 모든 경우 탐색
	static void combi(int[][] map,ArrayList<Point> list, Point[] safe, int cnt, int idx) {
		if(cnt==3) {
			
			// 처음 맵 복사해서 벽 세워봄.
			int[][] tmp = new int[N][M];
			deepCopy(tmp, map);
			
			// 뽑은 경우의 수로 벽 3개 세움
			for(int i=0; i<3; i++) {
				int x = safe[i].x;
				int y = safe[i].y;
				
				tmp[x][y]=1;
			}
			
			// 바이러스 퍼짐.
			for(int i=0; i<virus.size(); i++) {
				int x = virus.get(i).x;
				int y = virus.get(i).y;
				
				dfs(tmp, x, y);
			}
			
			// 안전구역 몇개인지 확인
			safeblock(tmp);
			
			return;
		}
		
		if(idx==list.size()) {
			return;
		}
		
		safe[cnt] = list.get(idx);
		combi(map, list, safe, cnt+1, idx+1);
		combi(map, list, safe, cnt, idx+1);
	}
	
	// 안전구역 확인.
	private static void safeblock(int[][] tmp) {
		int cnt=0;
		// 전체 맵돌려서 안전구역 셈
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(tmp[i][j] == 0) {
					cnt++;
				}
			}
		}
		
		// 최대값구하기
		ans = Math.max(ans, cnt);
	}

	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	// 바이러스 퍼짐.
	private static void dfs(int[][] map, int x, int y) {
		
		// 4방향 탐색
		for(int i=0; i<4; i++) {
			
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			// 범위 확인
			if(nx<0 || nx>=N || ny<0 || ny>=M) {
				continue;
			}
			
			// 바이러스로 퍼짐.
			if(map[nx][ny]==0) {
				map[nx][ny]=2;
				dfs(map, nx, ny);
			}
			
		}
	}
	
	// 맵 복사
	private static void deepCopy(int[][] tmp, int[][] map) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				tmp[i][j] = map[i][j];
			}
		}
		
	}
	
	
}
