import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {

			int N = Integer.parseInt(br.readLine());

			List<Integer> price = new ArrayList<>();

			StringTokenizer token = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				price.add(Integer.parseInt(token.nextToken()));
			}
            // 리스트에 담아서 내림차순으로 정렬시킴
			Collections.sort(price);
			Collections.reverse(price);
			
			int sum=0;
            // 리스트가 0부터 시작하므로 3번째 인덱스는 2
			int idx = 2;
			for(int i=0; i<price.size(); i++) {
				// 내림차순 정렬되있는 것 중에서 3개씩 끊어서 3번째 있는값을 제거
				if(i==idx) {
					//price.remove(idx);
					idx += 3;
				}
                // 3번째 값 제외한 합
				else {
					sum+= price.get(i);
				}
			}
			sb.append("#").append(t).append(" ").append(sum).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

}
