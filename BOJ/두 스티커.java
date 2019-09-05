import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// 시뮬레이션
// 규칙만 잘 찾으면 해결
public class Main두스티커 {
	static class Point implements Comparable<Point>{
		int x,y,width;

		public Point(int x, int y, int width) {
			super();
			this.x = x;
			this.y = y;
			this.width = width;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", width=" + width + "]";
		}

		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
			return o.width - this.width;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		
		K = Integer.parseInt(br.readLine());
		
		Point[] arr = new Point[K];
		for(int i=0; i<K; i++) {
			token = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(token.nextToken());
			int y = Integer.parseInt(token.nextToken());
			
			arr[i] = new Point(x, y, x*y);
			
		}
		
		Arrays.sort(arr);
		
		max = 0;
		patch(arr);
		System.out.println(max);
	}
	
	static int N,M,K,max;
	
	static void patch(Point[] arr) {
		for(int i=0; i<K-1; i++) {
			for(int j=i+1; j<K; j++) {
				down(arr, i, j);
				right(arr, i, j);
				loc_down(arr,i,j);
				loc_right(arr,i,j);
			}
		}
	}
	
	// 아래쪽에 붙이기
	static void down(Point[] arr, int i, int j) {
		int nx = arr[i].x+arr[j].x;
		int ny = Math.max(arr[i].y, arr[j].y);
		
		if(nx>N || ny>M) {
			// 90도 회전했을때 범위
			if(nx>M || ny>N) {
				return;
			}
		}
		
		// 넓이
		int nw = arr[i].width+arr[j].width;
		max = Math.max(nw, max);
	}
	
	// 오른쪽에 붙이기
	static void right(Point[] arr, int i, int j) {
		int nx = Math.max(arr[i].x, arr[j].x);
		int ny = arr[i].y+arr[j].y;
		
		if(nx>N || ny>M) {
			// 90도 회전 후 범위
			if(nx>M || ny>N) {
				return;
			}
		}
		
		int nw = arr[i].width+arr[j].width;
		max = Math.max(nw, max);
	}
	
	// 회전후 아래에 붙이기
	static void loc_down(Point[] arr,int i,int j) {
		int nx = arr[i].y + arr[j].x;
		int ny = Math.max(arr[i].x, arr[j].y);
		
		if(nx>N || ny>M) {
			if(nx>M || ny>N) {
				return;
			}
		}
		
		int nw = arr[i].width+arr[j].width;
		max = Math.max(nw, max);
	}
	
	// 회전후 오른쪽붙이기
	static void loc_right(Point[] arr,int i,int j) {
		int nx = arr[i].x + arr[j].y;
		int ny = Math.max(arr[i].y, arr[j].x);
		
		if(nx>N || ny>M) {
			if(nx>M || ny>N) {
				return;
			}
		}
		
		int nw = arr[i].width+arr[j].width;
		max = Math.max(nw, max);
	}

}
