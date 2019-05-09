import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
// 처음에 hashset으로 중복제거 해주려 했는데 객체로 받아서 그런지 중복제거가 안됨...
// 그리고 처음 맵을 들고 적을 한칸씩 이동하고 그맵을 계속 사용했더니.
// 맵이 처음 상태가 아니여서 틀렷음. -> 처음맵을 복사해서 복사맵을 들고다님.
public class Main캐슬디펜스 {
	static class Point{
		int x,y;

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
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		D = Integer.parseInt(token.nextToken());
		
		int[][] map = new int[N+1][M];
		for(int i=0; i<N; i++) {
			token = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		
		
		max=-1;
		// 궁수 3명위치 담을 배열
		int[] arr = new int[3];
		combi(map,arr, 0 , 0);
		
		bw.write(max+"");
		bw.flush();
		bw.close();

	}
	
	static int N,M,D, result, max;
	
	// 조합으로 궁수3명 뽑을 수 있는 모든 경우 
	static void combi(int[][] map, int[] arr, int cnt, int idx) {
		if(cnt==3) {
			// 궁수3명 위치 담을 리스트
			ArrayList<Point> list = new ArrayList<>();
			for(int i=0; i<3; i++) {
//				System.out.print(arr[i]+" ");
				
				// 뽑은 궁수 위치 리스트에 넣음
				list.add(new Point(N, arr[i]));
			}
			
			// 제거한 적의 수 가져올 변수
			result=0;
			
			// 원래 맵을 변경없이 계속 가져가야 하므로 복사해서 가져감.
			int[][] copy = new int[N][M];
			deepCopy(copy, map);
			
			// 적에 공격하여 제거할 메소드
			attack(copy,list, 0, 0);

			max = Math.max(max, result);
			
			return;
		}
		
		if(idx==M) {
			return;
		}
		
		arr[cnt] = idx;
		combi(map, arr, cnt+1, idx+1);
		combi(map, arr, cnt, idx+1);
		
	}
	
	// 적 공격하여 제거
	private static void attack(int[][] map, ArrayList<Point> list, int cnt, int count) {
		// 맵의 세로길이만큼  한칸씩 다내려오면 끝남.
		if(cnt==N) {
			// 제거한 적의 수를 result로 가져감.
			result = count;
//			System.out.println("제거한 적 수: "+count);
			return;
		}
		
		// 모든 적의 위치를 리스트에 담아줌.
		ArrayList<Point> black = new ArrayList<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				
				if(map[i][j]==1) {
					black.add(new Point(i, j));
				}
			}
		}
		
		// 적이 없으면 바로 리턴.
		if(black.isEmpty()) {
			result = count;
//			System.out.println("제거한 적 수: "+count);
			return;
		}
		
		// 제거할 적 담아놓음
		ArrayList<Point> remove = new ArrayList<>();
		for(int i=0; i<list.size(); i++) {
				// 궁수 위치
				int x = list.get(i).x;
				int y = list.get(i).y;
			
			int d=987654321, dx=0, dy=0;
			// 제거할 적이 있는지 확인.
			boolean isok = false;
			
			for(int j=0; j<black.size(); j++) {
				// 적 위치
				int nx = black.get(j).x;
				int ny = black.get(j).y;
				
				// 거리
				int dist = Math.abs(x-nx)+Math.abs(y-ny);
				
				// 적의 거리가 D이하면 제거 가능
				if(dist<=D) {
					// 제거할 적 있음.
					isok = true;
					
					// 거리가 더 가까운거 찾기
					if(d>dist) {
						d = dist;
						dx = nx;
						dy = ny;
					} 
					// 거리가 같으면 제일 왼쪽에 있는에 찾기
					else if(d==dist) {
						if(dy>ny) {
							dx = nx;
							dy = ny;
						}
					}
				}
				
			}
			
			// 제거할 적잇으면 remove리스트에 제거할 적 위치 넣음.
			if(isok) {
				
				remove.add(new Point(dx, dy));
			}
		}
		
		// 궁수3명 다 활 쏜 후 제거할 적 잇으면.
		if(remove.size()>0) {
			
			for(int i=0; i<remove.size(); i++) {
				int rx = remove.get(i).x;
				int ry = remove.get(i).y;
				
				// 제거 안되있으면 제거해주면서 count세줌. 이미 제거 되있으면 count안세줌
				// 즉, 중복된 적은 제외시킴.
				if(map[rx][ry]!=0) {
					map[rx][ry]=0;
					// 제거된 적 수
					count++;
				}
			}
		}
		
		// 적을 한칸씩 아래로 이동 -> 맵을 아래로 내림.
		int[][] tmp = new int[N+1][M];
		newMap(tmp, map);
		
		// 맵 내린후 그맵을 가지고 다시 반복
		attack(tmp, list, cnt+1, count);
		
	}
	
	// 처음 맵 전체 복사
	static void deepCopy(int[][] tmp, int[][] map) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				tmp[i][j]=map[i][j];
			}
		}
	}
	
	// 적 한칸씩 아래로 이동.
	private static void newMap(int[][] tmp, int[][] map) {
		for(int i=0; i<N; i++) {
			tmp[i+1] = map[i];
		}
		
//		System.out.println("---------맵 한칸 이동.---------");
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<M; j++) {
//				System.out.print(tmp[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
	}

}
