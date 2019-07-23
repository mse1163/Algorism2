import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 알파벳이..A~Z까지인거 못보고..무조건 10까지인줄...
// 배열을 10개짜리 만들어놔서 런타임에러 발생..ㅠㅠ
public class Main단어수학{

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		// 알파벳 기억할 배열. A~Z
		int[] alpa = new int[26];
		int num=0, idx=-1;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			num = 1;
			// 뒤에서부터 1의자리, 10의자리..알파벳 중복된것도 누적해서 저장.
			for(int j=str.length()-1; j>=0; j--) {
				idx = str.charAt(j)-'A';
				alpa[idx] += num;
				num*=10;
				
//				System.out.print(idx+" ");
			}
		}
//		System.out.println(Arrays.toString(alpa));
		
		// 오름차순 정렬.
		Arrays.sort(alpa);
		
		int sum=0, cnt=9;
		
		for(int i=alpa.length-1; i>=0; i--) {
			// 0이면 끝
			if(alpa[i]==0) {
				break;
			}
			else {
				sum += alpa[i]*cnt;
				cnt--;
			}
		}
		
		System.out.println(sum);

	}
}
