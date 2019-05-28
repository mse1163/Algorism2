import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
// 냅색(값 여러개 들고다니기)
public class Main{
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
 
        StringTokenizer token = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(token.nextToken());
        int W = Integer.parseInt(token.nextToken());
 
        int[][] map = new int[N+1][2];
 
        int[][] dp = new int[N + 1][W+1];
 
        for (int i = 1; i < N+1; i++) {
            token = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(token.nextToken());
            map[i][1] = Integer.parseInt(token.nextToken());
        }
 
        for (int i = 1; i < N+1; i++) {
            for (int j = 0; j < W+1; j++) {
                // 현재 배낭 무게가 주어진 무게보다 작으면 값 덮어씌움
                if (j < map[i][0])
                    dp[i][j] = dp[i - 1][j];
                // 자신의 무게전 값 + 현재 자신 , 현재자신 바로 위 값 비교
                else
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - map[i][0]] + map[i][1]);
 
            }
        }
         
        sb.append(dp[N][W]);
        bw.write(sb.toString());
        bw.flush();
        bw.close(); }
 
}
