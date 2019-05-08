import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
// 상어 움직이는거 메소드로 빼서 옮겻더니 시간이 터짐...
// 그냥 안에 넣어서 하니까 잘되네...
// 이건 무슨경우인지..참...
// 이동하고 죽이는거는 잘했음.
// 상어크기 큰거만 남기는게 생각하는데 어려웠음..
public class Main낚시왕 {
	static class Point{
		int x,y,speed,loc,num;

		public Point(int x, int y, int speed, int loc, int num) {
			super();
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.loc = loc;
			this.num = num;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", speed=" + speed + ", loc=" + loc + ", num=" + num + "]";
		}
		
	}
		
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		int fish = Integer.parseInt(token.nextToken());
		
		for(int i=0; i<fish; i++) {
			token = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(token.nextToken());
			int y = Integer.parseInt(token.nextToken());
			int speed = Integer.parseInt(token.nextToken());
			int loc = Integer.parseInt(token.nextToken());
			int num = Integer.parseInt(token.nextToken());
			
			list.add(new Point(x, y, speed, loc, num));
		}
		
		ans = 0;
		// 상어 없으면 바로 끝냄
		if(list.isEmpty()) {
			System.out.println(ans);
			return;
		}
		
		// 가로 길이만큼 돌면됨
		for(int i=1; i<M+1; i++) {
			
			die(i);
			move();
		
		}
		System.out.println(ans);
	}
	
	static int N,M, ans;
	static List<Point> list = new ArrayList<>();
	
	// 죽이기
	static void die(int count) {
		// 상어위치 찍을 맵. 
		// 맵을 기억하고 거기서 죽일상어 찾는게 시간 더 줄일수 잇음
		int[][] map = new int[N+1][M+1];
		for(int i=0; i<list.size(); i++) {
			int x = list.get(i).x;
			int y = list.get(i).y;
			int num = list.get(i).num;
			
			map[x][y] = num;
			
		}
		
		
		for(int i=1; i<N+1; i++) {
			// 상어바로 만나면 결과값에 더해주고, 그 위치에 해당하는 상어 리스트에서 제거하고 바로 브레이크
			if(map[i][count]!=0) {
				ans += map[i][count];
				
				for(int k=0; k<list.size(); k++) {
					//그 위치에 해당하는 상어 리스트에서 제거
					if(list.get(k).x==i && list.get(k).y==count) {
						list.remove(k);
						
					}
				}
				break;
			}
		}
			
		
	}
	
	// 상어 움직임
	static void move() {
		// 같은위치에 상어 몇마리인지 확인
		int[][] sel = new int[N+1][M+1];
		
		for(int k=0; k<list.size(); k++) {
			
			int x = list.get(k).x;
			int y = list.get(k).y;
			int speed = list.get(k).speed;
			int loc = list.get(k).loc;
			int num = list.get(k).num;
			
			// 상어 속도만큼 반복 
			for(int i=0; i<speed; i++) {
				if(loc==1) {
					x -= 1;
					
					// 범위 벗어나면 방향바꿔주고 바뀐방향으로 2칸 이동
					if(x<1) {
						x = x+2;
						loc = 2;
					}
				
				}
				else if(loc==2) {
					x += 1;
					
					if(x>=N+1) {
						x = x-2;
						loc = 1;	
					}
			
				}
				else if(loc==3) {
					y += 1;
					
					if(y>=M+1) {
						y = y-2;
						loc = 4;
						
					}
				
				}
				else if(loc==4) {
					y -= 1;
					
					if(y<1) {
						y = y+2;
						loc = 3;
					}
					
				}
			}
			
			// 같은 위치에 상어가 몇마리 있는지 넣어줌
			sel[x][y]++;
			// 상어 움직이고 최종위치 다시 넣어줌
			list.set(k, new Point(x, y, speed, loc, num));
		}
		
		check(sel);
	}
	
	// 같은 위치 상어 중 큰 상어만 남기기
	static void check(int[][] sel) {
		
		for(int i=0; i<N+1; i++) {
			for(int j=0; j<M+1; j++) {
				// 상어 여러마리인 위치 찾음
				if(sel[i][j]>1) {
					// 같은위치 상어 정보 담을 리스트
					List<Point> jos = new ArrayList<>();
					for(int k=0; k<list.size(); k++) {
						// 상어리스트에서 같은위치에 해당하는 상어를 jos리스트에 담음 
						if(list.get(k).x==i && list.get(k).y==j) {
							jos.add(list.get(k));
							// 중복된 상어를 리스트에서 제거 해줌.
							list.remove(k);
							k--;
						}
					}
					
					// 중복된 상어를 큰 순서대로 정렬해서 첫번째 상어만 리스트에 담아줌
					Collections.sort(jos, new Comparator<Point>() {

						@Override
						public int compare(Point o1, Point o2) {
							// TODO Auto-generated method stub
							return o2.num - o1.num;
						}
					});
					
					list.add(jos.get(0));
					
				}
				
			}
		}
		
	}

}
