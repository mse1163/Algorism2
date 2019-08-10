import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// perm과 swap 이용해서 순열 돌려서 구하면 시간초과남..
// 백트래킹 이용해서 순열 구함.. -> 좀 더 공부해야함. 어려움..
public class Solution숫자만들기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		StringTokenizer token;

		for (int t = 1; t <= T; t++) {

			N = Integer.parseInt(br.readLine());
			int[] operator = new int[4];

			token = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				operator[i] = Integer.parseInt(token.nextToken());
			}
//			System.out.println(Arrays.toString(operator));

			int[] oper = new int[N-1];

			int[] num = new int[N];
			token = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(token.nextToken());
			}

			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;

			dfs(operator, num,oper, 0);
			ans = max - min;
			bw.write("#" + t + " " + ans + "\n");
		}
		bw.flush();
		bw.close();
	}

	static int ans, max, min, N;
	// 백트래킹으로 순열 구함.
	static void dfs(int[] operator, int[] num, int[] oper, int idx) {
		if (idx == N - 1) {
			int sum = num[0];

			for (int i = 0; i < N - 1; i++) {
				if (oper[i] == 0) {
					sum += num[i + 1];
				} 
				else if (oper[i] == 1) {
					sum -= num[i + 1];
				} 
				else if (oper[i] == 2) {
					sum *= num[i + 1];
				} 
				else if (oper[i] == 3) {
					sum /= num[i + 1];
					
				}
			}

			min = Math.min(min, sum);
			max = Math.max(max, sum);

			return;
		}
		
		// 연산자4가지 다 돌려보면서 사용할 수 있는 연산자 있으면 사용
		// 사용하고 다시 돌려놓음.
		for (int i = 0; i < 4; i++) {
			if(operator[i]>0) {
				oper[idx] = i;
				operator[i]--;
				dfs(operator,num,oper,idx+1);
				operator[i]++;
			}
		}
	}
}
