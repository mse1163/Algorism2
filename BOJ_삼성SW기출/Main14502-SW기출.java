import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main14502 {
	static class Point{
		int x,y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
			
		}

		@Override
		public String toString() {
			return x + "," + y ;
		}
		
	}
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		map = new int[N][M];
		max = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			token = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
				if(map[i][j]==2) {
					virus.add(new Point(i, j));
				}
				else if(map[i][j]==0) {
					empty.add(new Point(i, j));
				}
			}
		}
		
		formun();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
			
		}
		
		System.out.println(max);
		
	}
	static int N,M, max;
	static int[][] map;
	static LinkedList<Point> virus = new LinkedList<>();
	static LinkedList<Point> empty = new LinkedList<>();
	static Queue<Point> q = new LinkedList<>();
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static void bfs() {
		boolean[][] visited = new boolean[N][M];
		q.addAll(virus);
		
		while(!q.isEmpty()) {
			
			Point node = q.poll();
			
			int x = node.x;
			int y = node.y;
		
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx<0 || ny<0 || nx>=N || ny>=M) {
					continue;
				}
				
				if(map[nx][ny]==0 && !visited[nx][ny]) {
					visited[nx][ny]=true;
					q.add(new Point(nx, ny));
				}
				
			}
			
		}
		
		int cnt=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visited[i][j]==false && map[i][j]==0)
					cnt++;
			}
		}
		
		max = Math.max(max, cnt);
	}
	static void formun() {
		
		for(int i=0; i<empty.size(); i++) {
			map[empty.get(i).x][empty.get(i).y]=1;
			
			for(int j=i+1; j<empty.size(); j++) {
				map[empty.get(j).x][empty.get(j).y]=1;
				
				for(int k=j+1; k<empty.size(); k++) {
					map[empty.get(k).x][empty.get(k).y]=1;
					bfs();
					map[empty.get(k).x][empty.get(k).y]=0;
				}
				
				map[empty.get(j).x][empty.get(j).y]=0;
			}
			map[empty.get(i).x][empty.get(i).y]=0;
		}
		
	}
}
