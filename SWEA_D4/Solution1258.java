import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution1258 {
	static class Point {
		int x, y, cnt;

		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return x + ", " + y + "," + cnt;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer token = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());

				}
			}
		

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0 && !visited[i][j]) {
					q.add(new Point(i, j, 1));
					map[i][j] = -1;
					visited[i][j] = true;
					bfs(visited);
				}
			}
		}
		
		int cnt=0;
		int result=0;
		arr = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == -1) {
					result++;
					matrix(i, j,cnt++);
					
				}
			}
		}
		
		// 정렬 -> 행*열이 작은 순으로 , 행*열 같으면 행이 작은 순으로
		Arrays.sort(arr, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if(o1[0]*o1[1] == o2[0]*o2[1]) {
					return Integer.compare(o1[0], o2[0]);
				}
				else {
					return Integer.compare(o1[0]*o1[1], o2[0]*o2[1]);
				}
				
			}
		});
		
//		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < 2; j++) {
//				System.out.print(arr[i][j] + " ");
//				
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		sb.append(result).append(" ");
		for(int i=0; i<N; i++) {
			for(int j=0; j<2; j++) {
				if(arr[i][j]!=0)
				sb.append(arr[i][j]).append(" ");
			}
		}
		sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[][] arr;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static Queue<Point> q = new LinkedList<>();
	
	//전체 탐색하면서 숫자 증가해줌
	static void bfs(boolean[][] visited) {

		while (!q.isEmpty()) {
			Point node = q.poll();

			int x = node.x;
			int y = node.y;
			int cnt = node.cnt;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}

				if (map[nx][ny] != 0 && !visited[nx][ny]) {
					map[nx][ny] = cnt;
					visited[nx][ny] = true;
					q.add(new Point(nx, ny, cnt + 1));
				}
			}
		}
	}

	// 행열 크기 구하기
	static void matrix(int sx, int sy, int cnt) {
		int[] lx = { 1, 0 };
		int[] ly = { 0, 1 };
		
			for (int i = 0; i < 2; i++) {
				 int x = sx;
				 int y = sy;
				
				// 행
				if (i == 0) {
					
					while(true) {
						
						x = x + lx[i];
						y = y + ly[i];

						// 0이거나 범위 벗어나면 끝내기, 끝내면서 바로 그 옆에 값을 가져옴
						if( x<0 || y<0 || x>=N || y>=N || map[x][y] == 0 ) {
							// 바로 옆에 값이 -1일때 +1을 해줘도 0이므로 1값을 별도로 지정해줌
							if(map[x-1][y]==-1) {
								arr[cnt][0]=1;
							}
							else {
							arr[cnt][0] = map[x - 1][y] + 1;
							}
							break;
						}

					}
					
					// 열
				} else if (i == 1) {
					while(true) {
						
						x = x + lx[i];
						y = y + ly[i];
						
						// 0이거나 범위 벗어나면 끝내기, 끝내면서 바로 그 옆에 값을 가져옴
						if(x<0 || y<0 || x>=N || y>=N || map[x][y] == 0 ) {
							// 바로 옆에 값이 -1일때 +1을 해줘도 0이므로 1값을 별도로 지정해줌
							if(map[x][y-1]==-1) {
								arr[cnt][1]=1;
							}
							else {
								arr[cnt][1] = map[x][y-1] + 1;	
							}
							break;
						}

					}
				}
			}

	}

}
