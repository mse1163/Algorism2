import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 무난한 bfs문제.
// 종료조건에서 조금 헤맷음..
public class Solution탈주범검거 {
	static class Point {
		int x, y, loc, cnt;

		public Point(int x, int y, int loc, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.loc = loc;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", loc=" + loc + ", cnt=" + cnt + "]";
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		StringTokenizer token;
		for (int t = 1; t <= T; t++) {
			token = new StringTokenizer(br.readLine());

			N = Integer.parseInt(token.nextToken());
			M = Integer.parseInt(token.nextToken());
			int R = Integer.parseInt(token.nextToken());
			int C = Integer.parseInt(token.nextToken());
			L = Integer.parseInt(token.nextToken());

			map = new int[N][M];

			for (int i = 0; i < N; i++) {
				token = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
				}
			}
			
			// 시작위치 
			q.add(new Point(R, C, map[R][C], 1));
			// 방문체크 배열
			sel = new int[N][M];
			// 시작점 방문체크
			sel[R][C]=-1;
			// 시작부터 1개이므로 초기값=1
			ans=1;

			// 만약 시간이 1이면 바로 끝. 아니면 도둑이 도망감.
			if(L!=1) {
				move(map, 0);
			}
			q.clear();
			bw.write("#"+t+" "+ans+"\n");
		}
		bw.flush();
		bw.close();
	}

	static int N, M, L, ans;
	static int[][] map, sel;
	static Queue<Point> q = new LinkedList<>();

	private static void move(int[][] map, int time) {
		
			while (!q.isEmpty()) {
				
				Point node = q.poll();
				int x = node.x;
				int y = node.y;
				int loc = node.loc;
				int cnt = node.cnt;
				
				// 만약 퍼진 시간이 L이면 끝.
				if(cnt==L) {
					continue;
				}
				
				if(loc==1) {
					up(x,y,cnt);
					down(x,y,cnt);
					left(x,y,cnt);
					right(x,y,cnt);
				}
				else if(loc==2) {
					up(x,y,cnt);
					down(x,y,cnt);
				}
				else if(loc==3) {
					left(x,y,cnt);
					right(x,y,cnt);
				}
				else if(loc==4) {
					up(x,y,cnt);
					right(x,y,cnt);
				}
				else if(loc==5) {
					down(x,y,cnt);
					right(x,y,cnt);
				}
				else if(loc==6) {
					down(x,y,cnt);
					left(x,y,cnt);
				}
				else if(loc==7) {
					up(x,y,cnt);
					left(x,y,cnt);
				}
				
		}
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(sel[i][j]+" ");
//			}
//			System.out.println();
//		}
		
	}
	
	// 위
	private static void up(int x, int y, int cnt) {
		x -= 1;
		
		// 범위 벗어나거나 방문했으면 끝.
		if(x<0 || sel[x][y]!=0) {
			return;
		}
		
		// 위로 갔을경우 이어서 갈 수 있는 배관번호.
		if(map[x][y]==1 || map[x][y]==2 || map[x][y]==5 || map[x][y]==6) {
			q.add(new Point(x, y, map[x][y], cnt+1));
			sel[x][y] = cnt;
			ans++;
		}
		
	}
	
	// 아래
	private static void down(int x, int y, int cnt) {
		x += 1;
		
		if(x>=N || sel[x][y]!=0) {
			return;
		}
		
		if(map[x][y]==1 || map[x][y]==2 || map[x][y]==4 || map[x][y]==7) {
			q.add(new Point(x, y, map[x][y], cnt+1));
			sel[x][y] = cnt;
			ans++;
		}
		
	}
	
	// 왼쪽
	private static void left(int x, int y, int cnt) {
		y -= 1;
		
		if(y<0 || sel[x][y]!=0) {
			return;
		}
		
		if(map[x][y]==1 || map[x][y]==3 || map[x][y]==4 || map[x][y]==5) {
			q.add(new Point(x, y, map[x][y], cnt+1));
			sel[x][y] = cnt;
			ans++;
		}
	}

	//오른쪽
	private static void right(int x, int y, int cnt) {
		y += 1;
		
		if(y>=M || sel[x][y]!=0) {
			return;
		}
		
		if(map[x][y]==1 || map[x][y]==3 || map[x][y]==6 || map[x][y]==7) {
			q.add(new Point(x, y, map[x][y], cnt+1));
			sel[x][y] = cnt;
			ans++;
		}
	}
}
