import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 처음에는 그냥 인접해 있으면 다 연합한 거로 생각했음
// 하지만 상하좌우에 따라 연합한 구역이 나눠짐. -> 즉, 나라가 인접해 있다고 다 연합 되는것은 아니였음.
public class Main인구이동 {
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
		StringTokenizer token = new StringTokenizer(br.readLine());

		N = Integer.parseInt(token.nextToken());
		L = Integer.parseInt(token.nextToken());
		R = Integer.parseInt(token.nextToken());

		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}

		ans = 0;
		move(map, 0);
		System.out.println(ans);

	}

	static int N, L, R, ans;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Queue<Point> q = new LinkedList<>();

	static void move(int[][] map, int cnt) {
		// 연합한 곳 체크 배열
		int[][] check = new int[N][N];
		
		// 연합한 곳 한번이라도 있으면 체크. -> 종료 조건시 사용할 것.
		boolean isok = false;
		
		// 연합한 곳 구별하기 위한 변수
		int idx = 1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				// 이미 방문했으면 다음칸으로 이동.
				if (check[i][j] != 0) {
					continue;
				}
				
				// 연합할 인구 총합, 연합한 나라 수.
				int sum = 0, sum_cnt = 0;
				
				// 시작점
				q.add(new Point(i, j));

				while (!q.isEmpty()) {
					Point node = q.poll();

					int x = node.x;
					int y = node.y;

					for (int k = 0; k < 4; k++) {
						int nx = x + dx[k];
						int ny = y + dy[k];

						if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
							continue;
						}
						
						// 두 나라간 인구 차이.
						int dist = Math.abs(map[x][y] - map[nx][ny]);
						
						// L<= 인구 차이 <=R
						if (L <= dist && dist <= R) {

							// 연합하였음 체크
							isok = true;
							
							// 연합을 아직 안했으면 연합체크, 연합할 인구 총합, 연합할 나라 수++
							if (check[x][y] == 0) {
								check[x][y] = idx;
								sum += map[x][y];
								sum_cnt++;
							}
							
							// 연합을 아직 안했으면 연합체크, 연합할 인구 총합, 연합할 나라 수++
							if (check[nx][ny] == 0) {
								check[nx][ny] = idx;
								sum += map[nx][ny];
								sum_cnt++;
								
								// 연합한 곳을 기준으로 다음 연합할 곳 찾기.
								q.add(new Point(nx, ny));
							}
						}
					}

				}
				
				// 연합한 나라가 없으면 다음으로 이동.
				if (sum_cnt == 0) {
					continue;
				}
				
				// 연합한 후 인구
				int dist = sum / sum_cnt;
				
				for (int n = 0; n < N; n++) {
					for (int m = 0; m < N; m++) {
						// 연합한 것끼리 찾아서 인구 이동해줌.
						if (check[n][m] == idx) {
							map[n][m] = dist;
						}
					}
				}
				
				// 다음 연합이랑 구별하기 위해 ++
				idx++;

			}
		}
		
		// 한번도 연합 안했으면 종료.
		if (!isok) {
			ans = cnt;
			return;
		}

//		
//		System.out.println("==========map==============");
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}

//		System.out.println("=========check=============");
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(check[i][j]+" ");
//			}
//			System.out.println();
//		}

		move(map, cnt + 1);

	}

}
