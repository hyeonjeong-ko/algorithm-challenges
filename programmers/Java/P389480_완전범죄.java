/*
algo: dp
level: hard

1. dp[i][j] = i개 물건을 처리하고 B 흔적이 j일 때 A의 최소 흔적
2. dp[0][0] = 0 으로 초기화, 나머지는 INF
3. 각 물건마다 A/B가 훔치는 두 경우를 탐색
4. 조건 만족하면 다음 상태로 dp 갱신
5. 마지막 줄(dp[N])에서 최소 A 흔적값 반환
*/


class Solution {
    public int solution(int[][] info, int n, int m) {
        int N = info.length;
        int INF = Integer.MAX_VALUE;

        // dp[i][j] = i개 물건을 처리했을 때 B의 흔적이 j일 경우 A의 최소 흔적
        int[][] dp = new int[N + 1][m];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = INF;
            }
        }
        dp[0][0] = 0;

        for (int i = 0; i < N; i++) { // i번째 물건
            int aTrace = info[i][0];
            int bTrace = info[i][1];

            for (int j = 0; j < m; j++) {
                if (dp[i][j] == INF) continue;

                // A 도둑이 훔치는 경우
                if (dp[i][j] + aTrace < n)
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + aTrace);

                // B 도둑이 훔치는 경우
                if (j + bTrace < m)
                    dp[i + 1][j + bTrace] = Math.min(dp[i + 1][j + bTrace], dp[i][j]);
            }
        }

        int answer = INF;
        for (int j = 0; j < m; j++) {
            answer = Math.min(answer, dp[N][j]);
        }
        return (answer < INF)? answer : - 1;
    }
}
