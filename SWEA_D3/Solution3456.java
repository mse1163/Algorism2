import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
// 직사각형 길이 찾기
public class Solution3456 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			
			// 숫자가 1~100
			int[] num = new int[101];
			
			StringTokenizer token = new StringTokenizer(br.readLine());
			for(int i=0; i<3; i++) {
				int n = Integer.parseInt(token.nextToken());
				// 입력받은 숫자 담음
				num[n]++;
			}
			
			for(int i=1; i<101; i++) {
				// 만약 입력받은것 중 똑같은 숫자가 없으면 이게 정답
				if(num[i]==1) {
					System.out.println("#"+t+" "+i);
					break;
				}
				// 모두 같은 수로 들어왔을 경우
				else if(num[i]==3) {
					System.out.println("#"+t+" "+i);
					break;
				}
			}
			
			
		}
	}

}
