package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
// 스타트와 링크
public class Main14889 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
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
		
		sel = new boolean[N];
		
		combi(0, 0);
		
		bw.write(min+"");
		bw.flush();
		bw.close();
	}
	
	static int N;
	static int[][] map;
	static boolean[] sel;
	static int min = Integer.MAX_VALUE;
	static ArrayList<Integer> team1 , team2;
	
	// 팀을 할 수 있는 경우를 조합으로 뽑음
	static void combi(int idx, int cnt) {
		if(cnt==N/2) {
			team1 = new ArrayList<>();
			team2 = new ArrayList<>();
			
			// 팀원이 절반이므로 boolean검사를 통해 한번에 팀을 나눠줌 -> true팀 false팀
			for(int i=0; i<N; i++) {
				if(sel[i]) {
					team1.add(i);
				}
				else {
					team2.add(i);
				}
			}
			// 뽑은 팀원에서 가능한 능력치를 모두 확인하기 위해 모든경우 조사
			powerset();
			
			return;
		}
		
		if(idx==N) {
			return;
		}
		
		sel[idx] = true;
		combi(idx+1, cnt+1);
		sel[idx] = false;
		combi(idx+1, cnt);
	}
	
	static void powerset() {
		int sum1=0; int sum2=0;
		
		for(int i=0; i<N/2; i++) {
			int x1 = team1.get(i);
			int x2 = team2.get(i);
			
			for(int j=i+1; j<N/2; j++) {
				
				int y1 = team1.get(j);	
				int y2 = team2.get(j);
				
				sum1 += map[x1][y1]+map[y1][x1];
				sum2 += map[x2][y2]+map[y2][x2];
			}
		}
		// 팀 능력치의 차이 중 최소값
		int result = Math.abs(sum1-sum2);
		min = Math.min(result, min);
		
	}

}
