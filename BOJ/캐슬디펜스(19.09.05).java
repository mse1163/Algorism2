import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
// 리스트 복사해서 사용할때 리스트값에서 x증가 할때 
// 바로 list.get(i).x++ 하지말고 set으로 담아서 값 바꿔줘야 처음 리스트에 영향 안미침.
public class Main캐슬디펜스 {
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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		D = Integer.parseInt(token.nextToken());

		map = new int[N + 1][M];
		list.clear();
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
				if (map[i][j] == 1) {
					list.add(new Point(i, j));
				}
			}
		}

		int[] arr = new int[3];
		ans = -1;
		combo(arr, 0, 0);
		System.out.println(ans);

	}

	static int N, M, D, ans, result;
	static int[][] map;
	static ArrayList<Point> list = new ArrayList<>();
	static ArrayList<Point> tmp = new ArrayList<>();
	static Point[] sel;
	
	// 궁수 위치 정하기.
	static void combo(int[] arr, int idx, int cnt) {
		if (cnt == 3) {
			sel = new Point[3];
			// 궁수 위치 저장
			for (int i = 0; i < 3; i++) {
				sel[i] = new Point(N, arr[i]);
			}
			
			// 처음 적의 위치 복사
			tmp.clear();
			tmp.addAll(list);

			result = 0;
			// 공격
			boom(tmp, sel);

			ans = Math.max(ans, result);
			return;
		}

		if (idx == M) {
			return;
		}

		arr[cnt] = idx;
		combo(arr, idx + 1, cnt + 1);
		combo(arr, idx + 1, cnt);
	}
	
	// 적 공격
	static void boom(ArrayList<Point> list, Point[] sel) {
		// 적이 없을때까지 반복
		while (!list.isEmpty()) {
			// 제거할 적 담아놓음 -> 제거할 수 잇는 적은 궁수당 한명이므로 배열크기 3으로 고정
			Point[] rm = new Point[3];
			for (int k = 0; k < 3; k++) {
				// 궁수 위치
				int sx = sel[k].x;
				int sy = sel[k].y;

				int min_dist = 987654321;
				// 제거할 적의 인덱스
				int idx = -1;
				
				// 전체 적있는 위치와 궁수와 거리 구해서 가까운 적 선택
				for (int i = 0; i < list.size(); i++) {
					Point node = list.get(i);

					int x = node.x;
					int y = node.y;
					
					// 거리
					int dist = Math.abs(sx - x) + Math.abs(sy - y);
					
					if (dist <= D) {
						// 가까운 거리의 적
						if (dist < min_dist) {
							min_dist = dist;
							idx = i;
						}
						// 거리가 같으면 왼쪽인 적 선택
						else if (dist == min_dist) {
							if (y < list.get(idx).y) {
								idx = i;
							}
						}
					}

				}
				
				// 제거할 적 있으면 제거배열에 넣어줌
				if (idx > -1) {
					rm[k] = list.get(idx);
				}
			}
			
			// 적 제거
			remove(list,rm);
			// 아래로 이동
			move(list);
		}
	}

	static void remove(ArrayList<Point> list, Point[] rm) {
		int cnt = 0;
		for (int k = 0; k < 3; k++) {
			// 제거 적있으면
			if (rm[k] != null) {
				// 위치 같은애 찾아서 제거.
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).x == rm[k].x && list.get(i).y == rm[k].y) {
						list.remove(i);
						i--;
						cnt++;
					}
				}
			}
		}
		
		// 제거한 적 수 세기
		result += cnt;
	}
	
	// 아래로 한칸 이동.
	static void move(ArrayList<Point> list) {
		for (int i = 0; i < list.size(); i++) {
			int x = list.get(i).x;
			list.set(i,new Point(x+1, list.get(i).y));
			// 성에 도착했으면 적 제거.
			if (x+1 == N) {
				list.remove(i);
				i--;
			}
		}
	}
}
