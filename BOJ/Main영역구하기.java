import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
// 영역구하기
// 직사각형 그리는게 예제처럼 그리려 했는데 굳이 그럴 필요가 없었음..
// 전형적인 bfs , dfs 문제
// 시작점부터 체크해주는거 잊지 않기!!!
// 무난하게 풀 수 있음.
public class Main2583 {
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

		M = Integer.parseInt(token.nextToken());
		N = Integer.parseInt(token.nextToken());
		int K = Integer.parseInt(token.nextToken());

		map = new int[M][N];

		for (int i = 0; i < K; i++) {
			token = new StringTokenizer(br.readLine());

			int sy = Integer.parseInt(token.nextToken());
			int sx = Integer.parseInt(token.nextToken());

			int ey = Integer.parseInt(token.nextToken());
			int ex = Integer.parseInt(token.nextToken());

//			System.out.println(sx +" "+sy+" "+ex+" "+ey);
			paint(sx, sy, ex, ey);

		}
		
		// 영역 갯수 세기
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) {
					q.add(new Point(i, j));
					bfs();
					cnt++;
				}
			}
		}
//		
//		for (int i = 0; i < M; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		Collections.sort(list);
		bw.write(cnt+"\n");
		
		for(int i=0; i<list.size(); i++) {
			bw.write(list.get(i)+" ");
		}
		bw.flush();
		bw.close();

	}

	static int map[][];
	
	// 직사각형 그리기
	static void paint(int sx, int sy, int ex, int ey) {

		for (int i = sx; i < ex; i++) {
			for (int j = sy; j < ey; j++) {
				map[i][j] = 1;
			}
		}

	}

	static int N, M;
	static List<Integer> list = new ArrayList<Integer>();
	static Queue<Point> q = new LinkedList<>();
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static void bfs() {
		// 맵을 1로 표시해놨으므로 헷갈리지 않게 2부터 카운트 세줌
		int count = 2;
		
		// 시작점 찍어주기
		map[q.peek().x][q.peek().y]=count;
		
		while (!q.isEmpty()) {
			
			Point node = q.poll();

			int x = node.x;
			int y = node.y;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || nx >= M || ny < 0 || ny >= N) {
					continue;
				}

				if (map[nx][ny] == 0) {
					map[nx][ny] = count++;
					q.add(new Point(nx, ny));
					
				}
			}

		}
		
		// 2부터 카운트셌으므로 1빼줌.
		list.add(count-1);
	}

}
