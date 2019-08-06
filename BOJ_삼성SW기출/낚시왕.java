import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 그 전에 푼것보다 메모리 줄음..
// 시간은 100ms정도 늘었는데 접속자가 많아서 그런거겟지..??
// 여러마리인 물고기 제거해주는게 어려움..
// 여전히 이부분에서 해맸음..
public class Main낚시왕2 {
	static class Point {
		int x, y, speed, loc, size;

		public Point(int x, int y, int speed, int loc, int size) {
			super();
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.loc = loc;
			this.size = size;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", speed=" + speed + ", loc=" + loc + ", size=" + size + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer token = new StringTokenizer(br.readLine());

		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		int num = Integer.parseInt(token.nextToken());

		for (int i = 0; i < num; i++) {
			token = new StringTokenizer(br.readLine());
			list.add(new Point(Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()),
					Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()),
					Integer.parseInt(token.nextToken())));
		}

		ans = 0;
		
		// 물고기없으면 끝.
		if(list.isEmpty()) {
			System.out.println(ans);
			return;
		}
		
		for (int i = 1; i < M + 1; i++) {
			// 물고기가 안남아있으면 끝
			if(list.isEmpty()) {
				break;
			}
			
			shoot(i);
			move();
		}

		System.out.println(ans);
	}

	static int N, M, ans;
	static ArrayList<Point> list = new ArrayList<>();

	private static void shoot(int w) {
		
		int ex = 987654321;
		int idx = -1;
		
		for (int i = 0; i < list.size(); i++) {
			// 땅과 가장 가까운 물고기는 x값이 제일 작은거.
			if (list.get(i).y == w && list.get(i).x < ex) {
				ex = list.get(i).x;
				idx = i;
			}
		}
		
		// 물고기 잡으면 ans에 잡은 물고기 크기++ 하고 물고기 제거.
		if (idx > -1) {
			ans += list.get(idx).size;
			list.remove(idx);
		}

	}

	private static void move() {
		// 같은 공간에 물고기 몇마리인지 확인.
		int[][] map = new int[N + 1][M + 1];
		
		for (int i = 0; i < list.size(); i++) {
			Point node = list.get(i);
			
			// 물고기 속도만큼 한칸씩 이동. 
			for (int s = 0; s < node.speed; s++) {

				if (node.loc == 1) {
					node.x--;
					
					// 만약 한칸이동시 테두리에 닿으면 방향 바꾸고 이동.
					if (node.x == 0) {
						node.x += 2;
						node.loc = 2;
					}
				} else if (node.loc == 2) {
					node.x++;

					if (node.x == N + 1) {
						node.x -= 2;
						node.loc = 1;
					}
				} else if (node.loc == 3) {
					node.y++;

					if (node.y == M + 1) {
						node.y -= 2;
						node.loc = 4;
					}
				} else if (node.loc == 4) {
					node.y--;

					if (node.y == 0) {
						node.y += 2;
						node.loc = 3;
					}
				}
			}
			
			list.get(i).x = node.x;
			list.get(i).y = node.y;
			list.get(i).loc = node.loc;
			
			// 해당위치에 물고기세기.
			map[node.x][node.y]++;
		}
		
		// 한 공간에 물고기 여러마리인거 처리.
		check(map);

	}

	private static void check(int[][] map) {
		ArrayList<Point> jos = new ArrayList<>();
		
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<M+1; j++) {
				// 여러마리 물고기이면.
				if(map[i][j]>1) {
					
					for(int k=0; k<list.size(); k++) {
						// 해당 위치의 물고기 찾아서 jos리스트에 저장하고 해당 물고기 삭제.
						if(list.get(k).x==i && list.get(k).y==j) {
							jos.add(list.get(k));
							list.remove(k);
							k--;
						}
					}
					
					// 여러마리 물고기 정보 중 크기가 가장 큰거 찾아서 다시 원래 리스트에 넣어줌.
					int max=-1; int idx=-1;
					for(int k=0; k<jos.size(); k++) {
						if(max<jos.get(k).size) {
							max = jos.get(k).size;
							idx = k;
						}
					}
					
					list.add(jos.get(idx));
					jos.clear();
				}
				
			}
		}

	}

}
