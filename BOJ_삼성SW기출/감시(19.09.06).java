import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main감시 {
	static class Point {
		int x, y, cctv;

		public Point(int x, int y, int cctv) {
			super();
			this.x = x;
			this.y = y;
			this.cctv = cctv;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", cctv=" + cctv + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());

		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
				if (map[i][j] != 0 && map[i][j] != 6) {
					list.add(new Point(i, j, map[i][j]));
				}
			}
		}
		
		ans=987654321;
		dfs(map, 0);
		System.out.println(ans);
		
	}

	static int N, M,ans;
	static ArrayList<Point> list = new ArrayList<>();
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	static void dfs(int[][] map, int idx) {

		if (idx == list.size()) {
			int cnt=0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j]==0) {
						cnt++;
					}
//					System.out.print(map[i][j]+" ");
				}
//				System.out.println();
			}
//			System.out.println();
			
			ans = Math.min(ans, cnt);
			return;
		}

		int x = list.get(idx).x;
		int y = list.get(idx).y;
		int num = list.get(idx).cctv;
		
	
		if (num == 1) {
			
			for (int i = 0; i < 4; i++) {
				int[][] tmp = new int[N][M];
				deepCopy(tmp, map);
				
				look(tmp, x, y, (0 + i)%4);
				dfs(tmp, idx + 1);
			}
		}
		if (num == 2) {
			for (int i = 0; i < 2; i++) {
				int[][] tmp = new int[N][M];
				deepCopy(tmp, map);
				
				look(tmp, x, y, (0 + i)%4);
				look(tmp, x, y, (2 + i)%4);
				dfs(tmp, idx + 1);
			}
		}
		if (num == 3) {
			for (int i = 0; i < 4; i++) {
				int[][] tmp = new int[N][M];
				deepCopy(tmp, map);
				
				look(tmp, x, y, (0 + i)%4);
				look(tmp, x, y, (3 + i)%4);
				dfs(tmp, idx + 1);
			}
		}
		if (num == 4) {
			for (int i = 0; i < 4; i++) {
				int[][] tmp = new int[N][M];
				deepCopy(tmp, map);
				
				look(tmp, x, y, (0 + i)%4);
				look(tmp, x, y, (2 + i)%4);
				look(tmp, x, y, (3 + i)%4);
				dfs(tmp, idx + 1);
			}
		}
		if (num == 5) {
			int[][] tmp = new int[N][M];
			deepCopy(tmp, map);
			
				look(tmp, x, y, 0);
				look(tmp, x, y, 1);
				look(tmp, x, y, 2);
				look(tmp, x, y, 3);
				dfs(tmp, idx + 1);
		}
	}

	static void look(int[][] map, int x, int y, int loc) {
		while (true) {
			x += dx[loc];
			y += dy[loc];

			if (x<0 || y<0 || x >= N || y >= M || map[x][y] == 6) {
				return;
			}
			
			if(map[x][y]==1 || map[x][y]==2 || map[x][y]==3 || map[x][y]==4 || map[x][y]==5 || map[x][y]==9) {
				continue;
			}
			map[x][y] = 9;
		}
	}
	
	static void deepCopy(int[][] tmp, int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmp[i][j] = map[i][j];
			}
		}
	}
}
