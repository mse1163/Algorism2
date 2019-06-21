import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
// sw역량테스트 문제였음
// N(2 ≤ N ≤ 50) 이므로 9개 타자 전체 순열 돌리면 시간초과 -> 9!
// 1번 타자 기억해 놓고 8개 타자들만 순열로 돌려 기억해놓은 1번타자를 4번에 고정시켜줌.
// 이렇게 하면 8!만큼 순열하므로 시간안에 들어올수 있음
// 조금 어려움...야구 규칙할면 이해가 쉬울것임..
// 아웃되면 주자들 다 나가는줄..알고 ball클리어 해줬는데 3아웃 될때만 주자들 다 내보내면됨
public class 야구공 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

		N = Integer.parseInt(br.readLine());

		arr = new int[N][9];
		// 1번타자들 기억해줌.
		sx = new int[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());

			for (int j = 1; j < 10; j++) {

				if (j == 1) {
					// 1번에 있는 타자 기억해놓고 나중에 4번타자로 넣어줌.
					sx[i] = Integer.parseInt(token.nextToken());
				}
				else {
					arr[i][j-1] = Integer.parseInt(token.nextToken());
				}
			}

		}

		max = -1;

		perm(arr, 1);
		System.out.println(max);

	}

	static int N, max, result, out;
	static int[][] arr;
	static int[] sx;
	static ArrayList<Integer> ball, new_juja;
	
	// 경기 진행.
	static int move(ArrayList<Integer> ball, ArrayList<Integer> num, int i) {
		
		// 3아웃 될때까지 반복해서 경기 진행
		while (true) {

			// 아웃
			if (num.get(i) == 0) {

				out++;
				
				// 3아웃 되면 ball에 있던 주자 없애줌.
				if (out == 3) {
					ball.clear();
					
					// 다음 타자로 넘어감.
					i++;
					
					if (i == 9) {
						i = 0;
					}
//					System.out.println(i);
					return i;
				}
			}
			// 안타
			else if (num.get(i) < 4) {
				// 나와잇는 주자들 안타 값만큼 이동
				for (int j = 0; j < ball.size(); j++) {
					int b = ball.get(j) + num.get(i);
					
					// 만약 홈에 들어가면 점수+1하고 주자에서 제외함.
					if (b >= 4) {
						result++;
						ball.remove(j);
						j--;
					} 
					// 홈에 안들어갔으면 안타값만큼 이동해줌.
					else {
						ball.set(j, b);
					}
				}
				
				// 안타친 주자 내보냄.
				ball.add(num.get(i));

			}

			// 홈런
			else if (num.get(i) == 4) {
				// 홈런하면 주자들 수 만큼 점수 더해줌. 홈런을 친 주자도 따로 점수 더해줌.
				result += ball.size() + 1;
				// 홈런치면 주자들 다 들어오므로 ball비워줌.
				ball.clear();
			}
			
			// 다음 타자로 넘어감.
			i++;
			if (i == 9) {
				i = 0;
			}
		}

	}
	
	// 9명 타자 순서 정하기.
	static void perm(int[][] arr, int idx) {

		if (idx == 9) {
			
			ball = new ArrayList<>();
			result = 0;
			int ball_idx = 0;
			
			for (int i = 0; i < N; i++) {
				// 4번타자 포함하는 타자들
				new_juja = new ArrayList<>();
				
				// 순열로 뽑은 8명 타자
				for(int j=1; j<9; j++) {
					new_juja.add(arr[i][j]);
				}
				
				// 4번타자고정 넣어줌.
				new_juja.add(3, sx[i]);
				
//				System.out.println(new_juja.toString());
				
				// 새로운 이닝시작 아웃은 리셋
				out = 0;
				// 경기진행
				ball_idx = move(ball, new_juja, ball_idx);
			}

//			System.out.println("result: "+result);
			max = Math.max(result, max);
			return;
		}
		
		// 순열 
		for (int i = idx; i < 9; i++) {

			swap(arr, i, idx);
			perm(arr, idx + 1);
			swap(arr, i, idx);
		}
	}

	static void swap(int[][] arr, int a, int b) {
		for (int i = 0; i < N; i++) {
			int tmp = arr[i][a];
			arr[i][a] = arr[i][b];
			arr[i][b] = tmp;
		}
	}

}
