import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
// 미세먼지!
// 처음에 맵복사해서 먼지 퍼지는거 하고 그랬는데 이렇게 하니 시간터짐..
// 굳이 복사할 필요없었음..
// 공청기 앞에 0으로 해주는거 못해서 틀림..

public class Main17144 {
	static class Point{
		int x,y,num;

		public Point(int x, int y, int num) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", num=" + num + "]";
		}
		
		
	}
	static class Air{
		int x,y;
		public Air(int x, int y) {
			super();
			this.x = x;
			this.y = y;
			
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		int T = Integer.parseInt(token.nextToken());
		
		int[][] map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			token = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
				if(map[i][j]!=0 && map[i][j]!=-1) {
					q.add(new Point(i, j, map[i][j]));
				}
				else if(map[i][j]==-1) {
					air.add(new Air(i, j));
				}
			}
		}
		
		ans=0;
		// T만큼 돌기
		for(int k=0; k<T; k++) {
			
			spread(map);
			
			q.clear();
			check(map);
			
//			System.out.println("------------새로운맵--------------");
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<M; j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			
		}
		
		// 미세먼지 값 세기
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]!=0 && map[i][j]!=-1) {
					ans += map[i][j];
				}
			}
		}
		
		bw.write(ans+"");
		bw.flush();
		bw.close();

	}
	
	static int N,M,ans;
	static int[][] copy;
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	static Queue<Point> q = new LinkedList<>();
	static List<Air> air = new ArrayList<>();
	static void spread(int[][] map) {
		
		while(!q.isEmpty()){
			
			Point node = q.poll();
			int x = node.x;
			int y = node.y;
			int num = node.num;
			
			int cnt=0;
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				// 범위 벗어나거나 공청기이면 먼지 못 퍼짐
				if(nx<0 || nx>=N || ny<0 || ny>=M || map[nx][ny]==-1) {
					continue;
				}
				
				// 먼지 퍼짐.
				map[nx][ny] += num/5;
				cnt++;
				
			}
			// 먼지 퍼진정도만큼 원래 값에서 빼줌.
			map[x][y] -= (num/5)*cnt;
		}

//		System.out.println("------------퍼짐-------------------");
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<M; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		// 공기 순환
		rotation(map);
		
	}
	
	static void rotation(int[][] map) {
		
		// 공청기 위
		int upx = air.get(0).x;
		int upy = air.get(0).y;
		
		// 공청기 아래
		int downx = air.get(1).x;
		int downy = air.get(1).y;
		
		for(int i=upx+1; i>0; i--) {
			map[i][0] = map[i-1][0];
		}
		
		for(int i=0; i<M-1; i++) {
			map[0][i] = map[0][i+1];
		}
		
		for(int i=0; i<upx; i++) {
			map[i][M-1] = map[i+1][M-1];
		}
		
		for(int i=downx; i<N-1; i++) {
			map[i][0] = map[i+1][0];
		}
		
		for(int i=0; i<M-1; i++) {
			map[N-1][i] = map[N-1][i+1];
		}
		
		for(int i=N-1; i>downx; i--) {
			map[i][M-1] = map[i-1][M-1];
		}
		
		for(int i=M-1; i>upy; i--) {
			map[upx][i] = map[upx][i-1];
			map[downx][i] = map[downx][i-1];
		}
		
		// 공기청정기 위치 적어줌..
		map[upx][upy] = -1;
		map[downx][downy]=-1;
		
		// 순회할때 무조건 공기청정기 앞은 0이므로 입력해줌. 안해주면 틀림
		map[upx][upy+1] = 0;
		map[downx][downy+1]=0;
	}
	
	// 먼지 리스트에 담기
	static void check(int[][] map) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]!=0 && map[i][j]!=-1) {
					q.add(new Point(i, j, map[i][j]));
				}
			}
		}
	}

}
