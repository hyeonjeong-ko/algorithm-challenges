/*
A solution to the card purchasing problem.

Metadata
Difficulty: Intermediate
Time Taken: 1 hr
Correct Answer Rate: 61.582%

Analysis
Input:
- 1 <= N <= 1000 (number of cards to buy)
- 1 <= Pi <= 10,000 (price of card packs)
Expected Time Complexity: O(N^2)

Implementation
Data Structures: 1D List (DP array)
Algorithms: Dynamic Programming
Statements: Nested for loop

Result
Time Complexity: O(N^2)

More
- Key insights: dp[i] stores the maximum cost for purchasing i cards; 
                dp[i] is calculated using dp[i - j] + Pj, where Pj is the price of a pack containing j cards.
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11052 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// 카드팩 가격 입력 받기 (1-based index)
		int[] prices = new int[N + 1]; // 1-based index
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			prices[i] = Integer.parseInt(st.nextToken());
		}

		// DP 배열 초기화
		int[] dp = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= i; j++) {
				dp[i] = Math.max(dp[i], dp[i - j] + prices[j]);
			}
		}
		System.out.println(dp[N]);
	}
}
