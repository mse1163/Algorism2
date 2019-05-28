import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution1234 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		for (int t = 1; t <= 2; t++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(token.nextToken());

			String str = token.nextToken();

			// System.out.println(N+", "+ str);

			Stack<Integer> stack = new Stack<>();
			// 스택에 넣으면서 위에있는 숫자랑 같으면 빼고, 다르면 넣어줌
			for (int i = 0; i < str.length(); i++) {
				int num = str.charAt(i) - '0';

				if (stack.isEmpty()) {
					stack.push(num);
				} 
				else {
					if (stack.peek() == num) {
						stack.pop();
					} else {
						stack.push(num);
					}
				}
			}
			
			sb.append("#").append(t).append(" ");
			for(int i=0; i<stack.size(); i++) {
				sb.append(stack.get(i));
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

}
