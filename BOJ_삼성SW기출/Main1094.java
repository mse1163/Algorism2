package baekjun;

import java.util.Scanner;
// 막대기
public class Main1094 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int X = sc.nextInt();	// 만들고 싶은 길이
		
		int stick = 64;
		
		if(X==64) {
			System.out.println(1);
			return;
		}
		
		int sum=0, cnt=0;
		while(true) {
			// 막대기 절반으로 나누고 sum에 더해줌
			stick = stick/2;
			sum+= stick;
			
			if(sum>X) {
				// sum이 X보다 크면 나눈 절반을 버려야 하므로 더해준거에서 다시 빼준다.
				sum-=stick;
			}
			
			else if(sum<X) {
				// sum이 X보다 작으면 sum에 포함되어있으므로 cnt증가
				cnt++;
			}
			else if(sum==X) {
				// 같으면 결과 출력해야 하는데 마지막에 나눈것도 sum에 더해줘서 가져온거므로 결과출력 전에 cnt증가
				cnt++;
				System.out.println(cnt);
				break;
			}
		}
		
	}

}
