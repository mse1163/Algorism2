import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// N(3 ≤ N ≤ 32) 이므로 모든 가능한 경우의 수가 10자리수가 넘음.. 그래서 long으로 카운트 세줘야함.
// DP로 해결...
public class Main파이프옮기기2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}

		cnt = 0;
		// 해당 위치에 어느방향으로 들어왔는지 체크
		sel = new long[N][N][3];
		move();
		System.out.println(cnt);
	}

	static int N;
	static long cnt;
	static int[][] map;
	static long[][][] sel;

	static void move() {
		// 시작점 체크.
		sel[0][1][0]=1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 오른쪽, 범위 안에 있고 갈 수 있으면
				if (j+1 < N && map[i][j + 1] == 0) {
					right(i, j);
				} 
				// 아래쪽
				if (i+1 < N && map[i + 1][j] == 0) {
					down(i, j);
				}
				// 대각선
				if (i+1<N && j+1<N &&map[i][j + 1] == 0 && map[i+1][j] == 0 && map[i + 1][j + 1] == 0) {
					deagac(i, j);
				}
			}
		}
		
		cnt = sel[N-1][N-1][0]+sel[N-1][N-1][1]+sel[N-1][N-1][2];
	}

	// 오른쪽이동
	private static void right(int x, int y) {
		// 현재위치에서 오른쪽, 대각 방향으로 들어온 갯수를 오른쪽으로 이동할때 더해줌.
		sel[x][y + 1][0] += sel[x][y][0] + sel[x][y][2];
	}

	// 아래 이동
	private static void down(int x, int y) {
		// 현재위치에서 아래쪽, 대각 방향으로 들어온 갯수를 아래쪽으로 이동할때 더해줌.
		sel[x + 1][y][1] += sel[x][y][1] + sel[x][y][2];

	}

	// 대각 이동
	private static void deagac(int x, int y) {
		// 현재위치에서 오른쪽,아래쪽, 대각 방향으로 들어온 갯수를 대각선으로 이동할때 더해줌.
		sel[x + 1][y + 1][2] += sel[x][y][0] + sel[x][y][1] + sel[x][y][2];
	}

}
