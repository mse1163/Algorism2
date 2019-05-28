import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Solution {
    static class Point{
        int x,y, cnt;
        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
        @Override
        public String toString() {
            return x + ", " + y + ", " + cnt ;
        }
         
    }
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
         
        for(int t=1; t<=T; t++) {
             
             N = Integer.parseInt(br.readLine());
             
             map = new int[N][N];
             
            for(int i=0; i<N; i++) {
                StringTokenizer token = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(token.nextToken());
                }
            }
             
            max = 0; same=0;
            // 완전 탐색
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    q.add(new Point(i, j, 1));
                    // 시작값 기억
                    start = map[i][j];
                    bfs();
                }
            }
             
            sb.append("#").append(t).append(" ").append(same).append(" ").append(max).append("\n");
             
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
     
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] map;
    static int N, max, same, start;
    static Queue<Point> q = new LinkedList<>();
    static void bfs() {
         
        while(!q.isEmpty()) {
         
            Point node = q.poll();
             
            int x = node.x;
            int y = node.y;
            int cnt = node.cnt;
             
            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                 
                if(nx<0 || ny<0 || nx>=N || ny>=N) {
                    continue;
                }
                 
                if(map[nx][ny]== map[x][y]+1) {
                    q.add(new Point(nx, ny, cnt+1));
                }
            }
            // cnt가 큰곳 찾기 , 처음 시작값 같이 저장    
            if(max<cnt) {
                max = cnt;
                same = start;
            }
            // cnt가 같을경우, 시작값이 원래 저장된 값보다 더 작으면 시작값을 바꿔줌
            else if( max == cnt) {
                if(same>start) {
                    same = start;
                }
            }           
        }
    }
}