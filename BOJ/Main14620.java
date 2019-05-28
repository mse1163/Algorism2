package BOJ;

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
// 꽃길
public class Main14620 {
	static class Point {
		int x, y;

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
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}

		
		
		Point[] flower = new Point[N*N];
		Point[] fsel = new Point[3];
		
		// flower배열에 모든 수를 다 넣어줌
		int k=0;
		for (int i = 0; i < N ; i++) {
			for (int j = 0; j < N; j++) {
				
				flower[k] = new Point(i, j);
				
				k++;
			}
		}
		
		ans=987654321;
		combi(flower, fsel, 0, 1);
		System.out.println(ans);
	}

	static int N, sum, ans;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static List<Point> list;
	
	// flower에 있는 모든 수를 돌면서 3개씩 조합으로 뽑아냄
	static void combi(Point[] flower, Point[] fsel, int cnt, int idx) {
		
		if(cnt==3) {
			//3개 뽑은 수를 q에 담아서 꽃잎을 펼친다.
			Queue<Point> q  = new LinkedList<Point>();
			for(int i=0; i<3; i++) {
				q.add(fsel[i]);
			}
			
			// 3개 모두 펼칠수 잇는 것 중에서 최소값을 찾아냄.
			if(bfs(q)) {
				ans = Math.min(ans, sum);
			}
			return;
		}
		
		if(idx==flower.length) {
			return;
		}
		
		fsel[cnt] = flower[idx];
		combi(flower, fsel, cnt+1, idx+1);
		combi(flower, fsel, cnt, idx+1);
		
	}
	
	// 꽃잎 펼치기.
	static boolean bfs(Queue<Point> q) {
		sum=0;
		boolean[][] sel = new boolean[N][N];
		while (!q.isEmpty()) {
			
			Point node = q.poll();
			
			int x = node.x;
			int y = node.y;
			
			sum+=map[x][y];
			sel[x][y]=true;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				// 꽃잎을 다 못펼치면 바로 리턴하고 끝냄.
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || sel[nx][ny]) {
					return false;
				}

				sel[nx][ny] = true;
				sum += map[nx][ny];

			}
		}
		return true;
	}

}
