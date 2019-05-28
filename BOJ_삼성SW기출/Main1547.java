package baekjun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
// 공
public class Main1547 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int[] cup = new int[4];
		
		// 컵 번호 1 2 3 넣음
		for(int i=1; i<4; i++) {
			cup[i]=i;
		}
		
		int a=0, b=0;
		for(int i=0; i<N; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			
			// 바꿀 숫자들
			int x = Integer.parseInt(token.nextToken());
			int y = Integer.parseInt(token.nextToken());
			
			// 바꿀 숫자의 인덱스 값 저장
			for(int j=1; j<4; j++) {
				if(cup[j]==x) {
					 a = j;
				}
				else if(cup[j]==y) {
					 b = j;
				}
			}
			swap(cup, a, b);
		}
		
		System.out.println(cup[1]);

	}
	
	static void swap(int[] cup,int a,int b) {
		int tmp = cup[a];
		cup[a] = cup[b];
		cup[b] = tmp;
	}

}
