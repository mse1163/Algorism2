import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
// 무선충전_완탐
public class Solution5644 {
	static class Point{
		int x,y,c,price;


		public Point(int x, int y, int c, int price) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
			this.price = price;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", c=" + c + ", price=" + price + "]";
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			
			StringTokenizer token = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(token.nextToken());	// 시간
			int BC = Integer.parseInt(token.nextToken());	// 기지국 갯수
			
			 token = new StringTokenizer(br.readLine());
			 
			int[] A = new int[M]; 		// 사용자 A 이동정보
			for(int i=0; i<M; i++) {
				A[i] = Integer.parseInt(token.nextToken());
			}
			
			token = new StringTokenizer(br.readLine());
			
			int[] B = new int[M]; 		// 사용자 B 이동정보
			for(int i=0; i<M; i++) {
				B[i] = Integer.parseInt(token.nextToken());
			}
			
			Point[] info = new  Point[BC];	// 기지국 정보
			for(int i=0; i<BC; i++) {
				token = new StringTokenizer(br.readLine());
				
				int y = Integer.parseInt(token.nextToken());
				int x = Integer.parseInt(token.nextToken());
				
				int c = Integer.parseInt(token.nextToken());
				int p = Integer.parseInt(token.nextToken());
				
				info[i] = new Point(x, y, c, p);
			}
			
			int sum=0;
			
			// 사람 초기 위치
			int ax = 1, ay=1;
			int bx = 10, by=10;
			
			for(int k=0; k<=M; k++) {
				
				List<Point> pA = getDist(ax, ay, info);
				List<Point> pB = getDist(bx, by, info);
				
				// 걸쳐있는 기지국이 몇개인가?
				if(pA.size()==0 && pB.size()!=0) {
					int max=0;
					// 담은거 중에서 가장 큰값
					for(int i=0; i<pB.size(); i++) {
						max = Math.max(max, pB.get(i).price);
					}
					sum+=max;
				}
				else if(pA.size()!=0 && pB.size()==0) {
					int max=0;
					for(int i=0; i<pA.size(); i++) {
						max = Math.max(max, pA.get(i).price);
					}
					sum+=max;
				}
				// 둘다 기지국에 걸쳐있으면 모든 경우의 수 다 해봐서 가장 큰거 뽑아냄
				else if(pA.size()!=0 && pB.size()!=0) {
					int max = 0;
					
					for(int i=0; i<pA.size(); i++) {
						for(int j=0; j<pB.size(); j++) {
							int tmp = pA.get(i).price + pB.get(j).price;
							
							// 같은 기지국에 있으면 N/2 함.
							if(pA.get(i) == pB.get(j)) {
								tmp/=2;
							}
							max = Math.max(max, tmp);
						}
					}
					
					sum+= max;
				}
				
				// 마지막에는 이동을 안함.
				if(k==M) {
					break;
				}
				
				// 사람들 이동
				ax += dx[A[k]];
				ay += dy[A[k]];
				bx += dx[B[k]];
				by += dy[B[k]];
				
			}

			sb.append("#").append(t).append(" ").append(sum).append("\n");
			
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
	}
	
	static int M, asum, bsum;
	static int[] dx = {0,-1,0,1,0};
	static int[] dy = {0,0,1,0,-1};
	
	// 기지국안에 포함되는지 여부 구하기
	static List<Point> getDist(int x, int y, Point[] info){
		
		List<Point> list = new ArrayList<>();
		for(int i=0; i<info.length; i++) {
			int dist = Math.abs(x-info[i].x)+Math.abs(y-info[i].y);
			
			if(dist<=info[i].c) {
				list.add(info[i]);
			}
			
		}
		return list;
	}
}
