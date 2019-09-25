import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
// DP사용..메모리제이션..
// 계단 내려갈시 3명까지 동시에 갈 수 있다는 조건 처리가 까다로웠음..
// powerset으로 모든 경우의수 검사후 계단1, 계단2로 내려갈 사람 분류함.
// 완전탐색으로 해결.
public class Solution점심식사시간 {
	static class Point {
		int x, y;

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

		StringTokenizer token;

		int T = Integer.parseInt(br.readLine());
		// 사람 위치
		person = new Point[10];
		// 계단 위치
		stair = new Point[2];
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			int idx = 0, b = 0;
			for (int i = 0; i < N; i++) {
				token = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int a = Integer.parseInt(token.nextToken());
					if (a == 1) {
						person[idx] = new Point(i, j);
						idx++;
					} else if (a > 1) {
						if (b == 0) {
							// 계단1 길이
							stair1_size = a;
						} else if (b == 1) {
							// 계단2 길이
							stair2_size = a;
						}
						// 계단 위치
						stair[b] = new Point(i, j);
						b++;
					}
				}
			}

			ans = 0;
			result = 987654321;
			boolean[] sel = new boolean[idx];

			powerset(person, sel, idx, 0);
			
			System.out.println("#"+t+" "+result);
		}
	}

	static int N, stair1_size, stair2_size, ans, result;
	static Point[] stair, person;
	static ArrayList<Point> group1 = new ArrayList<>();
	static ArrayList<Point> group2 = new ArrayList<>();
	
	// 모든 경우의 수 확인.
	static void powerset(Point[] person, boolean[] sel, int N, int idx) {
		if (idx == N) {
			group1.clear();
			group2.clear();
			for (int i = 0; i < N; i++) {
				// 계단1로 내려감.
				if (sel[i] == true) {
					group1.add(person[i]);
				}
				// 계단2로 내려감
				else {
					group2.add(person[i]);
				}
			}
			
			ans=0;
			if (group1.size() > 0) {
				stair1(N);
			}
			if (group2.size() > 0) {
				stair2(N);
			}

			result = Math.min(result, ans);
			return;
		}

		sel[idx] = true;
		powerset(person, sel, N, idx + 1);
		sel[idx] = false;
		powerset(person, sel, N, idx + 1);
	}

	static void stair1(int N) {
		// 계단 내려간 시작시간.
		int[][] arr = new int[2][group1.size()];
		// 계단에 이동후 1분후부터 이동가능.
		for (int i = 0; i < group1.size(); i++) {
			arr[0][i] = Math.abs(stair[0].x - group1.get(i).x) + Math.abs(stair[0].y - group1.get(i).y) + 1;
		}
		
		// 시간 적은것부터 정렬
		Arrays.sort(arr[0]);
		
		// 계단 내려가는 시간 저장, 3명이상일경우 기다렷다가 내려감.
		for (int i = 0; i < group1.size(); i++) {
			// 3명보다 적으면 그냥 바로 내려감
			if (i < 3) {
				arr[1][i] = arr[0][i];
			} 
			// 한명이 빠져나가고 다음 사람 내려가기 시작한 시간과 이동후 내려갈수있는 시작시간 비교후 큰값 저장
			else {
				arr[1][i] = Math.max(arr[0][i], arr[1][i - 3]+stair1_size);
			}
		}
		
		// 계단1 다 내려간후 도착시간.
		ans = arr[1][group1.size() - 1]+stair1_size;
	}

	static void stair2(int N) {
		int[][] arr = new int[2][group2.size()];
		for (int i = 0; i < group2.size(); i++) {
			arr[0][i] = Math.abs(stair[1].x - group2.get(i).x) + Math.abs(stair[1].y - group2.get(i).y) + 1;
		}

		Arrays.sort(arr[0]);
		for (int i = 0; i < group2.size(); i++) {
			if (i < 3) {
				arr[1][i] = arr[0][i];
			} 
			else {
				arr[1][i] = Math.max(arr[0][i], arr[1][i - 3]+stair2_size);
			}
		}

		ans = Math.max(ans, arr[1][group2.size()-1]+stair2_size);
	}
}
