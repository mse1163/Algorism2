package BOJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
// A->B
// 값을 뒤에서 계산해서 찾는거로 했는데 나누는거라 답이 정확히 안떨어짐..절반맞고 답이 안맞음.
// 앞으로 순서대로 계산해서 큐에 담아서 함...
// 우선순위큐 써서 하려했는데 안됨...
// 로직은 별차이 없는데 큐로 하니까 맞음...
// a,b 범위가 10^9이므로 최악의 경우인 10^9 * 2 하면 터지므로 long으로 해서 계산해줌

public class Main16953 {
	static class Point{
		long a, cnt;

		public Point(long a, long cnt) {
			super();
			this.a = a;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Point [a=" + a + ", cnt=" + cnt + "]";
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long a = sc.nextLong();
		int b = sc.nextInt();
		
		Queue<Point> q = new LinkedList<>();

		q.add(new Point(a, 1));
		long ans = 0;
		
		while(!q.isEmpty()) {
			
			Point node = q.poll();
			long n = node.a;
			long cnt = node.cnt;
			
			if(n<b) {
				// 2곱한거, 1을 오른쪽에 붙인거 둘 다 해봄
				q.add(new Point(n*2, cnt+1));
				q.add(new Point((n*10)+1, cnt+1));
			}
			// 같으면 cnt세고 끝
			else if(n==b) {
				ans = cnt;
				break;
			}
			// 크면 그냥 보내줌
			else if(n>b) {
				continue;
			}
			
		}
		
		if(ans==0) {
			System.out.println(-1);
		}
		else {
			System.out.println(ans);
		}
		
	}

}

