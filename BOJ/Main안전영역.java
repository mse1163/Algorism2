import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
// 안전영역
// k만큼 빗물채울때 k에 = 안들어가서 틀렷음..
// map[i][j]<k 했다가 틀림..
// 전형적인 dfs,bfs로 구역구하는 문제임..
public class Main2468 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		
		// 맵에서 최대값 뽑아서 최댓값만큼 반복돌림.
		int max_num = -1;
		for(int i=0; i<N; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(token.nextToken());
				max_num = Math.max(max_num, map[i][j]);
			}
		}

//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		
//		System.out.println(max_num);
		
		max=-1;
		// 최대값만큼 반복 돌면서 하나씩 빗물 확인함.
		for(int i=0; i<max_num; i++) {
			rain(map,i);
		}
		
		bw.write(max+"");
		bw.flush();
		bw.close();
	}
	
	static int N,max;
	static void rain(int[][] map, int k) {
		int[][] sel = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// k이하므로 <=으로 해야함.
                // map[i][j]<k 했다가 틀림..
				if(map[i][j]<=k) {
					sel[i][j]=1;
				}
			}
		}
		
		// 구역 몇개인지 세기
		int cnt=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(sel[i][j]==0) {
					sel[i][j]=1;
					dfs(sel,i,j);
					cnt++;
				}
			}
		}
		
		// 센 구역 중 최대값이 정답
		max = Math.max(cnt, max);
	}
	
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	
	// 구역 세기
	static void dfs(int[][] sel, int x, int y) {
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
		
			if(nx<0 || nx>=N || ny<0 || ny>=N) {
				continue;
			}
			
			if(sel[nx][ny]==0) {
				sel[nx][ny]=3;
				dfs(sel, nx, ny);
			}
		}
	}

}
