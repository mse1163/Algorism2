import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 빈공간에 벽내릴때 count--를 빼먹음..그래서 값이 계속 0으로 나옴..
// 최소값구하기 인데 처음 ans를 0으로 해줘서 값이 계속 0이 나옴..
// 벽 부수러 가기전에 먼저 맵 복사해둘것!!
// 상하좌우 벽 숫자만큼 거리 부술때 4방향 포문보다 하나씩하는게 더 나음..
// 여러번 반복해서 푸는 법 익힐것!!!
public class Solution벽돌깨기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			StringTokenizer token = new StringTokenizer(br.readLine());
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
			
			// 최소값 구하기 초기화.
			ans = 987654321;
			dfs(map, 0);

			bw.write("#" + t + " " + ans + "\n");

		}
		bw.flush();
		bw.close();
	}

	static int N, H, W, ans;

	static void dfs(int[][] map, int count) {
		// 구슬 N번 쏨.
		if (count == N) {
			// 남은 벽 확인
			remainWall(map);
//    		System.out.println(cnt);
			
			// 남은 벽 최소구하기.
			ans = Math.min(ans, cnt);
			return;
		}
		
		// 왼쪽 -> 오른쪽 방향으로 하나씩 다 구슬 쏨.
		for (int j = 0; j < W; j++) {
			// 처음 맵 복사
			int[][] tmp = new int[H][W];
			deepCopy(tmp, map);
			
			// j번째 줄에서 0이 아닌 숫자가 구슬을 맞은 시작점.
			for (int i = 0; i < H; i++) {
				if (tmp[i][j] != 0) {
					// 벽 부수러감.
					boom(tmp, i, j);
					break;
				}
			}
			
			// 빈공간 채우기, 아래로 내림.
			mapDown(tmp);
			// 구슬 쏜 횟수 증가시키고 내린맵 가져감.
			dfs(tmp, count + 1);
		}
	}

	static int cnt;
	// 남은 벽이 몇개인지 확인.
	private static void remainWall(int[][] tmp) {
		cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (tmp[i][j] != 0) {
					cnt++;
				}
			}
		}
//		System.out.println(cnt);

	}

	static Queue<Integer> q;
	// 맵 내려서 빈공간 채우기.
	private static void mapDown(int[][] tmp) {
		// 아래로 내려야하므로 세로 한줄씩 확인 
		for (int y = 0; y < W; y++) {
			// 한줄 담을 큐.
			q = new LinkedList<Integer>();
			
			// 아래부터 차례로 큐에 담으면서 0으로 초기화 시켜줌.
			for (int x = H - 1; x >= 0; x--) {
				q.add(tmp[x][y]);
				tmp[x][y] = 0;
			}
			
			// 맨 아래서 부터 위로 하나씩 채워감.
			int count = H - 1;
			// 큐빌때까지 반복 
			while (!q.isEmpty()) {
				int num = q.poll();
				
				// 큐에 있는 수가 0이 아니면 count번째에 값 넣어줌.
				if (num != 0) {
					tmp[count][y] = num;
					// 넣었으니 그 다음에 넣을 차례..
					count--;
				}
			}

		}

	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	
	// 벽돌 깨기
	private static void boom(int[][] tmp, int x, int y) {
		// 범위 벗어나면 끝
		if (x < 0 || x >= H || y < 0 || y >= W) {
			return;
		}
		
		int num = tmp[x][y];
		// 벽이면 0으로 벽부숨.
		if (num != 0) {
			tmp[x][y] = 0;
		}
		
		// 주변 벽 부수기.벽에 있는 숫자-1 만큼 상하좌우 다 벽부숴짐.
		// 아래랑 같은 거임.
//		for (int k = 0; k < num; k++) {
//			for (int i = 0; i < 4; i++) {
//				boom(tmp, x + dx[i] * k, y + dy[i] * k);
//			}
//		}
		
		// 주변 벽 부수기. 벽에 있는 숫자-1 만큼 상하좌우 다 벽부숴짐.
		for (int i = 0; i < num; i++) {
            boom(tmp, x + i, y);
            boom(tmp, x - i, y);
            boom(tmp, x, y + i);
            boom(tmp, x, y - i);
        }
		
	}
	
	// 맵 복사
	private static void deepCopy(int[][] tmp, int[][] map) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				tmp[i][j] = map[i][j];
			}
		}

	}
}