package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
// 공
public class Main1547_1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		int ball = 1;

		for (int i = 0; i < N; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());

			// 바꿀 숫자들
			int x = Integer.parseInt(token.nextToken());
			int y = Integer.parseInt(token.nextToken());
			
			// 입력받은 숫자중 ball이 있으면 ball을 바꿔줌
			if (ball == x) {
				ball = y;
			} else if (ball == y) {
				ball = x;
			} else {
				continue;
			}
		}
		System.out.println(ball);

	}

}
