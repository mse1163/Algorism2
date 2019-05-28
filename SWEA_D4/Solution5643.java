import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 키 순서
public class Solution5643 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= T; t++) {

			N = Integer.parseInt(br.readLine().trim());
			M = Integer.parseInt(br.readLine().trim());

			map = new int[N + 1][N + 1];

			for (int i = 0; i < M; i++) {
				StringTokenizer token = new StringTokenizer(br.readLine().trim());
				int a = Integer.parseInt(token.nextToken());
				int b = Integer.parseInt(token.nextToken());

				// 나보다 큰값
				map[a][b] = 1;	
				// 나보다 작은값
				map[b][a] = 2;
			}
//
//			for (int i = 1; i < N + 1; i++) {
//				for (int j = 1; j < N + 1; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			max = 0;
			for (int i = 1; i < N + 1; i++) {
				bfs(i);
			}

			sb.append("#").append(t).append(" ").append(max).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	

	static int N, M, max;
	static int[][] map;
	static boolean[] sel_big, sel_small;
	static Queue<Integer> q_big = new LinkedList<>();
	static Queue<Integer> q_small = new LinkedList<>();

	static void bfs(int start) {
		sel_big = new boolean[N + 1];
		sel_small = new boolean[N + 1];
		
		q_big.add(start);
		q_small.add(start);
		
		sel_big[start] = true;
		sel_small[start] = true;

		int cnt_big = 0;
		int cnt_small = 0;
		
		// 나보다 큰애 찾기
		while (!q_big.isEmpty()) {

			int v = q_big.poll();

			for (int i = 1; i < N + 1; i++) {
				if (map[v][i] == 1 && !sel_big[i]) {
					sel_big[i] = true;
					q_big.add(i);
					cnt_big ++;
				}
			}
		}
		
		// 나보다 작은애 찾기
		while (!q_small.isEmpty()) {
			int v = q_small.poll();

			for (int i = 1; i < N + 1; i++) {
				if (map[v][i] == 2 && !sel_small[i]) {
					sel_small[i] = true;
					q_small.add(i);
					cnt_small ++;
				}
			}

		}
		
		int result = cnt_big + cnt_small;
		// 나보다 큰애랑 작은애 합이 N-1이면 몇개인지 갯수 셈
		if(result == N-1) {
			max++;
			return;
		}

	}

}
