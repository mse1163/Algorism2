import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
// 경사로 
public class Main14890 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(token.nextToken());
		int L = Integer.parseInt(token.nextToken());
		
		int[][] map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			token = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//			
//		}
		
		int cnt=0;
		// 가로 -> 포문 할때 i,j
		for(int i=0; i<N; i++) {
			boolean isOk = true;
			boolean[] check = new boolean[N];
			// j+1로 뒤에거랑 비교하니까 j범위가 N-1임
			for(int j=0; j<N-1; j++) {
				// 계속 같은 숫자면 넘어감
				if(map[i][j]==map[i][j+1]) {
					continue;
				}
				// 숫자가 나보다 1 작은거 나온 경우
				else if(map[i][j]-1==map[i][j+1]) {
					// L길이만큼 높이가 같아야되서 포문으로 돌림
					for(int k=0; k<L; k++) {
						// 범위 확인, L갯수만큼 숫자가 같은지 확인, 경사로 이미 놓았던곳인지 확인
						if(j+1+k>=N || map[i][j+1]!=map[i][j+1+k] ||  check[j+1+k]) {
							// 그 전에 체크해놓은거 리셋시키기위해서 넣어놓음
							check = new boolean[N];
							isOk = false;
							break;
						}
						else {
							// 경사로 놓은거 체크
							check[j+1+k]=true;
						}
						
					}
				}
				// 숫자가 나보다 1 큰게 나온 경우
				else if(map[i][j]+1==map[i][j+1]) {
					for(int k=0; k<L; k++) {
						if(j-k < 0 || map[i][j]!=map[i][j-k] ||  check[j-k]) {
							check = new boolean[N];
							isOk = false;
							break;
						}
						else {
							check[j-k]=true;
						}
					}
				
				}
				// 숫자가 차이가 1보다 크게 나면 여기로 옴.
				else {
					isOk=false;
					break;
				}
			}
			if(isOk) {
				//System.out.println("i"+i+"번째");
				cnt++;
			}
		}
		
		// 세로 -> 포문 할때 j,i
		for(int j=0; j<N; j++) {
			boolean isOk = true;
			boolean[] check = new boolean[N];
			for(int i=0; i<N-1; i++) {
				
				if(map[i][j]==map[i+1][j]) {
					continue;
				}
				else if(map[i][j]-1==map[i+1][j]) {
					for(int k=0; k<L; k++) {
						
						if(i+1+k>=N || map[i+1][j]!=map[i+1+k][j] ||  check[i+1+k]) {
							check = new boolean[N];
							isOk = false;
							break;
						}
						else {
							check[i+1+k]=true;
						}
						
					}
				}
				else if(map[i][j]+1==map[i+1][j]) {
					for(int k=0; k<L; k++) {
						if(i-k < 0 || map[i][j]!=map[i-k][j] || check[i-k]) {
							check = new boolean[N];
							isOk = false;
							break;
						}
						else {
							check[i-k]=true;
						}
					}
				
				}
				else {
					isOk=false;
					break;
				}			
			}
			if(isOk) {
				//System.out.println("j"+j+"번째");
				cnt++;
			}
			
		}
		
		sb.append(cnt);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
