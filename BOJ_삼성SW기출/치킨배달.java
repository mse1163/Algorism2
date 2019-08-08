import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
// 치킨집 중 M개 치킨집 조합으로 뽑음
// 뽑은 치킨집 중 집이랑 최소거리인 곳 찾기
// 조합 + 거리..?
// 조합 짤수 있으면  문제였음.
public class Main치킨배달 {
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
		
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		int[][] map = new int[N][N];
		for(int i=0; i<N; i++) {
			token = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
				
				// 집 위치 저장
				if(map[i][j]==1) {
					home.add(new Point(i, j));
				}
				// 치킨집 위치 저장
				else if(map[i][j]==2) {
					chicken.add(new Point(i, j));
				}
			}
		}
		
		result=987654321;
		Point[] sel = new Point[M];
		// 조합
		combi(chicken, sel, 0, 0);
		
		System.out.println(result);

	}
	
	static int N,M, ans, result;
	static ArrayList<Point> home = new ArrayList<>();
	static ArrayList<Point> chicken = new ArrayList<>();
	
	// 치킨집 위치 M개만큼 조합으로 뽑음.
	static void combi(ArrayList<Point> chicken,Point[] sel, int idx, int cnt) {
		if(idx==M) {
//			System.out.println(Arrays.toString(sel));
			move(sel);
			
			// 뽑은 치킨조합 중 최소인 치킨거리 뽑기.
			result = Math.min(result, ans);
			
			return;
		}
		
		if(cnt==chicken.size()) {
			return;
		}
		
		sel[idx] = chicken.get(cnt);
		combi(chicken, sel, idx+1, cnt+1);
		combi(chicken, sel, idx, cnt+1);
		
	}
	
	// 집을 기준으로 치킨집 제일 가까운 거리 찾기.
	private static void move(Point[] sel) {
		ans=0;
		for(int i=0; i<home.size(); i++) {
			Point node = home.get(i);
			
			int hx = node.x;
			int hy = node.y;
			
			int dist=987654321;
			for(int j=0; j<sel.length; j++) {
				int cx = sel[j].x;
				int cy = sel[j].y;
				
				// 집에서 치킨집까지의 거리중 최소 거리 찾기.
				dist = Math.min(dist, Math.abs(hx-cx)+Math.abs(hy-cy));
			}
			
			// 최소거리면 치킨거리에 더해줌.
			ans += dist;
		}
	}
}
