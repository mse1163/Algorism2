package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
// 톱니바퀴
public class Main14891 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		// list에 하나씩 넣어줌-입력
		String str = br.readLine();
		for(int i=0; i<8; i++) {
			q1.add(str.charAt(i)-'0');
		}
		str = br.readLine();
		for(int i=0; i<8; i++) {
			q2.add(str.charAt(i)-'0');
		}
		str = br.readLine();
		for(int i=0; i<8; i++) {
			q3.add(str.charAt(i)-'0');
		}
		str = br.readLine();
		for(int i=0; i<8; i++) {
			q4.add(str.charAt(i)-'0');
		}
		
		int K = Integer.parseInt(br.readLine());
		for(int i=0; i<K; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(token.nextToken());
			int loc = Integer.parseInt(token.nextToken());
			
			dfs(start, loc);
		}
		
		// 결과 출력
		int sum=0;
		if(q1.get(0)!=0) {
			sum+=1;
		}
		if(q2.get(0)!=0) {
			sum+=2;
		}
		if(q3.get(0)!=0) {
			sum+=4;
		}
		if(q4.get(0)!=0) {
			sum+=8;
		}
		
		sb.append(sum);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static List<Integer> q1 = new LinkedList<>();
	static List<Integer> q2 = new LinkedList<>();
	static List<Integer> q3 = new LinkedList<>();
	static List<Integer> q4 = new LinkedList<>();
	
	// 시작하는 번호,방향에 따라 움직임
	static void dfs(int start, int loc) {
		
		if(start==1) {
			move1(loc);
		}
		else if(start==2) {
			move2(loc);
		}
		else if(start==3) {
			move3(loc);
		}
		else if(start==4) {
			move4(loc);
		}
	}
	
	// 1번톱니
	static void move1(int loc) {
		//시계방향
		if (loc == 1) {
			//1,2 연결
			if (q1.get(2) != q2.get(6)) 
			{
				//1,2,3연결
				if(q2.get(2)!=q3.get(6)) 
				{
					//1,2,3,4연결
					if(q3.get(2)!=q4.get(6)) 
					{
						location_clock(1);
						location_noclock(2);
						location_clock(3);
						location_noclock(4);
					}
					else 
					{
						location_clock(1);
						location_noclock(2);
						location_clock(3);
					}
				}
				else
				{
					location_clock(1);
					location_noclock(2);
				}
			}
			else
			{
				location_clock(1);
			}
		}
		// 반시계방향
		else {
			if (q1.get(2) != q2.get(6)) 
			{
				if(q2.get(2)!=q3.get(6)) 
				{
					if(q3.get(2)!=q4.get(6)) 
					{
						location_noclock(1);
						location_clock(2);
						location_noclock(3);
						location_clock(4);
					}
					else 
					{
						location_noclock(1);
						location_clock(2);
						location_noclock(3);
					}
				}
				else
				{
					location_noclock(1);
					location_clock(2);
				}
			}
			else
			{
				location_noclock(1);
			}
		}
	}
	
	//2번톱니
	static void move2(int loc) {
		//시계방향
		if (loc == 1) {
			
			if (q2.get(2) != q3.get(6) && q2.get(6)!=q1.get(2)) {
				//1,2,3,4연결
				if(q3.get(2)!=q4.get(6)) {
					location_clock(2);
					location_noclock(1);
					location_noclock(3);
					location_clock(4);
				}
				//1,2,3연결
				else {
					location_clock(2);
					location_noclock(1);
					location_noclock(3);
				}

			}
			else if(q2.get(2) != q3.get(6) && q2.get(6)==q1.get(2)) {
				//2,3,4연결
				if(q3.get(2)!=q4.get(6)) {
					location_clock(2);
					location_noclock(3);
					location_clock(4);
				}
				//2,3연결
				else {
					location_clock(2);
					location_noclock(3);
				}
			}
			//1,2연결
			else if(q2.get(2) == q3.get(6) && q2.get(6)!=q1.get(2)) {
				location_clock(2);
				location_noclock(1);
			}
			// 모두 연결 안되있음
			else {
				location_clock(2);
			}
		}
		// 반시계방향
		else {
			if (q2.get(2) != q3.get(6) && q2.get(6)!=q1.get(2)) {
				if(q3.get(2)!=q4.get(6)) {
					
					location_noclock(2);
					location_clock(1);
					location_clock(3);
					location_noclock(4);
				}
				else {
				location_noclock(2);
				location_clock(1);
				location_clock(3);
				}
			}
			else if(q2.get(2) != q3.get(6) && q2.get(6)==q1.get(2)) {
				if(q3.get(2)!=q4.get(6)) {
					
					location_noclock(2);
					location_clock(3);
					location_noclock(4);
				}
				else {
				location_noclock(2);
				location_clock(3);
				}
			}
			else if(q2.get(2) == q3.get(6) && q2.get(6)!=q1.get(2)) {
				location_noclock(2);
				location_clock(1);
			}
			else {
				location_noclock(2);
			}
		}
	}
	
	// 3번 톱니
	static void move3(int loc) {
		//시계방향
		if (loc == 1) {
			if (q3.get(2) != q4.get(6) && q2.get(2) != q3.get(6)) {
				if (q1.get(2) != q2.get(6)) {
					location_clock(1);
					location_noclock(2);
					location_clock(3);
					location_noclock(4);
				} else {
					location_noclock(2);
					location_clock(3);
					location_noclock(4);
				}
			}
			else if(q3.get(2) != q4.get(6) && q2.get(2) == q3.get(6)) {
				location_clock(3);
				location_noclock(4);
			}
			else if(q3.get(2) == q4.get(6) && q2.get(2) != q3.get(6)) {
				if (q1.get(2) != q2.get(6)) {
					location_clock(1);
					location_noclock(2);
					location_clock(3);
				}
				else {
					location_noclock(2);
					location_clock(3);
				}
			}
			else {
				location_clock(3);
			}
		}
		// 반시계방향
		else {
			if (q3.get(2) != q4.get(6) && q2.get(2) != q3.get(6)) {
				if (q1.get(2) != q2.get(6)) {
					location_noclock(1);
					location_clock(2);
					location_noclock(3);
					location_clock(4);
				} else {
					location_clock(2);
					location_noclock(3);
					location_clock(4);
				}
			}
			else if(q3.get(2) != q4.get(6) && q2.get(2) == q3.get(6)) {
				location_noclock(3);
				location_clock(4);
			}
			else if(q3.get(2) == q4.get(6) && q2.get(2) != q3.get(6)) {
				if (q1.get(2) != q2.get(6)) {
					location_noclock(1);
					location_clock(2);
					location_noclock(3);
				}
				else {
					location_clock(2);
					location_noclock(3);
				}
			}
			else {
				location_noclock(3);
			}
		}
		
	}
	
	//4번톱니
	static void move4(int loc) {
		// 시계방향
		if(loc==1) {
			if(q3.get(2)!=q4.get(6)) {
				if(q2.get(2)!=q3.get(6)) {
					if(q1.get(2)!=q2.get(6)) {
						location_noclock(1);
						location_clock(2);
						location_noclock(3);
						location_clock(4);
					}
					else {
						location_clock(2);
						location_noclock(3);
						location_clock(4);
					}
				}
				else {
					location_noclock(3);
					location_clock(4);
				}
			}
			else {
				location_clock(4);
			}
		}
		// 반시계방향
		else {
			if(q3.get(2)!=q4.get(6)) {
				if(q2.get(2)!=q3.get(6)) {
					if(q1.get(2)!=q2.get(6)) {
						location_clock(1);
						location_noclock(2);
						location_clock(3);
						location_noclock(4);
					}
					else {
						location_noclock(2);
						location_clock(3);
						location_noclock(4);
					}
				}
				else {
					location_clock(3);
					location_noclock(4);
				}
			}
			else {
				location_noclock(4);
			}
			
			
			
		}
	}
	
	// 시계방향 회전
	static void location_clock(int num) {
		
		if(num==1) {
			q1.add(0, q1.get(7));
			q1.remove(8);
		}
		else if(num==2) {
			q2.add(0, q2.get(7));
			q2.remove(8);
		}
		else if(num==3) {
			q3.add(0, q3.get(7));
			q3.remove(8);
		}
		else if(num==4) {
			q4.add(0, q4.get(7));
			q4.remove(8);
		}
	}
	
	// 반시계방향 회전
	static void location_noclock(int num) {
		
		if(num==1) {
			q1.add(q1.get(0));
			q1.remove(0);
		}
		else if(num==2) {
			q2.add(q2.get(0));
			q2.remove(0);
		}
		
		else if(num==3) {
			q3.add(q3.get(0));
			q3.remove(0);
		}
		else if(num==4) {
			q4.add(q4.get(0));
			q4.remove(0);
		}
	}
	
	
}
