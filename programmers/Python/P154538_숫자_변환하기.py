"""
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
"""
def solution(x, y, n):
    answer = 0
    dp = [float("inf")] * (y + 1)  # x에서 i가 되는 최소 연산 횟수를 저장하는 배열 초기화
    dp[x] = 0  # 초기값 설정. 시작점 x에서 x까지의 연산 횟수는 0
    
    for i in range(y+1):  # x부터 y까지의 모든 수에 대해 최소 연산 횟수 계산
        if i + n <= y:
            dp[i + n] = min(dp[i + n], dp[i] + 1)
        
        if i * 2 <= y:
            dp[i * 2] = min(dp[i * 2], dp[i] + 1)
            
        if i * 3 <= y:
            dp[i * 3] = min(dp[i * 3], dp[i] + 1)
    
    return dp[y] if dp[y] != float("inf") else -1  # y에 도달할 수 있으면 최소 연산 횟수를 반환하고, 그렇지 않으면 -1 반환
