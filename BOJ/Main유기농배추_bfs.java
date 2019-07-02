import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Point{
		int x,y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
        // 흰지렁이 갯수
		int cnt;
		
		for(int t=1; t<=T; t++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			M = Integer.parseInt(token.nextToken());    // 가로
			N = Integer.parseInt(token.nextToken());    // 세로
			int K = Integer.parseInt(token.nextToken());    // 배추 갯수
			
			map = new int[N][M]; 
            // 배추심기   
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
                    //배추구역 시작점
					if(map[i][j]==1) {
                        // 지렁이 갯수++
						cnt++;
						q.add(new Point(i,j));
                        // 배추 방문표시
						map[i][j]=2;
						bfs();
					}
				}
			}
			
			bw.write(cnt+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	static int N,M;
	static Queue<Point> q = new LinkedList<>();
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] map;
	
    //상하좌우 배추 있는지 확인
	static void bfs() {
		
		while(!q.isEmpty()) {
			Point node = q.poll();
			
			int x = node.x;
			int y = node.y;
			
			for(int i=0; i<4; i++) {
				int nx= x + dx[i];
				int ny= y + dy[i];
				
				if(nx<0 || ny<0 || nx>=N || ny>=M) {
					continue;
				}
				
                //배추 있으면 방문체크.
				if(map[nx][ny]==1) {
					map[nx][ny]=2;
					q.add(new Point(nx, ny));
				}
			}
			
		}
	}

}
