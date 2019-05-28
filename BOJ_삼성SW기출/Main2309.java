import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main2309 {
    // 난쟁이 9명중 7명 뽑아야 하므로 조합으로 찾음
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		int[] arr = new int[9]; // 난쟁이 9명
		int[] sel = new int[7]; // 찾을 난쟁이 7명
		
		for(int i=0; i<9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);       // 오른차순 정렬
		recur(arr, sel, 0, 0);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
	}
	static StringBuilder sb = new StringBuilder();
	static void recur(int[] arr, int[] sel, int idx, int cnt) {
		if(idx==sel.length) {
			int sum=0;
			for(int i=0; i<sel.length; i++) {
				sum+= sel[i];
			}
			
            // 난장이 키 합이 100이면 출력
			if(sum==100) {
				
				for(int i=0; i<sel.length; i++) {
					sb.append(sel[i]).append("\n");
				}
				return;
			}
			return;
		}
		
		if(cnt == arr.length) {
			return;
		}
		
		sel[idx] = arr[cnt];
		recur(arr, sel, idx+1, cnt+1);
		recur(arr, sel, idx, cnt+1);
	
	}

}
