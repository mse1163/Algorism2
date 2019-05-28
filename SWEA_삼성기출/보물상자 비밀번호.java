import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution5658 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(token.nextToken()); // 숫자의 개수
			int K = Integer.parseInt(token.nextToken()); // 크기 순서

			String str = br.readLine();
			List<Character> list = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				list.add(str.charAt(i));
			}
			
			// 16진수 문자열 담기
			List<Integer> result = new LinkedList<>();
			for (int n = 0; n < N/4; n++) {
				for (int i = 0; i < N; i += N/4) {
					// 입력받은 문자열을 N/4만큼씩 잘라서 넣음
					String text = "";
					for (int j = 0; j < N/4; j++) {
						text += list.get(i + j) + "";
					}
					// 자른 문자열(16진수)을 10진수로 바꿔서 중복안되게 result 리스트에 넣어줌
					int num = Integer.parseInt(text, 16);
					// 중복 여부 확인
					if(!result.contains(num)) {
						result.add(num);
					}
				}
				// 입력받은 문자열을 돌림 (한칸씩 뒤로 보냄 -> 맨 뒤에거는 맨 앞으로 옴)
				list.add(0,list.get(N-1));
				list.remove(N);
			}
			
			// 내림차순 정렬 => 오름차순 정렬 후 뒤집음...
			Collections.sort(result);
			Collections.reverse(result);
			//System.out.println(result.toString());
			
			// K번째 출력-> 0부터 입력받아서 k-1을 해줌
			sb.append("#").append(t).append(" ").append(result.get(K-1)).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
