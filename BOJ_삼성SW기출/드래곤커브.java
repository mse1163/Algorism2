import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 전체 가능한 모든 방향 저장
// 저장한 방향대로 위치 찍음.
// 전체 배열 돌면서 꼭지점 4개 있는지 확인

public class Main드래곤커브 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//100*100 배열
		int[][] map = new int[101][101];
		
		// 드래곤 커브 갯수
		int N = Integer.parseInt(br.readLine());
		StringTokenizer token;
		
		for (int i = 0; i < N; i++) {
			// 0~10 세대 -> 2^10 최대 1024개
			int[] loc = new int[1025];
			// 커브 갯수
			int size = 1;
			
			token = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(token.nextToken());
			int x = Integer.parseInt(token.nextToken());
			int d = Integer.parseInt(token.nextToken());
			int g = Integer.parseInt(token.nextToken());
			
			// 시작점 체크
			map[x][y] = 1;
			// 시작커브 방향 저장
			loc[size] = d;
			
			// 세대 수만큼 반복 돌면서 해당하는 방향 저장.
			for (int n = 0; n < g; n++) {
				// 방향 배열에 한칸씩 늘리면서 90도 회전한 방향 저장.
				// 맨뒤에서부터 90도회전한 방향 저장.
				for (int k = size; k > 0; k--) {
					size++;
					// 방향이 4가지이므로 4로 나눈 나머지 저장
					loc[size] = (loc[k] + 1) % 4;
				}
			}
			
			// 전체 만들어진 커브 갯수만큼 반복돌면서 방향대로 좌표이동.
			for(int n = 1; n<=size; n++) {
				x += dx[loc[n]];
				y += dy[loc[n]];
				
				if(x<0 || y<0 || x>=101 || y>=101) {
					continue;
				}
				
				// 좌표 체크
				map[x][y] = 1;
			}

		}
		
		
		int cnt=0;
		// 전체 배열 돌면서 꼭지점 4개 있는지 확인.
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(map[i][j]==1 && map[i+1][j]==1 && map[i][j+1]==1 && map[i+1][j+1]==1) {
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
	}

	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { 1, 0, -1, 0 };

}
