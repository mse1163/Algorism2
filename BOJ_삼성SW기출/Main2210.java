import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main2210 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		String[][] map = new String[5][5];
		
		for(int i=0; i<5; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				map[i][j] = token.nextToken();
			}
		}

//		for(int i=0; i<5; i++) {
//			for(int j=0; j<5; j++) {
//				System.out.print(map[i][j]+" "); 
//			}
//			System.out.println();
//		}
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				dfs(map, i,j, 0, "");
			}
		}
		//System.out.println(hs.toString());
		
		System.out.println(hs.size());
		
	}
	
	static HashSet<String> hs = new HashSet<>();
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	
	static void dfs(String[][] map,int x, int y, int cnt, String route) {
		// 상하좌우 반복돌면서 각각 		
		if(cnt==6) {
			//System.out.println(route);
			hs.add(route);
			
			return;
		}	
		
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(nx<0 || ny<0 || nx>=5 || ny>=5) {
				continue;
			}
			
			dfs(map, nx, ny, cnt+1, route+map[x][y]);
			
		}
		
	}
}
