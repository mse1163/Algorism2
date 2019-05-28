import java.util.Scanner;
// 민석이의 과제 제출
public class Solution5431 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			int N = sc.nextInt(); // 수강생 수
			int K = sc.nextInt(); // 과제 제출 수

			boolean[] check = new boolean[N+1];
				// 과제 낸 사람 체크
				for (int j = 0; j < K; j++) {
					int num = sc.nextInt();
					
					check[num] = true;
				}
				
				System.out.print("#"+t+" ");
				// 과제 안낸 사람만 출력 1부터 반복돌면서 확인하므로 정렬해줄 필요없음
				for(int i=1; i<N+1; i++) {
					if(!check[i]) {
						System.out.print(i+" ");
					}
				}
				System.out.println();
			
		}
	}

}
