/*
메타데이터
- **난이도**: Hard
- **소요 시간**: 5hr
분석
**문제**:
- 주어진 MxN 크기의 높이 격자에서, 항상 낮은 높이로만 이동하여 좌상단에서 우하단까지 도달할 수 있는 경로의 수를 계산해야 함
**초기 접근**:
- **알고리즘**: 깊이 우선 탐색(DFS)
- **예상 시간 복잡도**: O(4^(M*N))
- **문제점**:
  - 순수 DFS는 동일한 경로를 여러 번 탐색하게 되어, 불필요한 계산이 반복되며 시간 초과가 발생.
  - 격자 내 각 위치에서 여러 번 경로 계산
  
**최적화**:
- **Memorization & dp**:
  - 이미 계산된 경로의 수를 저장하여 동일한 경로를 다시 탐색하지 않는다.
  - 이를 통해 시간 복잡도를 O(M*N)으로 줄일 수 있다.
**결론**:
- **최종 예상 시간 복잡도**: O(M*N)
- **최적화된 구현**:
  - 메모이제이션을 적용한 DFS를 사용하여 문제를 효율적으로 해결하고, 중복 계산을 방지
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import java.io.*;
import java.util.*;

public class Main {
    static int[][] arr;
    static int[][] dp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 세로 크기
        int M = Integer.parseInt(st.nextToken()); // 가로 크기

        arr = new int[N + 2][M + 2];
        dp = new int[N + 2][M + 2];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        // 배열 입력받기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine()); // 각 줄마다 새 StringTokenizer 생성
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[1][1] = 1;
        System.out.println(dfs(N, M));
    }

    public static int dfs(int ci, int cj) {
        if (dp[ci][cj] == -1) { // 첫 방문
            dp[ci][cj] = 0; // 방문 표시

            for (int d = 0; d < 4; d++) {
                int pi = ci + dx[d];
                int pj = cj + dy[d];

                // 내리막길만 탐색
                if (arr[pi][pj] > arr[ci][cj]) {
                    dp[ci][cj] += dfs(pi, pj);
                }
            }
        }
        return dp[ci][cj];
    }
}
