import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
// 농작물 수확
public class Solution2805 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());

			int[][] map = new int[N][N];

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j) - '0';
				}
			}

//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}

			int start = N / 2;
			int end = N / 2;

			int sum = 0;
			// 중심부터 시작해서 시작,끝 점까지 반복 돌면서 더해줌
			for (int i = 0; i <= N / 2; i++) {

				for (int j = start; j <= end; j++) {
					sum += map[i][j];
				}
				if (start != 0 && end != N - 1) {
					start--;
					end++;
				}
			}

			for (int i = N / 2 + 1; i < N; i++) {
				start++;
				end--;
				
				for (int j = start; j <= end; j++) {
					sum += map[i][j];
				}
//				if (start != N - 1 && end != 0) {
//					
//				}
			}

			sb.append("#").append(t).append(" ").append(sum).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

}
