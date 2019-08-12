import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
// 배열 회전하는게 어려움..
// for문 4개 사용하는거 말고 다른 방법이 있는지 생각해 봐야함..
// 시간이 너무 오래 걸림..
public class Main배열돌리기4 {
   static class Point {
      int r, c, s;

      public Point(int r, int c, int s) {
         super();
         this.r = r;
         this.c = c;
         this.s = s;
      }

      @Override
      public String toString() {
         return "Point [r=" + r + ", c=" + c + ", s=" + s + "]";
      }

   }

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      StringTokenizer token = new StringTokenizer(br.readLine());

      N = Integer.parseInt(token.nextToken());
      M = Integer.parseInt(token.nextToken());
      K = Integer.parseInt(token.nextToken());

      int[][] map = new int[N + 1][M + 1];
      for (int i = 1; i < N + 1; i++) {
         token = new StringTokenizer(br.readLine());
         for (int j = 1; j < M + 1; j++) {
            map[i][j] = Integer.parseInt(token.nextToken());
         }
      }
      
      // 회전 연산 저장.
      Point[] arrloc = new Point[K];

      for (int i = 0; i < K; i++) {
         token = new StringTokenizer(br.readLine());
         // r,c,s
         arrloc[i] = new Point(Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()),Integer.parseInt(token.nextToken()));
      }
      
      ans=987654321;
      perm(map, arrloc, 0);
      System.out.println(ans);

   }

   static int N, M, K,ans;
   
   // 회전 연산할 순서 모든 경우의수 정하기 -> 순열.
   static void perm(int[][] map, Point[] arrloc, int idx) {
      if (idx == K) {
    	 // 처음 맵으로 시작하여 배열 회전시키기 위해 맵 전체 복사.
         int[][] tmp = new int[N+1][M+1];
         deepCopy(tmp, map);

         for (int i = 0; i < K; i++) {
//            System.out.print(arrloc[i] + " ");
        	 
        	// 회전 연산 순서대로 배열 회전.
            location(tmp, arrloc[i]);

         }
         
         // 각 행의 합 중에서 최소값 찾기.
         for(int i=1; i<N+1; i++) {
        	 int sum=0;
        	 for(int j=1; j<M+1; j++) {
        		 sum+=tmp[i][j];
        	 }
        	 
        	 ans = Math.min(ans, sum);
         }
         return;
      }
      
      for (int i = idx; i < K; i++) {
         swap(arrloc, i, idx);
         perm(map, arrloc, idx + 1);
         swap(arrloc, i, idx);
      }
   }

   static ArrayList<Point> list;
   
   // 배열 회전하기.
   static void location(int[][] map, Point arr) {
	   // 회전시킬 위치와 값을 저장할 리스트
      list = new ArrayList<>();
      
      // 시작점.
      int sx = arr.r - arr.s;
      int sy = arr.c - arr.s;
      
      // 끝점
      int ex = arr.r + arr.s;
      int ey = arr.c + arr.s;
      
      // 시작점을 기준으로 K개 만큼 배열회전 구간 생김.
      for (int k = 0; k < arr.s; k++) {
//    	  System.out.println(sx+", "+sy+", "+ex+", "+ey);
    	  
    	  // 위 왼 -> 위 오른
         for (int y = sy; y <= ey; y++) {
            if (y == ey) {
               list.add(new Point(sx + 1, ey, map[sx][y]));
            } else {
               list.add(new Point(sx, y + 1, map[sx][y]));
            }
         }
         
         // 위 오른 -> 아래 오른
         for (int x = sx + 1; x <= ex; x++) {
        	 // 마지막 끝점은 항상 방향바꿔서 이동.
            if (x == ex) {
               list.add(new Point(ex, ey - 1, map[x][ey]));
            }
            // 다음 칸으로 위치 이동
            else {
               list.add(new Point(x + 1, ey, map[x][ey]));
            }
         }
         
         // 아래 오른 -> 아래 왼
         for (int y = ey - 1; y >= sy; y--) {
            if (y == sy) {
               list.add(new Point(ex - 1, sy, map[ex][y]));
            } 
            else {
               list.add(new Point(ex, y - 1, map[ex][y]));
            }
         }
         
         // 아래 왼 -> 위 왼
         for (int x = ex - 1; x >= sx + 1; x--) {
            list.add(new Point(x - 1, sy, map[x][sy]));
         }
         
         // 이동시킨 위치 맵에 뿌리기.
         move(map, list);
         
         // 배열의 중심점 될때까지 안쪽 배열 회전
         sx++; sy++; ex--; ey--;
      }
   }
   
   // 이동시킨 위치 맵에 뿌리기.
   private static void move(int[][] map, ArrayList<Point> list2) {
      for (int i = 0; i < list.size(); i++) {
         int x = list.get(i).r;
         int y = list.get(i).c;
         int cnt = list.get(i).s;

         map[x][y] = cnt;
      }

   }
   
   private static void swap(Point[] arr, int a, int b) {
      Point tmp = arr[a];
      arr[a] = arr[b];
      arr[b] = tmp;

   }
   
   // 맵 복사.
   private static void deepCopy(int[][] tmp, int[][] map) {
      for (int i = 1; i < N + 1; i++) {
         for (int j = 1; j < M + 1; j++) {
            tmp[i][j] = map[i][j];
         }
      }

   }
}
