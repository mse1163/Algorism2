package jungol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main2247 {
	static class Point implements Comparator<Point>{
		int start, end;
		
		public Point(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return start + "," + end ;
		}

		@Override
		public int compare(Point o1, Point o2) {
			// TODO Auto-generated method stub
			return Integer.compare(o1.end,o2.end);
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		arr = new Point[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(token.nextToken());
			int E = Integer.parseInt(token.nextToken());

			arr[i] = new Point(S, E);

		}

		Arrays.sort(arr, new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				// TODO Auto-generated method stub
				return o1.start-o2.start;
			}
		});

		//System.out.println(Arrays.toString(num));
		time();
	}

	static Point[] arr;
	static int N;
	static int[] num;

	static void time() {

		int max_cnt = -1;
		int max_bin = -1;
		int start = Integer.MAX_VALUE;
		int end = Integer.MIN_VALUE;
		int start_bin = 0;
		int end_bin = 0;
		int bin_cnt=0;
		int cnt = 0;

		start = arr[0].start;
		end = arr[0].end;
		for (int i = 1; i < N; i++) {
            // 학생들 있는 시간을 최대로 구하기위해 시작과 끝을 늘려줌
			if (end >= arr[i].start) {
				if (start > arr[i].start) {
					start = arr[i].start;
				}
				if (end < arr[i].end) {
					end = arr[i].end;
				}

			} else {
                // 비어있는 시간구하기
				 start_bin = end;
				 end_bin = arr[i].start;
				 bin_cnt = end_bin - start_bin;

				start = arr[i].start;
				end = arr[i].end;

			}
            // 학생들 있는 시간
			cnt = end - start;
			if (max_cnt < cnt) {
				max_cnt = cnt;
			}
            // 비어있는 시간
			if (max_bin < bin_cnt) {
				max_bin = bin_cnt;
			}
		}

		System.out.println(max_cnt + " " + max_bin);

	}
}
