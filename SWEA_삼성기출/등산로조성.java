import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 쓸데없는 코드들이 많았음..
// 방문 체크를 하면 4방향으로 뻗어가면서 한번 갔던곳을 또 못가므로 안해줘도됨
// 높이 깎은 후 원상태로 돌려줘야함.
// 조금 까다로운 문제였음.
public class Solution등산로조성 {
	static class Point {
		int x, y;

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

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			StringTokenizer token = new StringTokenizer(br.readLine());

			N = Integer.parseInt(token.nextToken());
			K = Integer.parseInt(token.nextToken());

			map = new int[N][N];
			
			// 최대 높이 위치 담을 리스트
			list = new ArrayList<>();

			int max = -1;
			for (int i = 0; i < N; i++) {
				token = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
					// 최대 높이 리스트에 담기
					if (max < map[i][j]) {
						max = map[i][j];
						list.clear();
						list.add(new Point(i, j));
					} else if (max == map[i][j]) {
						list.add(new Point(i, j));
					}
				}
			}

//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}

//			System.out.println(list.toString());
			length = -1;
			mount();
			System.out.println("#" + t + " " + length);
		}
	}

	static int N, K, length, cnt;
	static List<Point> list;
	static int[][] map;
	
	// 등산로 탐색
	static void mount() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				// 최대위치 시작점은 높이 깍는데 제외
				for (int n = 0; n < list.size(); n++) {
					int x = list.get(n).x;
					int y = list.get(n).y;
					
					// 최대위치 시작점은 높이 깍는데 제외
					if (i == x && j == y) {
						continue;
					}
					
					// 전체 탐색하면서 k만큼 깍음.
					for (int k = K; k >= 0; k--) {
						// 만약 깍은게 0보다 작으면 넘어감.
						if (map[i][j] - k < 0) {
							continue;
						}
						
						// 높이 k만큼 깍음
						map[i][j] -= k;
						
						// 맵, 시작점, 시작높이 , 카운트
						dfs(map,x,y,map[x][y],1);
						
						// 원래 높이로 다시 돌려줌.
						map[i][j] += k;
					}
				}
			}
		}

	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	
	// 상하좌우 돌면서 등산로 탐색
	private static void dfs(int[][] tmp, int x, int y, int before, int cnt) {

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			// 범위 확인
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
				continue;
			}
			
			// 현재 갈 수 있는 곳이 그 전보다 작으면 계속 탐색
			if (tmp[nx][ny] < before) {
				dfs(tmp, nx, ny, tmp[nx][ny], cnt + 1);
			} 
		}
		
		// 갈 수 있는 곳 다 탐색후 최대값 구함.
		length = Math.max(cnt, length);

	}

}
