package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
// 감시-> CCTV 돌려서 감시하는 문제
// 값을 그 전상태로 돌리는 것을 못햇음...
// 계속 덮어져서 이어짐...
// 맵 복사가 잘못되있엇나..맵 복사해놓은 위치가 잘못되었나 했었음..ㅋㅋ

public class Main {
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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken()); // 세로
		M = Integer.parseInt(token.nextToken()); // 가로

		int[][] map = new int[N][M];

		list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
				
				// CCTV 리스트에 담기
				if (map[i][j] != 0 && map[i][j] != 6) {
					
					list.add(new Point(i, j, map[i][j]));
				}
			}
		}

		ans = 987654321;
		powerset(map, 0);

		bw.write(ans+" ");
		bw.flush();
		bw.close();
	}

	static int N, M, ans;
	static List<Point> list, check;
	static int[] dx = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dy = { 1, 0, -1, 0 };

	static void powerset(int[][] tmp, int idx) {
		// CCTV 다 확인 한경우
		if (idx == list.size()) {

			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (tmp[i][j] == 0) {
						cnt++;
					}

					if (ans < cnt)
						return;
				}
			}

			ans = Math.min(cnt, ans);
			return;
		}
		
		// CCTV번호, 위치
		int num = list.get(idx).cctv;
		int x = list.get(idx).x;
		int y = list.get(idx).y;

		if (num == 1) {

			for (int i = 0; i < 4; i++) {

				int[][] copy = new int[N][M];
				newMap(copy, tmp);
				
				// 우 하 좌 상
				location(copy, x, y, i);
				powerset(copy, idx + 1);

			}

		} else if (num == 2) {
			for (int i = 0; i < 2; i++) {
				int[][] copy = new int[N][M];
				newMap(copy, tmp);
				
				// 좌우 상하
				location(copy, x, y, i);
				location(copy, x, y, i + 2);

				powerset(copy, idx + 1);

			}

		} else if (num == 3) {
			for (int i = 0; i < 4; i++) {
				int[][] copy = new int[N][M];
				newMap(copy, tmp);
				
				// 상우 우하 하좌 좌상
				location(copy, x, y, (i + 3) % 4);
				location(copy, x, y, i);

				powerset(copy, idx + 1);

			}

		} else if (num == 4) {
			for (int i = 0; i < 4; i++) {
				int[][] copy = new int[N][M];
				newMap(copy, tmp);
				
				//좌상우 상우하 우하좌 하좌상
				location(copy, x, y, (i + 2) % 4);
				location(copy, x, y, (i + 3) % 4);
				location(copy, x, y, i);

				powerset(copy, idx + 1);

			}
		} else if (num == 5) {
			
			// 상하좌우
			location(tmp, x, y, 0);
			location(tmp, x, y, 1);
			location(tmp, x, y, 2);
			location(tmp, x, y, 3);

			powerset(tmp, idx + 1);

		}

	}

	static void location(int[][] copy, int x, int y, int loc) {
		// 오른쪽 확인
		if (loc == 0) {
			while (true) {

				y += 1;

				if (y >= M) {
					break;
				}

				if (copy[x][y] == 6) {
					break;
				}

				if (copy[x][y] == 0) {

					copy[x][y] = 9;
				}
			}

		}
		// 아래쪽 확인
		else if (loc == 1) {
			while (true) {
				x += 1;

				if (x >= N) {
					break;
				}

				if (copy[x][y] == 6) {
					break;
				}

				if (copy[x][y] == 0) {

					copy[x][y] = 9;
				}
			}
		}
		// 왼쪽 확인
		else if (loc == 2) {
			while (true) {
				y -= 1;

				if (y < 0) {
					break;
				}

				if (copy[x][y] == 6) {
					break;
				}

				if (copy[x][y] == 0) {

					copy[x][y] = 9;
				}
			}
		}
		// 위쪽 확인
		else if (loc == 3) {
			while (true) {
				x -= 1;

				if (x < 0) {
					break;
				}

				if (copy[x][y] == 6) {
					break;
				}

				if (copy[x][y] == 0) {

					copy[x][y] = 9;
				}
			}
		}

	}
	
	// 맵 새롭게 복사
	static void newMap(int[][] tmp, int[][] copy) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmp[i][j] = copy[i][j];
			}
		}
	}

}
