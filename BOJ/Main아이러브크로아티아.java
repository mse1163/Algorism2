import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// IM수준 문제임..ㄷㄷ
// 굉장히 쉬움.
public class Main아이러브크로아티아 {

	static class Point{
		int time; String ans;

		public Point(int time, String ans) {
			super();
			this.time = time;
			this.ans = ans;
		}

		@Override
		public String toString() {
			return "Point [time=" + time + ", ans=" + ans + "]";
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int start = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		
		Point[] t = new Point[N];
		StringTokenizer token;
		
		// 시간 세기
		int boom_time = 0;
		
		for(int i=0; i<N; i++) {
			token = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(token.nextToken());
			String ans = token.nextToken();
			
			// 시간, 대답 저장.
			t[i] = new Point(time, ans);
		}
		
//		System.out.println(Arrays.toString(t));
		
		// 문제수 N만큼 반복
		for(int i=0; i<N; i++) {
			boom_time += t[i].time;
			
			// 3분30초 = 210초 지나면 끝
			if(boom_time>=210) {
				System.out.println(start);
				return;
			}
			
			// 정답 맞추면 다음사람 넘어감.
			if(t[i].ans.equals("T")) {
				start++;
				if(start==9) {
					start = 1;
				}
			}
		}
	}

}
