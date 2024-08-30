/*
A solution to determine the minimum number of operations to transform x into y by either adding n, doubling, or tripling x.
Metadata
- **Difficulty**: Medium
- **Time Taken**: 1 hr
분석
**입력**:
- 세 개의 정수 `x`, `y`, `n`
**예상 시간 복잡도**: O(y), 여기서 1 <= y <= 1,000,000
구현
- **자료 구조**:
  - 동적 프로그래밍 배열 `dp`, 여기서 `dp[i]`는 `x`에서 `i`에 도달하기 위한 최소 연산 횟수를 저장
- **알고리즘**:
  - 동적 프로그래밍:
    1. `x`에서 `y`까지 반복하면서 각 가능한 연산(덧셈, 2배, 3배)에 대해 배열 `dp`를 갱신
*/
import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {        
        int[] dp = new int[y + 1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[x] = 0;
        
        for (int i = x; i <=y; i++) {
            if (dp[i] == Integer.MAX_VALUE) continue;
            if (i + n <= y) {
                dp[i + n] = Math.min(dp[i + n], dp[i] + 1);
            }
            if (i * 2 <= y) {
                dp[i * 2] = Math.min(dp[i * 2], dp[i] + 1);
            }
            if (i * 3 <= y) {
                dp[i * 3] = Math.min(dp[i * 3], dp[i] + 1);
            }
        }
            
        return dp[y]!=Integer.MAX_VALUE? dp[y] : -1;
    }
}
