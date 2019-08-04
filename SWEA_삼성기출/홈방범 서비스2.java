import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 거리로 계산하고 완탐하여 문제 해결함.

public class Solution홈방문서비스 {
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
		StringTokenizer token;

		for (int t = 1; t <= T; t++) {
			token = new StringTokenizer(br.readLine());
			N = Integer.parseInt(token.nextToken());
			M = Integer.parseInt(token.nextToken());

			int[][] map = new int[N][N];
			list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				token = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
          
          // 집 위치 저장
					if (map[i][j] == 1) {
						list.add(new Point(i, j));
					}
				}
			}

			max = -1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
          // 한 위치에서 1~N+3까지 거리에 해당하는 집 있는지 확인.
					move(i, j, 1);
				}
			}
			
			bw.write("#"+t+" "+max+"\n");

		}
		bw.flush();
		bw.close();

	}

	static int N, M, max;
	static ArrayList<Point> list;

	static void move(int sx, int sy, int n) {

		while (true) {
      // 최대 거리를 N+3으로 정해줌..그냥..감으로..함..
			if(n==N+3) {
				break;
			}
			
			int home = 0;
			for (int i = 0; i < list.size(); i++) {

				int x = list.get(i).x;
				int y = list.get(i).y;
        
        // 거리 계산
				int dist = Math.abs(sx - x) + Math.abs(sy - y);
        //거리가 범위안 보다 작으면 홈 갯수++
				if (dist <= n-1) {
					home++;
				}

			}
      // 운영비용 계산
			int cost = n * n + ((n - 1) * (n - 1));
      
      // 운영비용보다 이익이 더 많으면 최댓값 출력.
			if (cost <= home * M) {
				max = Math.max(max, home);
			}
      
			n++;
		}

	}
}
