package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main14888 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		num = new int[N];

		StringTokenizer token = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(token.nextToken());
		}

		oper = new int[N - 1];

		token = new StringTokenizer(br.readLine());
		int idx = 0;
		for (int i = 0; i < 4; i++) {
			int op = Integer.parseInt(token.nextToken());

			if (op != 0) {
				for (int j = 0; j < op; j++) {
					oper[idx] = i;
					idx++;
				}
			}
		}

		//System.out.println(Arrays.toString(oper));
		
		powerset(0);
		
		System.out.println(max);
		System.out.println(min);
	}

	static int N;
	static int[] num, oper;
	static boolean[] sel;
	static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	static void powerset(int idx) {

		if (idx == N - 1) {
			int sum=num[0];
			
			for (int i = 0; i < N - 1; i++) {
				if (oper[i] == 0) {
					sum += num[i+1];
				} else if (oper[i] == 1) {
					sum -= num[i+1];
				} else if (oper[i] == 2) {
					sum *=num[i+1];
				} else if (oper[i] == 3) {
					sum /= num[i+1];
				}
			}
			
			if(min>sum) {
				min = sum;
			}
			
			if(max<sum) {
				max = sum;
			}
			
		}

		for (int i = idx; i < N - 1; i++) {
			swap(i, idx);
			powerset(idx + 1);
			swap(i, idx);
		}
	}

	static void swap(int a, int b) {
		int tmp = oper[a];
		oper[a] = oper[b];
		oper[b] = tmp;
	}
}
