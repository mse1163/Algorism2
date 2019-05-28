import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
// 홈방법 서비스
public class Solution2117 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			StringTokenizer token = new StringTokenizer(br.readLine());
			N = Integer.parseInt(token.nextToken());
			M = Integer.parseInt(token.nextToken());

			map = new int[N][N];
			h_list = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				token = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
					// 집 위치 다 넣어줌
					if (map[i][j] == 1) {
						h_list.add(new Point(i, j));
					}
				}

			}

			// 입력확인
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}

			// 운영 서비스 비용
			service = new int[25];
			for (int i = 1; i < 25; i++) {
				service[i] = i * i + (i - 1) * (i - 1);
			}
			
		//	System.out.println(h_list.size());
		//	System.out.println(Arrays.toString(service));

			ans = -1;
			
			for(int i=1; i<25; i++) {
				count(i);
			}

			sb.append("#").append(t).append(" ").append(ans).append("\n");

		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static int N, M, ans;
	static int[][] map;
	static int[] service;
	static List<Point> h_list;

	static void count(int k) {
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < N; m++) {
				int home = 0;

				for (int i = 0; i < h_list.size(); i++) {
					int x = h_list.get(i).x;
					int y = h_list.get(i).y;
					// 거리가 k보다 작으면 카운트 세줌
					if (Math.abs(n - x) + Math.abs(m - y) <= k - 1) {
						home++;
					}
				}
				
				// home의 이익이 서비스 이용보다 더 크면 집 갯수 출력
				if (home * M >= service[k]) {
					ans = Math.max(home, ans);
				}
			}
		}
	}

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

}
