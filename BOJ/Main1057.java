package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
// 토너먼트
public class Main1057 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer token = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(token.nextToken());
		int a = Integer.parseInt(token.nextToken());
		int b = Integer.parseInt(token.nextToken());

		int cnt = 1;
		
		// 토너먼트로 2명씩 경기하므로 본인이 가지고 있는 번호를 2로 계속 나눠줌
		// 단, 번호가 홀수인 경우는 +1을 더해 짝수를 만들어주고 나눔
		// a,b가 같아지면 끝
		while (true) {
			if (a % 2 == 0 && b % 2 == 0) {
				a = a / 2;
				b = b / 2;
			} else if (a % 2 == 1 && b % 2 == 1) {
				a++;
				a = a / 2;
				b++;
				b = b / 2;
			}

			else if (a % 2 == 1 && b % 2 == 0) {
				a++;
				a = a / 2;
				b = b / 2;
			} else if (a % 2 == 0 && b % 2 == 1) {

				a = a / 2;
				b++;
				b = b / 2;
			}

			if (a != b) {
				cnt++;
			} else {
				break;
			}
		}

		sb.append(cnt);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
