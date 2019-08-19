import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//이차배열리스트 사용안하고 배열만 사용하여 시간 줄이려 했더니...
// 시간초과남..
// 이차배열리스트 사용하는게 더 시간이 적게 걸리나봐요..
public class Main나무재테크 {
	static class Point {
		int x, y, age;

		public Point(int x, int y, int age) {
			super();
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", age=" + age + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		K = Integer.parseInt(token.nextToken());

		A = new int[N + 1][N + 1];
		food = new int[N + 1][N + 1];

		for (int i = 1; i < N + 1; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				food[i][j] = 5;
				A[i][j] = Integer.parseInt(token.nextToken());
			}
		}

		tree = new ArrayList[N + 1][N + 1];
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				tree[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < M; i++) {
			token = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(token.nextToken());
			int y = Integer.parseInt(token.nextToken());
			int age = Integer.parseInt(token.nextToken());

			tree[x][y].add(age);
		}

		ans = 0;
		tree_breeding();
		System.out.println(ans);

	}

	static int N, M, K, ans;
	static ArrayList<Integer>[][] tree;
	static ArrayList<Point> die = new ArrayList<>();
	static Queue<Point> q = new LinkedList<>();
	static int[][] food, A;
	static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };
	
	// 나무 번식
	static void tree_breeding() {
		// K년후 
		for (int k = 0; k < K; k++) {

			Spring();
			Fall();
			Winter();

		}
	}
	
	// 봄
	private static void Spring() {
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				// 나무가 있고.
				if (!tree[i][j].isEmpty()) {
					// 여러개면 오름차순으로 정렬.
					if(tree[i][j].size()>1) {
						Collections.sort(tree[i][j]);
					}
					
					//나이만큼 양분을 먹으므로 양분이 나이보다 많아야함.
					for (int k = 0; k < tree[i][j].size(); k++) {
						int age = tree[i][j].get(k);
						// 양분 먹을 수 있으면
						if (food[i][j] >= age) {
							food[i][j] -= age;
							// 양분 먹고 나이+1
							tree[i][j].set(k, age + 1);
							
							// 나이가 5의 배수면 가을에 번식.
							if ((age + 1) % 5 == 0) {
								q.add(new Point(i, j, age + 1));
							}
						} 
						// 양분이 부족하면 죽은 나무 리스트에 저장.
						else {
							die.add(new Point(i, j, k));
						}

					}
					
					// 죽을 나무가 있으면 여름에 죽음.
					if (!die.isEmpty()) {
						Summer();
						die.clear();
					}
				}
			}
		}

	}
	
	// 여름
	private static void Summer() {
		// 나무를 제거하면 i만큼 index가 땡겨지므로 idx-i 로 인덱스값 찾음
		for (int i = 0; i < die.size(); i++) {
			Point node = die.get(i);
			int idx = node.age;
			food[node.x][node.y] += tree[node.x][node.y].get(idx - i) / 2;
			tree[node.x][node.y].remove(idx - i);
		}
	}
	
	// 가을
	private static void Fall() {
		// 나이가 5의 배수인 나무위치에서 8방향으로 번식
		while (!q.isEmpty()) {
			Point node = q.poll();

			int x = node.x;
			int y = node.y;

			for (int k = 0; k < 8; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];

				if (nx < 1 || ny < 1 || nx >= N + 1 || ny >= N + 1) {
					continue;
				}

				tree[nx][ny].add(0, 1);
			}
		}
	}
	
	// 겨울에 양분 저장. 나무 갯수 여기서 바로 세버림.
	private static void Winter() {
		ans = 0;
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				ans += tree[i][j].size();
				food[i][j] += A[i][j];
			}
		}
	}
}
