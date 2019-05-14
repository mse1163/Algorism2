import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 3차배열 방문체크 처음 사용하여 이해를 못함.
// visited[][][0] -> 벽 안부순상태, 길이면 요거로 방문체크
// visited[][][1] -> 벽을 이미 부순상태거나 벽일경우 요거로 방문체크
// 최단경로 구하는거는 BFS 사용. 
// DFS는 도착한게 최단인지 알 수 없음.
// 벽부수고 이동하기
public class Main2206 {
	static class Point {
		int x, y, cnt;
		boolean isok;

		public Point(int x, int y, int cnt, boolean isok) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.isok = isok;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", cnt=" + cnt + ", isok=" + isok + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer token = new StringTokenizer(br.readLine());

		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());

		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

//		for(int i=0; i<N; i++) {
//			for(int j=0; j<M; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}

		ans = 987654321;
		// 방문체크 배열 (벽 부숨, 안부숨)
		visited = new boolean[N][M][2];
		
		// 시작점 방문체크
		visited[0][0][0]=true;
		
		q.add(new Point(0, 0, 1, false));
		dfs(map);
		
		// 끝까지 못감.
		if (ans == 987654321) {
			ans = -1;
		}

		q.clear();

		bw.write(ans + "");
		bw.flush();
		bw.close();

	}

	static int N, M, ans;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Queue<Point> q = new LinkedList<>();
	static boolean[][][] visited;
	
	// 이동.
	private static void dfs(int[][] map) {

		while (!q.isEmpty()) {
			Point node = q.poll();

			int x = node.x;
			int y = node.y;
			int cnt = node.cnt;
			boolean isok = node.isok;	// 벽 부술 수 있는지 여부
			
			// 끝점 오면 끝냄.
			if (x == N - 1 && y == M - 1) {
				ans = Math.min(ans, cnt);
				return;
			}

			// 최소값 구하므로 ans보다 크면 끝냄.
			if (ans < cnt) {
				return;
			}
			
			// 4방향 상하죄우 확인
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				// 범위 확인
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}

				// 벽 부술수 있음
				if(!isok) {
					// 벽이면서 벽 방문안함. 벽을 부수지 않았음 visited[][][1]로 체크
					if(map[nx][ny]==1 && !visited[nx][ny][1]) {
						// 벽부순거 방문체크
						visited[nx][ny][1]=true;
						// 벽 부순거 체크.(true)로 바꿔줌.
						q.add(new Point(nx, ny, cnt+1, true));
						
					}
					
					// 길이면서 방문안함.
					else if(map[nx][ny]==0 && !visited[nx][ny][0]){
						// 방문체크
						visited[nx][ny][0]=true;
						q.add(new Point(nx, ny, cnt+1, isok));
					}
				}
				// 벽 부수고 와서 못부숨
				else {
					// 길이면서 방문안함. -> 이미 벽을 한번 부셨으로 visited[][][1]로 체크
					if(map[nx][ny]==0 && !visited[nx][ny][1]){
						visited[nx][ny][1]=true;
						q.add(new Point(nx, ny, cnt+1, isok));
					}
				}
			}
		}
	}
}
