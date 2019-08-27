import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
// 메모리제이션 사용
// 손으로 배열 그려서 이해하면 더 쉬움.
public class Main로마숫자만들기 {
	// 고정 값이므로 static로 선언
	static int[] num = {1,5,10,50};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1 ≤ N ≤ 20
		int N = Integer.parseInt(br.readLine());
		
		// 중복 체크 배열 -> 최대크기가 50*20 이므로 1001배열 크기 만들어놓음.
		// 1~N까지 한칸씩 내려가면서 더해줌.
		int[][] arr = new int[N+1][1001];
		
		// 시작 초기화
		for(int i=0; i<4; i++) {
			arr[1][num[i]]=1;
		}
		
		// 2~N까지 한칸씩 내려감.
		for (int i = 2; i < N + 1; i++) {
			// 전체 크기 배열 돌면서 중복여부체크하고 값표시
			for (int j = 1; j < 1001; j++) {
				for (int k = 0; k < 4; k++) {
					// 그 전에 있던 숫자에서 num[k] 만큼 더해줘서 만약 아직 체크안되있으면 1로 체크함.
					if (arr[i - 1][j] == 1 && arr[i][j + num[k]] == 0) {
						arr[i][j + num[k]] = 1;
					}
				}
			}
		}
		
		int cnt=0;
		// 마지막N 배열 중에서 체크된거 갯수 세기. 
		for(int i=1; i<1001; i++) {
			if(arr[N][i]==1) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}

}
