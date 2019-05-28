import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// 치킨배달
public class Main {
	static class Point{
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
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(br.readLine());
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

		home = new LinkedList<>();
		chicken = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					 home.add(new Point(i, j));
				}
				
				else if(map[i][j] == 2) {
					chicken.add(new Point(i, j));
				}
			}
		}
		
		ans = 987654321;
		Point[] sel = new Point[M];
		combi(sel, 0, 0);	
		
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

	static int N, M,ans,num;
	static int[][] map;
	static List<Point> home;	// 집 
	static List<Point> chicken;	// 치킨집 
	static List<Point> sum;		// 조합으로 뽑은 치킨집 
	
	// 최소 이동거리 구하기
	static void move() {
		num = 0;
		for(int i=0; i<home.size(); i++) {
			int hx = home.get(i).x;
			int hy = home.get(i).y;
			
			int min = 987654321;
			
			// 한 집당 제일 가까운 치킨집 구함
			for(int j=0; j<sum.size(); j++) {
				
				int cx = sum.get(j).x;
				int cy = sum.get(j).y;
				
				int result = Math.abs(hx - cx) + Math.abs(hy - cy);
				min = Math.min(min, result);
				
			}
			// 제일 가까운 치킨집 구해서 결과에 더해줌
			num+= min;

		}
	}
	
	// M갯수 치킨집 조합으로 뽑음.
	static void combi(Point[] sel, int cnt, int idx) {
		if(cnt==M) {
			sum = new LinkedList<>();
			//System.out.println(Arrays.toString(sel));
			for(int i=0; i<M; i++) {
				sum.add(new Point(sel[i].x, sel[i].y));
			}
			
			move();
			
			// 조합으로 뽑은 치킨집의 최소거리를 비교하여 그중 제일 작은 거리 넣어줌
			ans = Math.min(ans, num);
			return;
		}
		
		if(idx==chicken.size()) {
			return;
		}
		
		sel[cnt] = chicken.get(idx);
		combi(sel, cnt+1, idx+1);
		combi(sel, cnt, idx+1);
	}

}
