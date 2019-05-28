package baekjun;

import java.util.Scanner;
//역사
public class Main1613 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		int[][] map = new int[n+1][n+1];
		
		// 최대값으로 설정해놓기
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<n+1; j++) {
				if(i==j) {
					continue;
				}
				map[i][j]=987654321;
			}
		}
		
		for(int i=0; i<k; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			map[a][b]=1;
		}
		
		// 플로이드와샬
		for(int mid=1; mid<n+1; mid++) {
			for(int start=1; start<n+1; start++) {
				for(int end=1; end<n+1; end++) {
					map[start][end] = Math.min(map[start][end], map[start][mid]+map[mid][end]);
					
					
				}
			}
		}
		
		int s = sc.nextInt();
		
		for(int i=0; i<s; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			// a가 b보다 더 먼저 지나감
			if(map[a][b]<987654321 && map[b][a]==987654321) {
				System.out.println(-1);
			}
			// b가 a보다 더 먼저 지나감
			else if(map[a][b]==987654321 && map[b][a]<987654321) {
				System.out.println(1);
			}
			// 지나가지 않음
			else {
				System.out.println(0);
			}
		}
	}

}
