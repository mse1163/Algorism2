import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백트래킹 쓰는 문제 
// 방향 바꾸고 감, 안바꾸고 감 -> 두 방향 
public class Solution디저트카페 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringTokenizer token;
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				token = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
				}
			}
			
			ans=-1;
			for(int x=0; x<N-2; x++) {
				for(int y=1; y<N-1; y++) {
					check = new int[101];
					visited = new boolean[N][N];
					sx=x; sy=y;
					dfs(x,y,0,0);
				}
			}
			
			System.out.println("#"+t+" "+ans);
			
		}
	}
	
	static int N, ans, sx, sy;
	static int[][] map;
	static boolean[][] visited;
	static int[] check;
	
	static void dfs(int x, int y, int loc, int cnt) {
		if(loc==0) {
			// 방향 바꿈
			loc(x+1, y+1, loc+1, cnt+1);
			
		}
		else if(loc==1) {
			// 방향 안바꿈
			loc(x+1, y+1, loc, cnt+1);
			// 방향 바꿈
			loc(x+1, y-1, loc+1, cnt+1);
		}
		else if(loc==2) {
			// 방향 안바꿈
			loc(x+1, y-1, loc, cnt+1);
			// 방향 바꿈
			loc(x-1, y-1, loc+1, cnt+1);
		}
		else if(loc==3) {
			// 방향 안바꿈
			loc(x-1, y-1, loc, cnt+1);
			// 방향 바꿈
			loc(x-1, y+1, loc+1, cnt+1);
		}
		else if(loc==4) {
			// 처음위치 도착
			if(sx==x && sy==y) {
				ans = Math.max(ans, cnt);
				return;
			}
			// 방향 안바꿈
			loc(x-1, y+1, loc, cnt+1);
		}
	}
	
	// 디저트 먹을수 있는지 체크
	static void loc(int x, int y, int loc, int cnt) {
		// 범위확인
		if(x<0 || y<0 || x>=N || y>=N) {
			return;
		}
		
		// 방문아직 안했고, 숫자 중복X
		if(!visited[x][y] && check[map[x][y]]==0) {
			visited[x][y] = true;
			check[map[x][y]]++;
			dfs(x, y, loc, cnt);
			visited[x][y] = false;
			check[map[x][y]]--;
		}
	}
}
