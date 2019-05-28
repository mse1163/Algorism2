package BOJ;

import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
// 촌수 찾기
public class Main2644 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		// 비교대상
		int a = sc.nextInt();
		int b = sc.nextInt();
		
		int M = sc.nextInt();
		
		int[][] map = new int[N+1][N+1];
		
		// 최소값을 찾는거므로 초기화를 최대값으로 해줌
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				map[i][j] = 987654321;
			}
		}
		
		
		for(int i=0; i<M; i++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			
			map[n][m]=1;
			map[m][n]=1;
		}
		
//		for(int i=1; i<N+1; i++) {
//			for(int j=1; j<N+1; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		// 플로이드 와샬 이용
		for(int mid = 1; mid<N+1; mid++) {
			for(int start=1; start<N+1; start++) {
				for(int end=1; end<N+1; end++) {
					if(map[start][end] > map[start][mid]+map[mid][end]) {
						map[start][end] = map[start][mid]+map[mid][end];
					}
				}
			}
		}
		
		if(map[a][b]==987654321) {
			System.out.println(-1);
		}
		else {
			System.out.println(map[a][b]);
		}
	}

}
