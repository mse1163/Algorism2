import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 이차배열 사용하여 리스트 사용할 때 보다 시간 절반으로 줄임.
public class Main낚시왕 {

	static class Point{
		int x,y,speed,loc, size;

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
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		int num = Integer.parseInt(token.nextToken());
		
		if(num==0) {
			System.out.println(0);
			return;
		}
		
		Point[][] map = new Point[N+1][M+1];
		for(int i=0; i<num; i++) {
			token = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(token.nextToken());
			int y = Integer.parseInt(token.nextToken());
			
			map[x][y] = new Point(x, y, Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()));
			
		}
		
		ans=0;
		for(int i=1; i<M+1; i++) {
			die(map,i);
			move(map);
		}
		
		System.out.println(ans);

	}

	static int N,M,ans;
	private static void die(Point[][] map, int w) {
		// 제일 처음에 물고기 발견되면 물고기 잡고 제거.
		for(int i=1; i<N+1; i++) {
			if(map[i][w]!=null) {
//				System.out.println("size: "+ map[i][w].size);
				ans += map[i][w].size;
				map[i][w]=null;
				return;
			}
		}
		
	}
	
	private static void move(Point[][] map) {
		Point[][] sel = new Point[N+1][M+1];
		
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<M+1; j++) {
				// 맵에 물고기 있으면
				if(map[i][j]!=null) {
					Point node = map[i][j];
					// 물고기 이동.
					for(int s=0; s<node.speed; s++) {
						if(node.loc==1) {
							node.x--;
							
							if(node.x==0) {
								node.x += 2;
								node.loc=2;
							}
						}
						else if(node.loc==2) {
							node.x++;
							
							if(node.x==N+1) {
								node.x -=2;
								node.loc=1;
							}
						}
						else if(node.loc==3) {
							node.y ++;
							if(node.y==M+1) {
								node.y -= 2;
								node.loc = 4;
							}
						}
						else if(node.loc==4) {
							node.y--;
							if(node.y==0) {
								node.y += 2;
								node.loc = 3;
							}
						}
					}
					
					// 물고기 이동한 위치에 이미 물고기가 있으면
					if(sel[node.x][node.y]!=null) {
						// 원래있던물고기 크기와 새로온 물고기 크기 비교.
						if(sel[node.x][node.y].size<node.size) {
							// 물고기 크기 더 크면 새로운물고기 정보 저장
							sel[node.x][node.y] = node;
						}
					}
					// 물고기가 없으면 물고기 해당위치에 저장.
					else {
						sel[node.x][node.y] = node;
					}
				}
				// 원래맵에 물고기있던거 다 초기화해줌.
				map[i][j]=null;
			}
		}
		
		// 새로 이동한 물고기위치를 원래 맵에 복사.
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<M+1; j++) {
				map[i][j] = sel[i][j];
			}
		}
	}

}
