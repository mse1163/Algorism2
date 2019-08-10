import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// 1. 안바꾸고 그냥 지나감 2. A로 바꿈 3. B로 바꿈
// 3가지 경우 모두 확인해줘야 하는 문제
// 저 3가지 경우를 생각하는게 어려웠음..
// 합격기준 확인 후 다시 A나 B로 바꾸고 다시 확인해야하는 과정을 재귀로 돌리는게 어려웟음.
public class Solution보호필름 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		StringTokenizer token;
		for (int t = 1; t <= T; t++) {
			token = new StringTokenizer(br.readLine());

			N = Integer.parseInt(token.nextToken());
			M = Integer.parseInt(token.nextToken());
			K = Integer.parseInt(token.nextToken());

			int[][] map = new int[N][M];

			for (int i = 0; i < N; i++) {
				token = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());

				}
			}
			
      // 만약 합격기준이 1이면 전부 통과이므로 바로 0출력하고 끝.
			if(K==1) {
				bw.write("#"+t+" "+0+"\n");
			}
			else {
				ans = 987654321;
				input(map, 0, 0);
				bw.write("#"+t+" "+ans+"\n");
			}
		}
		bw.flush();
		bw.close();

	}

	static int N, M, K, ans;

	private static void input(int[][] map, int cnt, int x) {
		
		if(x==N) {
			// 합격기준 통과 확인.
			if(pass(map, cnt)) {
				// 통과 했으면 cnt 최소값 저장.
				ans = Math.min(ans, cnt); 
			}
			return;
		}

		// 최소값보다 크면 볼 필요 없이 리턴.
		if (ans <= cnt) {
			return;
		}
		
		// 안바꾸고 그냥 지나감.
		input(map, cnt, x+1);
		
		// 바꾸기 전에 값 저장해둠.
		int[] tmp = new int[M];
		for(int j=0; j<M; j++) {
			tmp[j] = map[x][j];
		}
		
		// A약품 투입.
		for (int j = 0; j < M; j++) {
			map[x][j] = 0;
		}
		
		// 약품 투입했으므로 cnt+1해줌. 
		input(map, cnt + 1, x+1);
		
		// B약품 투입
		for (int j = 0; j < M; j++) {
			map[x][j] = 1;
		}

		input(map, cnt + 1, x+1);
		
		// 원래 있던 값으로 되돌려줌.
		map[x] = tmp;
	}
	
	// 합격기준 통과 여부 확인.
	private static boolean pass(int[][] map, int cnt) {
		for (int j = 0; j < M; j++) {
			int same = 1;
			boolean isok = false;
			for (int i = 1; i < N; i++) {
				if (map[i][j] == map[i - 1][j]) {
					same++;
					// 합격기준이면 다음거 확인.
					if (same == K) {
						isok = true;
						break;
					}
				} else {
					same = 1;
				}
			}
			// 만약 합격기준 못미치면 리턴 하고 다음으로 약품투임.
			if (!isok) {
				return false;
			}
		}
		
		// 합격기준 통과 했으면 true로 리턴.
		return true;

	}

}
