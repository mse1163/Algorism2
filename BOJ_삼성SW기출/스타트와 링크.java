import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// 조합과 모든경우의수로 구하는 문제
// 무난하게 풀이.
public class Main스타트와링크 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(token.nextToken());
			}
		}
		
		result = 987654321;
		boolean[] sel = new boolean[N];
		powerset(sel, 0, 0);
		
		bw.write(result+"");
		bw.flush();
		bw.close();
	}
	
	static int N, result;
	static int[][] map;
	
	// 팀원 절반씩해서 모든 경우의 수 뽑기
	static void powerset(boolean[] sel, int cnt, int idx) {
		if(cnt==N/2) {
			// 스타트팀
			int[] start = new int[N/2];
			// 링크팀
			int[] link = new int[N/2];
			
			int s=0, l=0;
			for(int i=0; i<N; i++) {
				// true면 스타트팀
				if(sel[i]) {
//					System.out.print(i+" ");
					start[s] = i;
					s++;
				}
				// false면 링크팀
				else {
					link[l] = i;
					l++;
				}
			}
			
			// 팀을 다 나눠서 팀원 능력치 구하기
			combi(start, link);
			
			return;
		}
		
		if(idx==N) {
			return;
		}
		
		sel[idx] = true;
		powerset(sel, cnt+1, idx+1);
		sel[idx] = false;
		powerset(sel, cnt, idx+1);
		
		
	}
	
	// 팀원 중 두명씩 뽑아서 모든 능력치 구하기 
	static void combi(int[] start, int[] link) {
		int start_sum = 0;
		int link_sum = 0;
		
		// 중복안되게 모든 경우 돌면서 능력치 더함.
		for(int i=0; i<start.length-1; i++) {
			for(int j=i+1; j<start.length; j++) {
				
				start_sum += map[start[i]][start[j]];
				start_sum += map[start[j]][start[i]];
				
				link_sum += map[link[i]][link[j]];
				link_sum += map[link[j]][link[i]];
			}
		}
		
		// 두 팀의 능력치 차이
		int min = Math.abs(start_sum - link_sum);
//		System.out.println(min);
		// 능력치 차이 최소값
		result = Math.min(result, min);
		
	}

}
