import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 유기농배추 dfs 방법
public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		int cnt;
		
		for(int t=1; t<=T; t++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			M = Integer.parseInt(token.nextToken());
			N = Integer.parseInt(token.nextToken());
			int K = Integer.parseInt(token.nextToken());
			
			map = new int[N][M];
			for(int i=0; i<K; i++) {
				token = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(token.nextToken());
				int x = Integer.parseInt(token.nextToken());
				
				map[x][y]=1;
			}
			
//			for(int i=0; i<N; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
			
			cnt=0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j]==1) {
						cnt++;
						map[i][j]=2;
						dfs(i,j);
					}
				}
			}
			
			bw.write(cnt+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	static int N,M;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] map;

	static void dfs(int x, int y) {
		for(int i=0; i<4; i++) {
			int nx= x + dx[i];
			int ny= y + dy[i];
			
			if(nx<0 || ny<0 || nx>=N || ny>=M) {
				continue;
			}
			
			if(map[nx][ny]==1) {
				map[nx][ny]=2;
				dfs(nx,ny);
			}
		}
	}

}
