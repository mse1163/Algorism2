import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
// 인구 이동
// 종료시키는 조건이 어려웠음. 종료를 못해서 무한 루트 돌아븜..ㄷㄷ
// isok_cnt 넣어서 하니 해결됨
public class Main {
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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

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
		result = 1;
		while (true) {
			// 방문체크
			boolean sel[][] = new boolean[N][N];
			// 연합여부 체크
			boolean isok = false;
			// 연합 몇번햇는지 체크
			int isok_cnt=0;
			// 방문하지 않은거 찾으면서 전체 탐색
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!sel[i][j]) {
						q.add(new Point(i, j));
						sel[i][j] = true;
						int sum = map[i][j];
						isok = move(map, sel, sum);
						if(isok) {
							isok_cnt++;
						}
						q.clear();
					}
				}
			}

//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			// 연합 한번도 안했으면 끝
			if (isok_cnt==0) {
				break;
			}
			
			ans++;

		}
		bw.write(ans+"");
		bw.flush();
		bw.close();
	}

	static int N, L, R, ans, result;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static List<Point>[] list;
	static Queue<Point> q = new LinkedList<>();

	static boolean move(int[][] map, boolean[][] sel, int sum) {

		List<Point> list = new ArrayList<>();
		list.add(new Point(q.peek().x, q.peek().y));
		while (!q.isEmpty()) {

			Point node = q.poll();

			int x = node.x;
			int y = node.y;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}
				
				// 차이
				int dist = Math.abs(map[x][y] - map[nx][ny]);

				if (L <= dist && dist <= R) {

					if (!sel[nx][ny]) {
						// 방문체크, 값을 더해줌
						sel[nx][ny] = true;
						sum += map[nx][ny];
						list.add(new Point(nx, ny));

						q.add(new Point(nx, ny));
					}
				}
			}
		}

        // 연합시킴.           
		if (list.size() > 1) {
			
			int new_num = sum / list.size();

			for (int i = 0; i < list.size(); i++) {
				map[list.get(i).x][list.get(i).y] = new_num;
			}

			return true;
		}
		
		// 연합 안됨
		return false;
	}
}
