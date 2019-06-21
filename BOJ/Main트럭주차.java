import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 다소 쉬운 문제.
public class Main트럭주차 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer token = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(token.nextToken());
		int B = Integer.parseInt(token.nextToken());
		int C = Integer.parseInt(token.nextToken());
		
		//주어진 시간 1~100
		int[] arr = new int[101];
		
		for(int i=0; i<3; i++) {
			token = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(token.nextToken());
			int end = Integer.parseInt(token.nextToken());
			
			// 처음 시작시간이 지나야 그 시간에 트럭이 있었다는 것이므로 start+1부터 end까지 계산
			for(int d=start+1; d<=end; d++ ) {
				// 트럭이 주차되어있는 시간대에 몇대 있는지 저장
				arr[d]++;
			}
		}
		
//		System.out.println(Arrays.toString(arr));
		int result = 0;
		// 전체 시간배열 돌면서 주차된 트럭수*값
		for(int i=1; i<101; i++) {
			// 한대도 없었으면 다음으로...
			if(arr[i]==0) {
				continue;
			}
			else if(arr[i]==1) {
				result+=arr[i]*A;
			}
			else if(arr[i]==2) {
				result+=arr[i]*B;
			}
			else if(arr[i]==3) {
				result += arr[i]*C;
			}
			
		}
		// 결과 출력
		System.out.println(result);
	}

}
