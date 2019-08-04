import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 한 공간에 모이는 군집을 처리해주는게 핵심..
// 이차배열 리스트 사용!
public class Solution미생물격리 {
    static class Point {
        int x, y, num, loc;
 
        public Point(int x, int y, int num, int loc) {
            super();
            this.x = x;
            this.y = y;
            this.num = num;
            this.loc = loc;
        }
 
        @Override
        public String toString() {
            return "Point [x=" + x + ", y=" + y + ", num=" + num + ", loc=" + loc + "]";
        }
    }
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        int T = Integer.parseInt(br.readLine());
        StringTokenizer token;
 
        for (int t = 1; t <= T; t++) {
            token = new StringTokenizer(br.readLine());
 
            N = Integer.parseInt(token.nextToken());
            M = Integer.parseInt(token.nextToken());
            K = Integer.parseInt(token.nextToken());
 
            for (int i = 0; i < K; i++) {
 
                token = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(token.nextToken());
                int y = Integer.parseInt(token.nextToken());
                int num = Integer.parseInt(token.nextToken());
                int loc = Integer.parseInt(token.nextToken());
 
                q.add(new Point(x, y, num, loc));
            }
             
            bfs(0);
            bw.write("#"+t+" "+sum+"\n");
            q.clear();
        }
        bw.flush();
        bw.close();
 
    }
 
    static int N, M, K,sum;
    static Queue<Point> q = new LinkedList<>();
    static int[] dx = {0, -1, 1, 0, 0 };
    static int[] dy = {0, 0, 0, -1, 1 };
 
    static void bfs(int m) {
         
        if (m == M) {
            sum=0;
            result();
            return;
        }
        
        // 미생물 담을 배열리스트
        ArrayList<Point>[][] map = new ArrayList[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
         
        while(!q.isEmpty()) {
            Point node = q.poll();
 
            int x = node.x;
            int y = node.y;
            int num = node.num;
            int loc = node.loc;
             
            int nx = x + dx[loc];
            int ny = y + dy[loc];
            
            // 미생물 움직임.
            move(nx, ny, num, loc, map);
        }
         
        Misangmul(map);
        bfs(m + 1);
    }
    
    // 미생물 한공간에 여러개인 경우 처리하기.
    private static void Misangmul(ArrayList<Point>[][] map) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!map[i][j].isEmpty()) {
                	// 미생물군집이 하나이면 큐에 담아줌.
                    if(map[i][j].size()==1) {
                        q.add(map[i][j].get(0));
                    }
                    // 군집이 여러개인 경우.
                    else {
                        int max_num=-1;
                        int max_loc= 0;
                        int sum_num = 0;
                        // 담아져있는 군집 갯수만큼 반복
                        for(int k=0; k<map[i][j].size(); k++) {
                        	// 군집 중 미생물 수 많은 군집의 방향으로 넣어줌.
                            if(map[i][j].get(k).num>max_num) {
                                max_num = map[i][j].get(k).num;
                                max_loc = map[i][j].get(k).loc;
                            }
                            
                            // 군집에 있는 미생물 수는 모두 합친다.
                            sum_num += map[i][j].get(k).num;
                        }
                        // 군집 중 미생물 최대인 군집의 방향과 전체 미생물수 총합.
                        q.add(new Point(i, j, sum_num, max_loc));
                    }
                }
            }
        }
    }
    
    // 남은 미생물 수 총합.
    private static void result() {
         
        while(!q.isEmpty()) {
            sum += q.poll().num;
        }
    }
    
    // 미생물 움직임. 
    private static void move(int nx, int ny, int num, int loc, ArrayList<Point>[][] map) {
    		// 만약 약품색칠 테두리에 가면 미생물수 절반, 반대방향.
            if (nx == 0 || nx==N-1 || ny==0 || ny==N-1) {
                int new_loc = changeLoc(loc);
                map[nx][ny].add(new Point(nx, ny, num / 2, new_loc));
            } 
            // 그냥 이동만 해줌.
            else {
            	
                map[nx][ny].add(new Point(nx, ny, num, loc));
            }
    }
    
    // 반대방향으로 바꾸기.
    private static int changeLoc(int loc) {
        if(loc==1) {
            return 2;
        }
        else if(loc==2) {
            return 1;
        }
        else if(loc==3) {
            return 4;
        }
        else if(loc==4) {
            return 3;
        }
        return 0;
    }
}
