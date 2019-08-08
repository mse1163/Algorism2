import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 거리가 가까운 곳에 있는 물고기 조건 걸어 놓은게 까다로움..
// 먹을 수 있는 물고기들 중 골라서 물고기 먹는게 어려웠음.
public class Main아기상어 {
	static class Point {
		int x, y,cnt;

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

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());

				if (map[i][j] == 9) {
					q.add(new Point(i, j, 0));
					map[i][j]=0;
				}
			}
		}

		time = 0;
		move(map,2,0);
		System.out.println(time);

	}

	static int N, time;
	static ArrayList<Point> fish = new ArrayList<>();
	static Queue<Point> q = new LinkedList<>();

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static void move(int[][] map, int size, int eat) {
		int[][] sel = new int[N][N];
		fish.clear();
		while (!q.isEmpty()) {
			Point node = q.poll();
			int x = node.x;
			int y = node.y;
			int cnt = node.cnt;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
					continue;
				}

				// 물고기 이동
				// 방문아직 안했고, 빈곳이거나 크기가 나보다 작거나 같으면 이동가능.
				if (sel[nx][ny] == 0 && (map[nx][ny] == 0 || size >= map[nx][ny])) {
					// 다른 물고기가 있는데 그 물고기가 나보다 크기가 작으면 먹음.
					if (map[nx][ny] != 0 && size > map[nx][ny]) {
						// 방문체크.
						sel[nx][ny]=cnt+1;
						
						// 먹을 수 있는 물고기 fish리스트에 다 담아줌.
						fish.add(new Point(nx, ny,cnt+1));
					} 
					// 물고기 먹을 수 없으면 그냥 방문체크하고 이동만 해줌.
					else {
						sel[nx][ny] = cnt + 1;
						q.add(new Point(nx, ny,cnt + 1));
					}
				}

			}

		}
		
		// 먹을 수 있는 물고기가 있음
		if(!fish.isEmpty()) {
			eat(map,size, eat);
		}
	}

	// 물고기 먹기.
	private static void eat(int[][] map,int size, int eat) {
		
		int dist=987654321;
		int x=-1, y=-1;
		// 먹을 수 있는 물고기들 중에서..
		for(int i=0; i<fish.size(); i++) {
			Point node = fish.get(i);
			// 거리가 가장 가까운 물고기 먹음.
			if(dist>node.cnt) {
				dist = node.cnt;
				x = node.x;
				y = node.y;
				
			}
			// 거리가 같으면 제일 위쪽 물고기 먹음.
			else if(dist==node.cnt) {
				if(x>node.x) {
					dist = node.cnt;
					x = node.x;
					y = node.y;
				}
				// 거리가 같고, 제일 위쪽에 있으면서 가장 왼쪽에 있는 물고기 먹음.
				else if(x==node.x) {
					if(y>node.y) {
						dist = node.cnt;
						x = node.x;
						y = node.y;
					}
				}
			}
		}
		
//		System.out.println(dist);
		// 물고기 거리만큼 걸린 시간
		time += dist;
		
		// 먹은 물고기 카운트.
		eat++;
		// 먹은 물고기 수와 내 크기 같으면 내 크기+1 해줌.
		if(size==eat) {
			eat=0;
			size++;
		}
		
		// 물고기 먹으면 제거.
		map[x][y]=0;
		
		// 물고기 먹은 위치에서 새롭게 시작.
		q.add(new Point(x, y, 0));
		
		move(map,size,eat);

	}

}
