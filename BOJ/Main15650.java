import java.util.Scanner;

// 조합 재귀함수 이용하여 똑같이 품
public class Main15650 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		int[] arr = new int[N+1];
		int[] visited = new int[M];
		
		for(int i=1; i<=N; i++) {
			arr[i] = i;
		}
		
		combination(arr, visited, 0, 1);
	}
	
	static int N, M;
	
	static void combination(int[] arr,int[] visited, int idx, int cnt) {
		if(idx==M) {                                // 찾고자하는 갯수랑 같으면 리턴
			for(int i=0; i<M; i++) {
				System.out.print(visited[i]+" ");
			}
			System.out.println();
			return;
		}
		
		if(cnt==N+1) {              // 전체 수랑 같으면 리턴
			return;
		}
		
		visited[idx] = arr[cnt];            
		combination(arr, visited, idx+1, cnt+1);
		combination(arr, visited, idx, cnt+1);
	}

}
