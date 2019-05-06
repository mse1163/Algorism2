import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 무한루프 처리가 잘 안맞았음..
// 방문체크를 방향이 바뀔때만 그 방향으로 기억해놓은게 핵심?이엿음.ㅋㅋ
// 범위 벗어나면 끝난다는 조건을 방향이 바뀌면서 벗어나는 경우도 봐줬어야함.
// 조금 어려운..?까다로운 문제엿음..
public class Main3987 {
	static class Point {
		int x, y, loc;

		public Point(int x, int y, int loc) {
			super();
			this.x = x;
			this.y = y;
			this.loc = loc;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", loc=" + loc + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());

		char[][] map = new char[N + 1][M + 1];
		for (int i = 1; i < N + 1; i++) {
			String str = br.readLine();
			for (int j = 1; j < M + 1; j++) {
				map[i][j] = str.charAt(j - 1);
			}
		}

		token = new StringTokenizer(br.readLine());
		// 시작점
		int sx = Integer.parseInt(token.nextToken());
		int sy = Integer.parseInt(token.nextToken());

		boolean isok = false;
		int result = 0, max = -1;
		
		// 4방향 다 확인해야하므로 포문
		for (int i = 1; i < 5; i++) {
			q.add(new Point(sx, sy, i));
			
			// 방문한곳 체크. 단, 방향이 바뀔때(/, \)만 방향만 체크해놓음. 
			// 같은위치에 같은방향으로 들어오면 결국 같은 길을 밟게 되므로...
			int[][] check = new int[N + 1][M + 1];

			move(map, check);
			
			// 같은 코스를 계속 밟음.. 무한루프
			if (ans == -2) {
				isok = true;
				// i에 해당하는 방향 
				char loc = location(i);
				bw.write(loc + "\n" + "Voyager");
				break;
			}
			// 정상적으로 돌다가 끝남. 최대값이랑 방향 기억해놓음
			else if (max < ans) {
				max = ans;
				result = i;
			}

//			System.out.println(i+" "+max);
			
			// 범위 벗어나서 끝나는 경우도 있어서 큐를 비워줘야함.
			q.clear();
		}
		
		// 무한루프 아닐때
		if (!isok) {
			char loc = location(result);
			bw.write(loc + "\n" + max + "");
		}
		bw.flush();
		bw.close();

	}

	static int N, M, ans;
	static Queue<Point> q = new LinkedList<>();
	
	// 움직임.
	static void move(char[][] map, int[][] check) {
		// 처음 시작점도 포함해줘야하므로 1부터 시작
		int cnt = 1;
		ans = 0;
		while (!q.isEmpty()) {
			Point node = q.poll();
			int x = node.x;
			int y = node.y;
			int loc = node.loc;

			// 위
			if (loc == 1) {
				x -= 1;
				
				// 범위 벗어나거나 블랙홀일 경우 끝냄
				if (x == 0 || map[x][y] == 'C') {
					ans = cnt;
					break;
				}
				// 방문 아직 안했고 방향 바꿔줌.
				else if (map[x][y] == '/' && check[x][y] != loc) {
					// 이동시간 증가
					cnt++;
					// 방문 체크에 방향으로 해줌.
					check[x][y] = loc;
					// 방향 바꿔서 넣어줌
					q.add(new Point(x, y, 2));
					
				} else if (map[x][y] == '\\' && check[x][y] != loc) {
					cnt++;
					check[x][y] = loc;
					q.add(new Point(x, y, 4));
					
				} 
				// 일반 땅은 그냥 시간 증가해주고 같은 방향으로 넣어줌
				else if (map[x][y] == '.') {
					cnt++;
					q.add(new Point(x, y, loc));
				} 
				// 방문했었는데 그곳이 현재 나랑 같은 방향으로 이미 지나갔음. -> 무한루프 돈다는 의미
				else if (check[x][y] == loc) {
					ans = -2;
					return;
				}
			}
			
			// 오른쪽
			else if (loc == 2) {
				y += 1;

				if (y == M + 1 || map[x][y] == 'C') {
					ans = cnt;
					break;
				} else if (map[x][y] == '/' && check[x][y] != loc) {
					cnt++;
					check[x][y] = loc;
					q.add(new Point(x, y, 1));
				} else if (map[x][y] == '\\' && check[x][y] != loc) {
					cnt++;
					check[x][y] = loc;
					q.add(new Point(x, y, 3));
				} else if (map[x][y] == '.') {
					cnt++;
					q.add(new Point(x, y, loc));
				} else if (check[x][y] == loc) {
					ans = -2;
					return;
				}

			}
			// 아래
			else if (loc == 3) {
				x += 1;

				if (x == N + 1 || map[x][y] == 'C') {
					ans = cnt;
					break;
				} else if (map[x][y] == '/' && check[x][y] != loc) {
					cnt++;
					check[x][y] = loc;
					q.add(new Point(x, y, 4));
				} else if (map[x][y] == '\\' && check[x][y] != loc) {
					cnt++;
					check[x][y] = loc;
					q.add(new Point(x, y, 2));
				} else if (map[x][y] == '.') {
					cnt++;
					q.add(new Point(x, y, loc));
				} else if (check[x][y] == loc) {
					ans = -2;
					return;
				}

			}
			// 왼쪽
			else if (loc == 4) {
				y -= 1;

				if (y == 0 || map[x][y] == 'C') {
					ans = cnt;
					break;
				} else if (map[x][y] == '/'&& check[x][y] != loc) {
					cnt++;
					check[x][y] = loc;
					q.add(new Point(x, y, 3));
				} else if (map[x][y] == '\\'&& check[x][y] != loc) {
					cnt++;
					check[x][y] = loc;
					q.add(new Point(x, y, 1));
				} else if (map[x][y] == '.') {
					cnt++;
					q.add(new Point(x, y, loc));
				} else if (check[x][y] == loc) {
					ans = -2;
					return;
				}

			}

		}
	}
	
	// 방향으로 출력
	static char location(int loc) {
		if (loc == 1) {
			return 'U';
		} else if (loc == 2) {
			return 'R';
		} else if (loc == 3) {
			return 'D';
		} else if (loc == 4) {
			return 'L';
		}
		return '0';
	}
}
