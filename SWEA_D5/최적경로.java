import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// 파워셋으로 모든 고객집간 거리 구해서 최소거리 찾기.
// 무난한 
public class Solution최적경로 {
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
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		StringTokenizer token;
		for(int t=1; t<=T; t++) {
			
			N = Integer.parseInt(br.readLine());
			
			Point[] arr = new Point[N];
			
			token = new StringTokenizer(br.readLine());
			// 회사위치
			sx = Integer.parseInt(token.nextToken());
			sy = Integer.parseInt(token.nextToken());
			
			// 집위치
			ex = Integer.parseInt(token.nextToken());
			ey = Integer.parseInt(token.nextToken());
			
			//고객집 위치
			for(int i=0; i<N; i++) {
				arr[i] = new Point(Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()));
			}
			
//			System.out.println(Arrays.toString(arr));
			ans=987654321;
			powerset(arr,0);
			
			bw.write("#"+t+" "+ans+"\n");
		}
		bw.flush();
		bw.close();
	}
	
	static int N,sx,sy,ex,ey,ans;
	// 모든 경우의 수 확인.
	private static void powerset(Point[] arr, int idx) {
		
		if(idx==N) {
//			System.out.println(Arrays.toString(arr));
			// 거리 계산
			dist(arr);
			return;
		}
		
		for(int i=idx; i<N; i++) {
			swap(arr, i, idx);
			powerset(arr, idx+1);
			swap(arr, i, idx);
		}
	}
	
	// 거리 계산
	private static void dist(Point[] arr) {
		// 회사-첫번째 고객 거리
		int dist = Math.abs(sx-arr[0].x)+Math.abs(sy-arr[0].y);
		// 고객집간 거리
		for(int i=0; i<N-1; i++) {
			dist += Math.abs(arr[i].x - arr[i+1].x)+Math.abs(arr[i].y-arr[i+1].y);
		}
		// 마지막고객-집 거리
		dist += Math.abs(arr[N-1].x-ex)+Math.abs(arr[N-1].y-ey);
		
		// 최소 거리
		ans = Math.min(ans, dist);
	}

	private static void swap(Point[] arr, int a, int b) {
		Point tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

}
