import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution6730 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			
			int N = Integer.parseInt(br.readLine());
			
			int[] arr = new int[N];
			
			StringTokenizer token = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(token.nextToken());
			}
			
			int max = 0;
			int min = 0;
			for(int i=0; i<N-1; i++) {
				int n = arr[i]- arr[i+1];
				
				// up
				if(n>0) {	
					max = Math.max(max, n);
				}
				// down
				else if(n<0) {
					n = Math.abs(n);
					min = Math.max(min, n);
				}
				else {
					continue;
				}
				
			}
			sb.append("#").append(t).append(" ").append(min).append(" ").append(max).append("\n");
			
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
				
	}

}
