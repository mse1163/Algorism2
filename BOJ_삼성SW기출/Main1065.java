import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main{

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

        // 1<= N <=1000
		int N = Integer.parseInt(br.readLine());    

		int cnt = 0;
		int[] num = new int[3];

		for (int t = 1; t <= N; t++) {
            // 1~99까지는 모두 등차수열이 되므로 따로 빼줌
			if (0<t && t < 100) {
				cnt=t;
			}
            // 1000은 등차가 아니므로 그냥 break로 끝내줌
			else if(t==1000) {
				break;
			}
            // 100부터 등차인지 아닌지 확인
			else {
				int i = t, k=0;
                // 3자리수를 배열에 담아줌
                // 역순으로 담기는데 등차인지 아닌지 찾는거므로 상관없음
				while(i>0) {
					num[k] = i%10;
					i = i/10;
					k++;
				}

				 //System.out.println(Arrays.toString(num));
				 // 3자리수만 비교하면 되므로 이렇게 해도 됨
				 if(num[0]-num[1] == num[1]-num[2]) {
					 cnt++;
				 }
			}
		}
		sb.append(cnt);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
	}
}