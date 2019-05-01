import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
	// 준홍이의 카드놀이
public class Solution7102 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(token.nextToken());
			int M = Integer.parseInt(token.nextToken());

			int[] cnt = new int[N + M + 1];
			
			// 합으로 나올수 있는 모든 수 표시
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < M + 1; j++) {
					cnt[i + j]++;
				}
			}
			
//			System.out.println(Arrays.toString(cnt));
			
			// 가장 많이 나온 수 담을 리스트
			List<Integer> list = new ArrayList<>();
			int max = -1;
			for(int i=2; i<cnt.length; i++) {
				if(cnt[i]>max) {
					max = cnt[i];
					// 최대값이 새로 바뀌면 원래있던 리스트 비워주고 새로 그 숫자를 넣음
					list.clear();
					list.add(i);
				}
				else if(cnt[i]==max) {
					// 최대값이랑 같으면 리스트에 추가
					list.add(i);
				}
			}
			
			System.out.print("#"+t+" ");
			for(int i=0; i<list.size(); i++) {
				System.out.print(list.get(i)+" ");
			}
			System.out.println();
		}

	}

}
