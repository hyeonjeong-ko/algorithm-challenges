"""
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
"""

import sys
input = sys.stdin.readline

def dfs(ci, cj):
    if dp[ci][cj] == -1:  # 첫방문
        dp[ci][cj] = 0
        for di, dj in ((-1, 0), (1, 0), (0, -1), (0, 1)):  # 네방향(이전좌표)
            pi, pj = ci + di, cj + dj  # 이전좌표
            if arr[pi][pj] > arr[ci][cj]:  # 내리막길
                dp[ci][cj] += dfs(pi, pj)  # 네방향 경로수 누적
    return dp[ci][cj]

M, N = map(int, input().split())
# 올바르게 arr 생성
arr = [[0] * (N + 2)] + [[0] + list(map(int, input().split())) + [0] for _ in range(M)] + [[0] * (N + 2)]

# dp테이블 생성 및 초기값
dp = [[-1] * (N + 2) for _ in range(M + 2)]
dp[1][1] = 1

print(dfs(M, N))
