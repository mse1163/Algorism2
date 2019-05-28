package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
// 뱀
// 처음에 큐에 넣어서 머리위치에서 몸길이를 뺀 위치를 꼬리라 생각하여 거기를 방문false로 바꿔줬음
// 직선으로 움직일때는 되지만 뱀이 방향을 바꾸면 머리랑 꼬리가 꺽여있어서 몸길이만큼 빼서 사용못함
// 그래서 Deque를 이용하여 머리는 앞으로 넣고 꼬리는 뒤에서 빼주는 식으로 해결함.
public class Main3190 {
	static class Point {
		int time;
		String loc;

		public Point(int time, String loc) {
			super();
			this.time = time;
			this.loc = loc;
		}

		@Override
		public String toString() {
			return "Point [time=" + time + ", loc=" + loc + "]";
		}

	}

	static class Node {
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
			
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());

		int K = Integer.parseInt(br.readLine());

		int[][] map = new int[N + 1][N + 1];
		for (int i = 0; i < K; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(token.nextToken());
			int y = Integer.parseInt(token.nextToken());

			map[x][y] = 1;

		}

//		for (int i = 1; i < N + 1; i++) {
//			for (int j = 1; j < N + 1; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}

		int L = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		for (int i = 0; i < L; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(token.nextToken());
			String loc = token.nextToken();
			list.add(new Point(t, loc));
		}

		ans = 0;
		move(map);
		
		bw.write(ans+"");
		bw.flush();
		bw.close();
	}

	static int N, ans;

	static List<Point> list;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static Deque<Node> q = new LinkedList<>();
	
	static void move(int[][] map) {
		
		boolean[][] sel = new boolean[N + 1][N + 1];
		int loc = 1;		// 방향
		int cnt=0;			// 이동시간
		sel[1][1]=true;		// 시작점 체크
		q.add(new Node(1, 1));
		
		int k = 0;
		while(true) {
			int x = q.peekFirst().x;
			int y = q.peekFirst().y;
			
			// 명령어 수행
			if (k < list.size()) {
				if (cnt == list.get(k).time) {
					String location = list.get(k).loc;
					// 오른쪽으로 90도 회전
					if (location.equals("D")) {
						if (loc == 3) {
							loc = 0;
						} else {
							loc++;
						}
					} 
					// 왼쪽으로 90도 회전
					else if (location.equals("L")) {
						if (loc == 0) {
							loc = 3;
						} else {
							loc--;
						}
					}
					k++;
				}
			}
			
			
			int nx = x + dx[loc];
			int ny = y + dy[loc];
			
			// 범위 벗어나거나 내 몸이 있는 곳이면 종료
			if (nx < 1 || ny < 1 || nx >= N + 1 || ny >= N + 1 || sel[nx][ny]) {
				// 끝나는 시점도 시간세줌
				ans = cnt + 1;
				return;
			}
			// 사과 있으면 사과 지우고, 방문체크
			if (map[nx][ny] == 1) {
				map[nx][ny] = 0;
				sel[nx][ny] = true;
				q.addFirst(new Node(nx, ny));
				cnt++;
			}
			// 사과 없을때 그냥 방문체크, 꼬리 밀어줌
			else {
				sel[nx][ny] = true;
				// 머리 저장
				q.addFirst(new Node(nx, ny));
				
				int tx = q.peekLast().x;
				int ty = q.peekLast().y;
				// 꼬리 하나 밀어줌(마지막거 삭제)
				sel[tx][ty] = false;
				q.pollLast();
				cnt++;
			}

		}

	}
}
