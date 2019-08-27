import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 흔히 생각하는 리스트에 넣어서 제거하는 방식은 메모리초과
// 왼쪽, 오른쪽으로 확인하면서 입력받은 배열은 건들지 않고 해야 조건안에 들어올수 있음.
// 조금 어려운 문제.
public class MainRBY팡{

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		ans = -1;
		// 전체 배열 확인.
		for (int i = 0; i < N; i++) {
			move(i);
		}
		System.out.println(N-ans);
	}

	static int[] arr;
	static int N, remove, ans;
	
	// 움직임.
	private static void move(int i) {
		// 초기값 저장.
		int tmp = arr[i];
		int change_num;
		
		for (int k = 1; k < 4; k++) {
			// 초기값 제외하고 1,2,3중 값 바꾸기.
			if (tmp != k) {
				// 제거한 구슬 수
				remove=0;
				// 바꾼 숫자 저장.
				change_num = k;
				// 터치기.
				boom(i, i, change_num, 1);
				ans = Math.max(remove, ans);
			}
		}
	}
	
	// 터치기.
	private static void boom(int left, int right, int change_num, int cnt) {
		// 왼쪽, 오른쪽으로 한칸씩 이동하면서 터치는거 확인.
		left--;
		right++;
		
		while (true) {
			// 제거한 것이 하나라도 있는지 확인 여부
			boolean isok = false;
			// 왼쪽 범위 안에 있으면
			if (left >= 0) {
				// 왼쪽에 있는 값이랑 바꾼 숫자랑 같으면
				if (arr[left] == change_num) {
					// 왼쪽으로 한칸 더 이동
					left--;
					// 제거할 카운트 +1
					cnt++;
					// 제거할거 있으므로 true 체크
					isok = true;
				}
			}
			
			// 오른쪽 범위 안에 있으면
			if (right < N) {
				// 오른쪽에 있는 값이랑 바꾼 숫자랑 같으면
				if (arr[right] == change_num) {
					// 오른쪽 한칸 이동
					right++;
					// 제거할 카운트+1
					cnt++;
					// 제거할 거 있으므로 true 체크
					isok = true;
				}
			}
			
			//제거할게 없으면 끝. 아니면 반복
			if(!isok) {
				break;
			}
		}
		
		// 만약 제거할 구슬이 4개 이상이면 remove에 저장.
		if (cnt >= 4) {
			remove += cnt;
			
			// 왼쪽 오른쪽 범위 안에 있으면서 왼쪽과 오른쪽 값이 같으면 다시 터지기
			if(left>=0 && right<N && arr[left]==arr[right]) {
				boom(left, right, arr[left], 2);
			}
		} 
	}
}
