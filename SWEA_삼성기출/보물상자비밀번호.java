import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution보물상자비밀번호 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(token.nextToken());
			int K = Integer.parseInt(token.nextToken());
			
			String str = br.readLine();
			
			// 입력받은 암호를 리스트에 하나씩 넣음
			ArrayList<Character> list = new ArrayList<>();
			for(int i=0; i<str.length(); i++) {
				list.add(str.charAt(i));
			}
			
			// 중복 없이 넣어야 하므로 hashset사용
			HashSet<Integer> hs = new HashSet<>();
			String num = "";
			
			// N/4만큼 돌면 결국 처음이랑 같아짐
			for(int k=0; k<N/4; k++) {
//				System.out.println(list.toString());
			int j=0;
			for(int i=0; i<N; i++){
				
				num += list.get(i);
				// N/4만큼 쪼개서 넣음.
				if(j==N/4-1) {
					j=0;
					// 16진수를 10진수로 변환하고 hs에 넣어줌.
					int result = Integer.parseInt(num, 16);
					hs.add(result);
					
					// num을 비워줘야 새로 다시 값을 받음.
					num="";
				}
				else {
					j++;
				}
			}
			
			// 맨 뒤에거를 맨 앞에 넣고 맨뒤에거 삭제하면 한칸씩 뒤로 움직임.
			list.add(0, list.get(list.size()-1));
			list.remove(list.size()-1);
			
			}
			
//			System.out.println(hs.toString());
			// 정렬하려고 다시 리스트에 넣어줌
			ArrayList<Integer> sort = new ArrayList<>(hs);
			Collections.sort(sort);
			
//			// 반대로 뒤집어서 내림차순으로 정렬 - 시간이 더 많이 걸림..
//			Collections.reverse(sort);
			
//			System.out.println(sort.toString());
			
			// 반대로 안뒤집고 할경우 -> 전체사이즈에서 K만큼 빼면 오름차순정렬로 되있으므로 값 같음.
			System.out.println("#"+t+" "+sort.get(sort.size()-K));
//			// 반대로 뒤집을 경우
//			System.out.println("#"+t+" "+sort.get(K-1));
			
		}

	}

}
