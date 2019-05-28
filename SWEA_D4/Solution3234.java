import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
// 양팔 저울
public class Solution3234 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			StringTokenizer token = new StringTokenizer(br.readLine());

			int[] w = new int[N];
			for (int i = 0; i < N; i++) {
				w[i] = Integer.parseInt(token.nextToken());

			}

			cnt=0;
			perm(w, 0);
			sb.append("#").append(t).append(" ").append(cnt).append("\n");

		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static int N,cnt;
	
	// 순열로 모든 경우의 수 뽑음
	static void perm(int[] w, int cnt) {
		if (cnt == N) {
//			System.out.println(Arrays.toString(w));

			int[] weight = new int[N];
			weight = w;

			cal(weight,0,0,0);
			return;
		}

		for (int i = cnt; i < N; i++) {
			swap(w, i, cnt);
			perm(w, cnt + 1);
			swap(w, i, cnt);
		}
	}
	
	// 왼쪽, 오른쪽 조건 봐서 더함
	static void cal(int[] weight, int idx, int left, int right) {
		if(idx==N) {
			cnt++;
			return;
		}
		
		// 왼쪽에 무게는 무조건 더해줘도 됨
		cal(weight, idx+1, left+weight[idx], right);
		
		// 왼쪽 값 보다 오른쪽에 더한값이 더 작으면 오른쪽에 값을 더해줌.
		if(left>=right+weight[idx]) {
			cal(weight, idx+1, left, right+weight[idx]);
		}
	}
	
	static void swap(int[] w, int a, int b) {
		int tmp = w[a];
		w[a] = w[b];
		w[b] = tmp;
	}

}
