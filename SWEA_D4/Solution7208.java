import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
// 지도 칠하기
public class Solution7208 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			
			StringTokenizer token = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(token.nextToken());
			}
			
			map = new int[N][N];
			sel = new boolean[N];
			for(int i=0; i<N; i++) {
				token = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
				}
			}
			
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
			
			cnt=0;
			dfs(0);
			sb.append("#").append(t).append(" ").append(cnt).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}
	
	static int[][] map;
	static int[] arr;
	static boolean[] sel;
	static int N, cnt;

	// 정점, 간선 문제에서 자신을 제외한 3가지색으로 바꿔서 비교하는 조건만 추가함
	static void dfs(int v) {
		
		sel[v] = true;
		
		for(int i=0; i<N; i++) {
			
			if(map[v][i]==1 && !sel[i]) {
				sel[i] = true;
				if(arr[v]==arr[i]) {
					// 색이 같으면 바꿔야 되므로 cnt++; 
					cnt++;
					// 1~4색깔 중 자기를 뺀 다른 색으로 바꿔 비교
					for(int j=0; j<4; j++) {
						if(arr[i]!=j) {
							arr[i]=j;
							dfs(i);
						}
					}
				}
				// 색이 다르면 다음으로 넘어가서 비교
				else {
					dfs(i);
					
				}
			}
		}		
	}
}
