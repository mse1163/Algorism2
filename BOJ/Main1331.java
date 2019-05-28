import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

// 나이트 투어
// 체스에서 나이트라는거를 몰라서 문제를 이해하지 못했음
// 모두 다 방문했는지 여부 확인 안해주면 틀림.
// 시작점 방문체크 안해줫더니 틀렷음...
public class Main {
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
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		map = new int[7][7];
		for (int i = 0; i < 36; i++) {
			String text = br.readLine();
			char t = text.charAt(0);

			int tx = text.charAt(1) - '0';
			
			// 우리가 흔히 쓰는 2차배열로 사용하기 위해 좌표 바꿔줌.
			int x = changX(tx);
			int y = changY(t);
			
			// 전체를 리스트에 담아서 확인.
			list.add(new Point(x, y));
		}
		
		bfs();
		
		if (ans==33) {
			bw.write("Invalid");
		} else {
			bw.write("Valid");
		}
		bw.flush();
		bw.close();
	}

	static int[][] map;
	static int ans=-1;
	static ArrayList<Point> list = new ArrayList<>();
	static int[] dx = { -2, -2, 2, 2, -1, 1, -1, 1 };
	static int[] dy = { -1, 1, -1, 1, -2, -2, 2, 2 };

	static void bfs() {
		// 시작점 방문체크 -> 이거 안해줫더니 틀렷음..
		map[list.get(0).x][list.get(0).y]=1;
		
		for (int k = 1; k < list.size(); k++) {
			
			int x = list.get(k - 1).x;
			int y = list.get(k - 1).y;
			
			// 그전에 있던 곳에서 나이트 8방향으로 갈수 있는지 확인
			for (int i = 0; i < 8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 1 || nx >= 7 || ny < 1 || ny >= 7) {
					continue;
				} 
				else {
					if (nx == list.get(k).x && ny == list.get(k).y) {
						
						// 갈 수 있는데 이미 방문했으면 리턴.
						if(map[nx][ny]==1) {
							ans=33;
							return;
						}
						// 갈수 있고 방문도 아직 안했음.
						map[nx][ny]=1;
						break;
					}
				}
			}
		}
        
        // 모든곳 방문했는지 확인
		for(int i=1; i<7; i++) {
			for(int j=1; j<7; j++) {
				if(map[i][j]==0) {
					ans=33;
					return;
				}
			}
		}
        
		// 끝점에서 다시 시작점으로 갈수 있는지 확인
		int ex = list.get(35).x;
		int ey = list.get(35).y;
		
		boolean isok = false;
		for(int i=0; i<8; i++) {
			int nx = ex + dx[i];
			int ny = ey + dy[i];
			
			if(nx==list.get(0).x && ny==list.get(0).y) {
				isok = true;
				return;
			}
		}
		
		if(!isok) {
			ans=33;
		}
	}
	
	// 알파벳을 좌표로 바꿈
	static int changY(char t) {
		if (t == 'A') {
			return 1;
		} else if (t == 'B') {
			return 2;
		} else if (t == 'C') {
			return 3;
		} else if (t == 'D') {
			return 4;
		} else if (t == 'E') {
			return 5;
		} else if (t == 'F') {
			return 6;
		}
		return -1;
	}
	
	// 흔히 아는 좌표로 바꿈
	static int changX(int x) {
		if (x == 6) {
			return 1;
		} else if (x == 5) {
			return 2;
		} else if (x == 4) {
			return 3;
		} else if (x == 3) {
			return 4;
		} else if (x == 2) {
			return 5;
		} else if (x == 1) {
			return 6;
		}
		return 0;
	}

}
