package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
// 뿌요뿌요
// 맵 내리는 것을 잘 못해줫음...
// 맵 잘 내려가는지 확인!! 
// 꼭!! 먼저 큐에 담으면서 초기화 시켜준후 큐에서 빼서 비교해서 내려줌
// 초기화 안해주고 바로 내려서 안됫음..
// 한번 수행할때 여러개 터져야 하므로 while문으로 무한루프 돌아줌. 터진게 업으면 끝냄
public class Main11559 {
	static class Point {
		int x, y;
		char text;

		public Point(int x, int y, char text) {
			super();
			this.x = x;
			this.y = y;
			this.text = text;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", text=" + text + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		char[][] map = new char[12][6];

		for (int i = 0; i < 12; i++) {
			String str = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = str.charAt(j);
			}
		}

//		for (int i = 0; i < 12; i++) {
//			for (int j = 0; j < 6; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}

		
		boolean[][] sel;
		int cnt = 0;
//한번 수행할때 여러개 터져야 하므로 while문으로 무한루프 돌아줌. 터진게 업으면 끝냄
		while (true) {
			
			sel = new boolean[12][6];
			boolean isok = false;

			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (map[i][j] != '.' && !sel[i][j]) {
						list = new ArrayList<>();
						
						boom(map, sel, i, j, map[i][j]);
						
						// 4개 이상 같은 문자 잇으면 터침.
						if (list.size() >= 4) {
							isok = true;

							for (int k = 0; k < list.size(); k++) {
								int x = list.get(k).x;
								int y = list.get(k).y;

								map[x][y] = '.';
							}
						}
						
					}

				}
			}
			// 한번이라도 터졌으면 cnt++;
			if (!isok)
				break;
			else
				cnt++;
			// 맵 빈곳 내림
			down(map);
		}
		
		System.out.println(cnt);
	}
	
	static List<Point> list;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static void boom(char[][] map, boolean[][] sel, int x, int y, char text) {

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= 12 || ny >= 6) {
				continue;
			}

			if (map[nx][ny] == text && !sel[nx][ny]) {
				sel[nx][ny] = true;
				list.add(new Point(nx, ny, text));
				boom(map, sel, nx, ny, text);
			}
		}

	}
	
	static Queue<Character> q;
	static void down(char[][] map) {

		for (int j = 0; j < 6; j++) {
			q = new LinkedList<>();
			// 일단 큐에 있던거 다 담으면서 .으로 초기화 해쥼
			for (int i = 11; i >= 0; i--) {
				q.add(map[i][j]);
				map[i][j]='.';
			}
			
			// 큐에서 빼면서 .이 아닌거는 아래로 내려줌
			int count = 11;
			while(!q.isEmpty()) {
				char text = q.poll();
				if (text != '.') {
					map[count][j] = text;
					count--;
				}

			}
		}

	}

}
