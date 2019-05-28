import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
// 콩심기
public class Solution4301 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {

			StringTokenizer token = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(token.nextToken());
			int M = Integer.parseInt(token.nextToken());
			
			int[][] map = new int[N][M];
			int[] dx = {-2,2,0,0};
			int[] dy = {0,0,-2,2};
			
			int cnt=0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j]==0) {
						map[i][j]=1;	// 콩 놓은위치
						cnt++;
						
						int x = i;
						int y = j;
						for(int k=0; k<4; k++) {
							int nx = x + dx[k];
							int ny = y + dy[k];
							
							if(nx<0 || ny<0 || nx>=N || ny>=M) {
								continue;
							}
							
							if(map[nx][ny]==0) {
								map[nx][ny]=2; // 콩 놓으면 안되는 위치
							}
						}
						
					}
					
				}
			}
			
			sb.append("#").append(t).append(" ").append(cnt).append("\n");

		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
