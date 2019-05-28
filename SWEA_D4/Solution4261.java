import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
// 빠른 휴대전화 키패드
public class Solution4261 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		String[] phone = new String[10];
		phone[2] = "abc";
		phone[3] = "def";
		phone[4] = "ghi";
		phone[5] = "jkl";
		phone[6] = "mno";
		phone[7] = "pqrs";
		phone[8] = "tuv";
		phone[9] = "wxyz";

		for (int t = 1; t <= T; t++) {

			StringTokenizer token = new StringTokenizer(br.readLine());

			String S = token.nextToken();
			int N = Integer.parseInt(token.nextToken());

			token = new StringTokenizer(br.readLine());

			int cnt = 0;
			for (int i = 0; i < N; i++) {
				String word = token.nextToken();

				boolean isok = true;
				for (int j = 0; j < S.length(); j++) {
					if (word.charAt(j) == '\0') {
						isok = false;
						break;
					} 
					else {
						int num = S.charAt(j)-'0';
						if (phone[num].contains(word.charAt(j) + "")) {
							continue;
						} else {
							isok = false;
							break;
						}
						
					}
				}

				if (isok) {
					cnt++;
				}

			}

			sb.append("#").append(t).append(" ").append(cnt).append("\n");

		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
