import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
// 결국엔 완탐 문제였음.
// 시간 줄여보겠다고 map이 0이면 break 했는데 그러면 안되는거였음.
// 전부 탐색해서 값을 찾아야 하므로 다 완탐으로 돌려야됨.
public class Main이차원배열과연산 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer token = new StringTokenizer(br.readLine());

		r = Integer.parseInt(token.nextToken());
		c = Integer.parseInt(token.nextToken());
		k = Integer.parseInt(token.nextToken());

		int[][] map = new int[101][101];

		for (int i = 1; i < 4; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 1; j < 4; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		
		ans=0;
		dfs(map,0);
		System.out.println(ans);

	}

	static int r, c, k,ans;

	private static void dfs(int[][] map,int cnt) {
		// 해당 값이 맞으면 종료
		if(map[r][c]==k) {
			ans=cnt;
			return;
		}
		
		// 시간이 100번 넘으면 -1출력하고 종료
		if(cnt>100) {
			ans=-1;
			return;
		}
		
		int x = -1, y = -1;
		for (int i = 1; i < 101; i++) {
			for (int j = 1; j < 101; j++) {
				// 최대 행 or 열 크기 뽑기.
				if (map[i][j] != 0) {
					x = Math.max(x, i);
					y = Math.max(y, j);
				}
			}
		}
		
		if (x >= y) {
			// 행 정렬
			width(map, x, y);
		} 
		else {
			// 열 정렬
			height(map, x, y);
			
		}
		
		dfs(map,cnt+1);

	}

	private static void width(int[][] map, int x, int y) {
		for (int i = 1; i <= x; i++) {
			// 숫자와 횟수 기억하기 위한 2차배열
			int[][] number = new int[101][2];
			for (int j = 1; j <= y; j++) {
				if (map[i][j] == 0) {
					continue;
				}
				
				// 숫자.
				number[map[i][j]][0]=map[i][j];
				// 숫자 카운트 횟수
				number[map[i][j]][1]++;
				// 해당위치는 0으로 초기화시켜줌.
				map[i][j] = 0;
			}
			
			// 횟수에 따른 오름차순 정렬
			Arrays.sort(number, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					return o1[1]-o2[1];
				}
			});
			
			int j = 1;
			for (int s = 1; s < 101; s++) {
				if (number[s][1] != 0) {
					// 숫자먼저 저장
					map[i][j] = number[s][0];
					// 횟수 저장
					map[i][j + 1] = number[s][1];
					j += 2;
				}
			}
		}

	}

	private static void height(int[][] map, int x, int y) {
		for (int j = 1; j <= y; j++) {
			int[][] number = new int[101][2];
			for (int i = 1; i <= x; i++) {
				if (map[i][j] == 0) {
					continue;
				}
				
				// 숫자.
				number[map[i][j]][0]=map[i][j];
				// 숫자 카운트 횟수
				number[map[i][j]][1]++;
				map[i][j] = 0;
			}

			Arrays.sort(number, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					return o1[1]-o2[1];
				}
			});
			
			int i = 1;
			for (int s = 1; s < 101; s++) {
				if (number[s][1] != 0) {
					map[i][j] = number[s][0];
					map[i+1][j] = number[s][1];
					i += 2;
				}
			}
		}

	}

}
