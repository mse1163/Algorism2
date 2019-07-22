import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 초기화...꼭꼭꼭 기억합시당..!!!!!!!!
// 어려운 문제는 아니였음..
public class Main전구길만걷자 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		String[] str = new String[N];
		
		// 원래 주어진 전구에서 각각 바뀌는 횟수.
		int bult_cnt=0;
		for(int i=0; i<N; i++) {
			str[i] = br.readLine();
			
			for(int j=0; j<str[i].length()-1; j++) {
				// 전구 바뀌는 횟수
				if(str[i].charAt(j)-'0'!=str[i].charAt(j+1)-'0') {
					bult_cnt++;
				}
			}
		}
		
//		System.out.println(Arrays.toString(str));
		
		// 초기화..!! 꼭 해줍시당..ㅠㅠ
		result=987654321;
		change(str,0);
		
		// 원래 전구 횟수 + 순열돌리고 전구 최소 횟수
		result = bult_cnt+ result;
		System.out.println(result);
	}
	
	static int N;
	static int result;
	
	// 순열
	static void change(String[] str, int idx) {
		if(idx==N) {
			// 바뀐 전구 횟수.
			int cnt=0;
			// 처음 전구배열의 끝번호 저장.
			int bult = str[0].charAt(str[0].length()-1)-'0';
			for(int i=1; i<N; i++) {
				
				if(bult!=str[i].charAt(0)-'0'){
						cnt++;
				}
				
				bult = str[i].charAt(str[i].length()-1)-'0';
				
			}
			
			result = Math.min(result, cnt);
			return;
		}
		
		for(int i=idx; i<N; i++) {
			swap(str, idx , i);
			change(str, idx+1);
			swap(str, idx, i);
		}
	}
	
	static void swap(String[] str, int a, int b) {
		String tmp = str[a];
		str[a] = str[b];
		str[b] = tmp;
	}

}
