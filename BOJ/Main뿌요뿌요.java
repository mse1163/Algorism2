import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
// 터트리고 맵내리는거 기억해놓을것!! 
// 처음에는 보고 풀었는데 이번에는 혼자서 생각하면서 풀었음>.<
// 무난무난했던 문제.
public class MainPuyoPuyo {
	static class Point {
		int x, y;
		char color;

		public Point(int x, int y, char color) {
			super();
			this.x = x;
			this.y = y;
			this.color = color;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", color=" + color + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[][] map = new char[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);

			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}

		ans = 0;
		move(map);
		System.out.println(ans);
	}

	static int N = 12, M = 6, ans;
	static ArrayList<Point> list;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static Queue<Character> q = new LinkedList<>();

	static void move(char[][] map) {
		// 터질 뿌요 있는지 확인.
		boolean isok = false;
		// 방문체크
		int[][] sel = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 뿌요면서 방문안했으면 시작.
				if (map[i][j] != '.' && sel[i][j]==0) {
					list = new ArrayList<>();
					// 시작한 뿌요 리스트에 담아주고 방문체크.
					list.add(new Point(i, j, map[i][j]));
					sel[i][j]=1;
					
					dfs(map,sel, i, j, map[i][j]);
					
					// 만약 리스트 크기가 4이상이면 터트릴거임.
					if (list.size() >= 4) {
						// 터트린거 체크.
						isok = true;
						boom(map, list);
					}
				}
			}
		}
		
		// 만약 뿌요 터트렸으면 횟수+ 하고 반복. 터트린거 없으면 종료.
		if (isok) {
			ans++;
			move(map);
		}

	}
	
	// 4방향 같은색 있는지 확인.
	static void dfs(char[][] map, int[][] sel,int x, int y, char color) {

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
				continue;
			}
			
			//4방향 확인하고 같은 색이면서 방문안했으면 리스트에 담아주고 방문체크.
			if (map[nx][ny] == color && sel[nx][ny]==0) {
				list.add(new Point(nx, ny, color));
				sel[nx][ny]=1;
				dfs(map,sel, nx, ny, color);
			}
		}
	}

	static void boom(char[][] map, ArrayList<Point> list) {
		// 4개이상 모여있는거 터트림.
		for (int i = 0; i < list.size(); i++) {

			int x = list.get(i).x;
			int y = list.get(i).y;

			map[x][y] = '.';

		}
		
		// 다 터트리고 나서 맵 내리기.
		for (int j = 0; j < M; j++) {
			for (int i = N-1; i >= 0; i--) {
				// 원래 있던거 큐에 담음.
				q.add(map[i][j]);
				// 맵 초기상태로 돌려놓음.
				map[i][j]='.';
			}
			
			// 뿌요를 맨 아래부터 채워야 하므로 N-1부터 시작
			int cnt = N - 1;
			while (!q.isEmpty()) {
				// 큐에 있던거 하나씩 빼면서
				char c = q.poll();
				// 뿌요면 맵에 넣어줌.
				if (c != '.') {
					map[cnt][j] = c;
					cnt--;
				}

			}
		}
	}

}
