import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// dfs 잘 사용할 줄 알면 무난하게 할 수 있음..
// 벽돌 상하좌우 확인하면서 터트릴때 범위 확인 먼저 해줘야함..
public class Solution벽돌깨기2{

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		StringTokenizer token;

		for (int t = 1; t <= T; t++) {
			token = new StringTokenizer(br.readLine());
			N = Integer.parseInt(token.nextToken());
			W = Integer.parseInt(token.nextToken());
			H = Integer.parseInt(token.nextToken());

			int[][] map = new int[H][W];

			for (int i = 0; i < H; i++) {
				token = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
				}
			}

//			for (int i = 0; i < H; i++) {
//				for (int j = 0; j < W; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}

			ans = 987654321;
			start(map, 0);

			if (ans == 987654321) {
				ans = 0;
			}

			bw.write("#" + t + " " + ans + "\n");
		}
		bw.flush();
		bw.close();

	}

	static int N, W, H, ans;
	static Queue<Integer> q = new LinkedList<>();

	// 구슬 던져서 벽돌 맞추기 시작.
	private static void start(int[][] map, int cnt) {
		// 구슬 던진 횟수.
		if (cnt == N) {
			// 남은 구슬 세기.
			wall_count(map);
			return;
		}

		for (int j = 0; j < W; j++) {
			for (int i = 0; i < H; i++) {

				if (map[i][j] != 0) {
					// 맵복사
					int[][] tmp = new int[H][W];
					deepCopy(tmp, map);

					// 벽돌 터트리기.
					boom(tmp, i, j);

					// 벽돌 내리기.
					wall_down(tmp, cnt);

					start(map, cnt + 1);

					break;
				}
			}
		}
	}

	// 벽돌 터트리기.
	private static void boom(int[][] map, int x, int y) {
		// 범위 벗어나면 리턴
		if (x < 0 || y < 0 || x >= H || y >= W) {
			return;
		}

		// 벽돌 숫자 기억.
		int wall = map[x][y];

		// 벽돌이 0이 아니면 0으로 만듬.
		if (wall != 0) {
			map[x][y] = 0;
		}

		// 벽돌 숫자만큼 상하좌우 뻗어나감.
		for (int i = 1; i < wall; i++) {
			boom(map, x - i, y);
			boom(map, x + i, y);
			boom(map, x, y - i);
			boom(map, x, y + i);
		}
	}

	// 벽돌 내리기
	private static void wall_down(int[][] map, int cnt) {

		for (int j = 0; j < W; j++) {
			for (int i = H - 1; i >= 0; i--) {
				if (map[i][j] != 0) {
					q.add(map[i][j]);
				}
				map[i][j] = 0;
			}

			int d = H - 1;
			while (!q.isEmpty()) {
				int num = q.poll();

				map[d][j] = num;
				d--;

			}
		}
	}

	// 남은 벽돌 세기
	private static void wall_count(int[][] map) {
		int count = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] != 0) {
					count++;
				}
			}
		}

		ans = Math.min(ans, count);

	}

	// 벽 복사.
	private static void deepCopy(int[][] tmp, int[][] map) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				tmp[i][j] = map[i][j];
			}
		}
	}

}
