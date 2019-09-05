import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 1. 색종이 붙일 처음 위치 찾기
// 2. 불일수 잇는 최대 크기 찾기
// 3. 붙였다 뗏다.

public class Main색종이붙이기2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[][] map = new int[10][10];
		for (int i = 0; i < 10; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}

		ans = 987654321;
		move(map, 0);
		if (ans == 987654321) {
			ans = -1;
		}
		System.out.println(ans);
	}

	static int ans;
	static int[] box = { 0, 5, 5, 5, 5, 5 };

	static void move(int[][] map, int cnt) {

		if (ans < cnt) {
			return;
		}

		int sx = -1, sy = -1;
		// 색종이 붙일 시작위치
		out: for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (map[i][j] == 1) {
					sx = i;
					sy = j;
					break out;
				}
			}
		}
		
		// 색종이 붙일게 없으면 끝
		if (sx == -1 && sy == -1) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		// 색종이 크기 구하기
		int size = getSize(map, sx, sy);
		
		// 크기만큼 붙였다 뗏다.
		for (int k = size; k > 0; k--) {
			if (box[size] == 0) {
				continue;
			}
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < k; j++) {
					map[sx + i][sy + j] = 0;
				}
			}
			box[k]--;
			move(map, cnt + 1);
			box[k]++;
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < k; j++) {
					map[sx + i][sy + j] = 1;
				}
			}

		}

	}
	
	// 색종이 크기 구하기
	static int getSize(int[][] map, int x, int y) {
		int size = 5;
		// 5부터 1로 줄여가며 확인
		while (size > 0) {
			boolean isok = false;
			// 범위 넘어가거나 중간에 0이 있으면 끝.
			out: for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (x + i >= 10 || y + j >= 10 || map[x + i][y + j] == 0) {
						isok = true;
						break out;
					}
				}
			}
			
			// 붙일수 있으면 크기 리턴
			if (!isok) {
				return size;
			}

			size--;
		}

		return size;
	}
}
