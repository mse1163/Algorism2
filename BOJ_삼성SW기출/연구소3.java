import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
// 조합으로 잘뽑아내서 bfs로 퍼치기만 하면 되므로 생각보다 쉬운 문제...
public class Main2328 {
	static class Point{
		int x,y,cnt;

		public Point(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", cnt=" + cnt + "]";
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		int[][] map = new int[N][N];
		List<Point> list = new ArrayList<>();
		
		boolean zero = false;
		for(int i=0; i<N; i++) {
			token = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
				if(map[i][j]==2) {
					list.add(new Point(i, j, 1));
				
				}
				
				else if(map[i][j]==0) {
					zero=true;
				}
			}
		}
		
		// 이미 바이러스 다 퍼져있음. 이동 공간 없음.
		if(!zero) {
			bw.write(0+"");
			bw.flush();
			bw.close();
			return;
		}
		
		Point[] virus = new Point[M];
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		ans = 987654321;
		combi(map,list, virus,0,0);
		
		// 바이러스 다 안퍼짐
		if(ans==987654321) {
			bw.write(-1+"");
		}
		// 바이러스 다 퍼짐
		else {
			bw.write(ans+"");
		}
		bw.flush();
		bw.close();
		
		
	}
	
	static int N,M,ans;
	static Queue<Point> q = new LinkedList<>();
	static int[] dx={-1,1,0,0};
	static int[] dy={0,0,-1,1};
	
	// 조합으로 M개 바이러스 뽑음
	static void combi(int[][] map, List<Point> list,Point[] virus, int cnt, int idx) {
		if(cnt==M) {
//			System.out.println(Arrays.toString(virus));
			q.clear();
			for(int i=0; i<M; i++) {
				q.add(virus[i]);
			}
			
			move(map);
			
			return;
		}
		
		if(idx==list.size()) {
			return;
		}
		
		virus[cnt] = list.get(idx);
		combi(map,list, virus, cnt+1, idx+1);
		combi(map,list, virus, cnt, idx+1);
		
	}
	// 바이러스 퍼짐
	static void move(int[][] map) {
		// 퍼지는 시간, 방문체크 
		int[][] sel = new int[N][N];
		
		while(!q.isEmpty()) {
			
			Point node = q.poll();
			int x = node.x;
			int y = node.y;
			int cnt = node.cnt;
			
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx<0 || ny<0 || nx>=N || ny>=N || map[nx][ny]==1 || sel[nx][ny]!=0) {
					continue;
				}
				// 바이러스는 시간표시는 안해주지만 바이러스 지나갈때도 시간이 흐르므로 그거는 큐에 담아줌.
				if(map[nx][ny]==2 && sel[nx][ny]==0) {
					sel[nx][ny] = -1;
					q.add(new Point(nx, ny, cnt+1));
				}
				else if(map[nx][ny]==0 && sel[nx][ny]==0) {
					sel[nx][ny]=cnt;
					q.add(new Point(nx, ny, cnt+1));
				}
			}
			
		}
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(sel[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		boolean isok = false;
		int result=-1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				// 바이러스 안퍼진곳 있으면 끝
				if(map[i][j]==0 && sel[i][j]==0) {
					isok = true;
					break;
				}
				// 바이러스 퍼진곳중 가장 큰값이 걸린 시간임.
				else if(map[i][j]==0 && sel[i][j]!=0){
					result = Math.max(result, sel[i][j]);
				}
			}
		}
		
		// 다 퍼진경우 최소시간 찾기
		if(!isok) {
//			System.out.println(result);
			ans = Math.min(result,ans);
		}
		
	}
}
