/*
주어진 수들 중 M개를 선택하여 길이가 M인 수열을 만든다.
선택한 수열은 비내림차순이어야 한다 (앞의 숫자가 뒤의 숫자보다 작거나 같은 순서).
중복되는 수열을 제거하고, 각 수열을 사전순으로 출력한다.
백트래킹을 사용해 중복을 방지하며, 조건에 맞는 수열을 생성한다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P15664 { // N개의 자연수 중 M개를 고른 수열
	static int N, M;
	static int[] numbers;
	static boolean[] visited;
	static StringBuilder ans = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new int[N];
		visited = new boolean[N];

		// 입력값을 numbers 배열에 저장
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		// 사전순 출력을 위해 정렬
		Arrays.sort(numbers);

		// 백트래킹 시작
		backtracking(0, 0, new StringBuilder());
		System.out.print(ans);
	}

	private static void backtracking(int n, int start, StringBuilder sequence) {
		if (n == M) {
			ans.append(sequence).append("\n");
			return;
		}

		int prev = 0;
		for (int j = start; j < N; j++) { // 탐색후보; 현재값보다 같거나 큰값
			if (!visited[j] && prev != numbers[j]) {
				prev = numbers[j]; // 탐색한 숫자 재탐색 X
				visited[j] = true;

				// 선택한 숫자를 sequence 에 추가하고 백트래킹
				backtracking(n + 1, j + 1, new StringBuilder(sequence).append(numbers[j]).append(" "));

				// 백트래킹 후 visited 상태 되돌리기
				visited[j] = false;
			}
		}
	}
}
