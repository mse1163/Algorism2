import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution보물왕태혁 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			
			int N = Integer.parseInt(br.readLine());
			int[] num = new int[N];
			
			StringTokenizer token = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				// 주어진 약수를 배열에 저장.
				num[i]= Integer.parseInt(token.nextToken());
			}
			
			// 약수를 오름차순 정렬해야 계산하기 편함.
			Arrays.sort(num);
//			System.out.println(Arrays.toString(num));
			
			// 맨앞과 맨뒤 숫자 곱한게 찾고자하는 수가 됨.
			int ans = num[0]*num[N-1];
			
			bw.write("#"+t+" "+ans+"\n");
			
		}
		bw.flush();
		bw.close();

	}

}
