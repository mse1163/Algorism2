import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// dfs로 하면 시간초과..ㄷㄷ
// DP로 접근해서 시간 줄여 해결..
// 해당위치에 최소이동을 구하는 방법으로 
public class Main무한부스트 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer token = new StringTokenizer(br.readLine());

		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());

		map = new int[N + 1][M + 1];

		for (int i = 1; i < N + 1; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 1; j < M + 1; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}

		ans = 0;
		move();
		System.out.println(ans);
	}

	static int N, M, ans;
	static boolean isok_d, isok_r;
	static int[][] map;

	static void move() {
		// 해당 위치에서 최소값 기억할 배열
		int[][] sel = new int[N + 1][M + 1];
		
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < M + 1; j++) {
				// 최대 k칸 이동(1~k칸)
				for (int k = 1; k <= map[i][j]; k++) {
					// 아래로 이동, 범위확인.
					if (i + k < N + 1) {
						down(sel,i,j,k);
					}
					// 오른쪽으로 이동, 범위 확인
					if(j + k < M + 1) {
						right(sel, i, j, k);
					}
				}
			}
		}

		ans = sel[N][M];

	}
	
	static void down(int[][] sel, int i, int j, int k) {
		// 이동하려는 칸에 아무것도 안왔으면 해당위치값+1
		if (sel[i + k][j] == 0) {
			sel[i + k][j] = sel[i][j] + 1;
		}
		// 이동하려는 칸과 해당위치 값+1 중에서 작은값 저장.
		else {
			sel[i + k][j] = Math.min(sel[i + k][j], sel[i][j] + 1);
		}
	}
	
	static void right(int[][] sel, int i, int j, int k) {
		if (sel[i][j + k] == 0) {
			sel[i][j + k] = sel[i][j] + 1;
		} else {
			sel[i][j + k] = Math.min(sel[i][j + k], sel[i][j] + 1);
		}
	}
}
