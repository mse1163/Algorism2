import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
// 보충학습과 평균
public class Solution3314 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			
			int[] student = new int[5];
			StringTokenizer token = new StringTokenizer(br.readLine());
			
			int sum=0;
			for(int i=0; i<5; i++) {
				student[i] = Integer.parseInt(token.nextToken());
				
				// 학생 점수중 40점 미만이면 무조건 보충을 듣고 40점을 받으므로
				// 40점 미만은 40점으로 값을 바꿔 넣어줌.
				if(student[i]<40) {
					student[i] = 40;
				}
				
				sum += student[i];
			}
			
			// 그이후 평균 구하면 됨.
			int result = sum/5;
			
			System.out.println("#"+t+" "+result);
			
			
		}

	}

}
