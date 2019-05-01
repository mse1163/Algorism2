import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
//View
public class Solution1206 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int t=1; t<=10; t++) {
			int N = Integer.parseInt(br.readLine());
			
			int[] building = new int[N];
			
			StringTokenizer token = new StringTokenizer(br.readLine());
			// 높이를 일단 다 배열에 담음
			for(int i=0; i<N; i++) {
				
				building[i] = Integer.parseInt(token.nextToken());
				
			}
			
			int cnt=0;
			// 현재 위치를 기준으로 전,후 2칸씩 비교하여 나보다 작은 높이이면 전망이 좋은거임.
			// 전망 좋으면 그중 몇개층이 그런지 세야하므로 나보다 낮은 높이중 가장 큰수를 빼면 그 숫자만큼 전망 좋은집을 알수 있다.
			for(int i=2; i<N-2; i++) {
				int min=987654321;
				if(building[i-2]<building[i] && building[i-1] < building[i] && building[i+2]< building[i] && building[i+1]< building[i]) {
					min = Math.min(building[i]-building[i-2],min);
					min = Math.min(building[i]-building[i-1],min);
					min = Math.min(building[i]-building[i+2],min);
					min = Math.min(building[i]-building[i+1],min);
					
					cnt += min;
					
				}
			}
			
			System.out.println("#"+t+" "+cnt);
			
		}
	}

}
