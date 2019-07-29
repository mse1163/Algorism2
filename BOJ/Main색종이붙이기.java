import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 색종이 붙엿다가 떼기..백트래킹..문제..
// 완탐..
// 어려운 문제..
public class Main색종이붙이기2 {
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
		
		int[][] map = new int[10][10];
		StringTokenizer token;
		
		for(int i=0; i<10; i++) {
			token = new StringTokenizer(br.readLine());
			for(int j=0; j<10; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
				
				if(map[i][j]==1) {
					list.add(new Point(i, j));
				}
			}
		}
		
		// 색종이 붙일 공간 없으면 0출력하고 바로 끝
		if(list.size()==0) {
			System.out.println(0);
			return;
		}
		
		// 색종이 붙인 수
		paper_cnt=0;
		
		ans=987654321;
		dfs(map,0);
		
		if(ans==987654321) {
			ans=-1;
		}
		System.out.println(ans);
		
	}
	
	static int ans,paper_cnt;
	static int[] paper = new int[6];
	static ArrayList<Point> list = new ArrayList<>();
	
	static void dfs(int[][] map, int cnt) {
		// 색종이 붙인수가 ans보다 크면 리턴 -> 최소값 찾는 문제이므로..
        if(paper_cnt>=ans) {
			return;
		}
        
        // 색종이 붙일공간에 다 붙였으면 끝.
		if(cnt==list.size()) {
			
			ans = Math.min(paper_cnt, ans);
			return;
		}
		
		
		for(int i=0; i<list.size(); i++) {
			int x = list.get(i).x;
			int y = list.get(i).y;
			
			if(map[x][y]==1) {
				// 5x5 부터 색종이 붙여봄
				for(int k=5; k>0; k--) {
					// 색종이 5장 다 사용했으면 다음으로..
					if(paper[k]>=5) {
						continue;
					}
					
					// 색종이 사용.
					paper[k]++;
					// 색종이 사용 갯수.
					paper_cnt++;
					move(map,x,y,k,cnt);
					paper_cnt--;
					paper[k]--;
				}
				
				return;
			}
			
		}
		
	}
	
	// 색종이 붙이러 이동함.
	private static void move(int[][] map, int x, int y,int k, int cnt) {
		// 색종이 붙일 크기가 범위 벗어나면 끝.
		if(x+k>10 || y+k>10) {
			return;
		}
		
		int[][] tmp = new int[10][10];
		deepCopy(tmp, map);
		
		// 색종이 붙인 공간 세기.
		int count = 0;
		for(int i=x; i<x+k; i++) {
			for(int j=y; j<y+k; j++) {
				if(tmp[i][j]==0) {
					return;
				}
				
				tmp[i][j] = 0;
				count++;
			}
		}
		
		cnt += count;
		
		dfs(tmp, cnt);
		
	}
	
	private static void deepCopy(int[][] tmp, int[][] map) {
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
				tmp[i][j] = map[i][j];
			}
		}
		
	}

}
