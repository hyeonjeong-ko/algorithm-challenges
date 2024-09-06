"""
metadata
- **난이도**: 중간
- **소요 시간**: 1hr
analysis
**문제**:
- 주어진 N일 동안 각 일자마다 상담할지 말지를 선택해 최대 수익을 계산해야 함. 상담 시 해당 일자와 이후 일정 기간 동안 다른 상담을 할 수 없음.
**초기 접근**:
- **알고리즘**: 깊이 우선 탐색(DFS), BackTracking
- **예상 시간 복잡도**: O(2^N)
- **문제점**:
  - 단순 DFS는 모든 상담을 선택할지 말지를 재귀적으로 탐색하므로, 시간이 오래 걸림.
  - N 최대 약 50일때까지만 가능
  
**최적화**:
- **메모이제이션(Memoization)**:
  - 이미 계산된 경로에서의 최대 수익을 저장하여 동일한 경로를 다시 탐색하지 않음.
  - 이를 통해 시간 복잡도를 O(N)으로 줄일 수 있음.
  
**결론**:
- **최종 예상 시간 복잡도**: O(N)
- **최적화된 구현**:
  - 메모이제이션을 적용한 DFS를 사용하여 중복 계산을 방지하고, 문제를 효율적으로 해결.
"""
N = int(input())
t = [0] * N
p = [0] * N
for i in range(N):
    t[i], p[i] = map(int, input().split())

dp = [0] * (N + 1)
for n in range(N-1, -1, -1):
    if n + t[n] <= N: # 기간내 상담완료 가능
        dp[n] = max(dp[n+1], dp[n + t[n]] + p[n]) # i번째 상담결정시 최대수익
    else:
        dp[n] = dp[n+1]

print(dp[0])


""" 내풀이
N = int(input())
t = [0] + []
p = [0] + []
ans = 0

for _ in range(N): # 입력받기
    T, P = map(int, input().split())
    t.append(T)
    p.append(P)

def dfs(i, price):
    global ans
    ans = max(ans, price)
    i += t[i]  # 현재 상담이 끝난 후 날짜로 이동
    for j in range(i, N + 1):
        if j + t[j] - 1 <= N:  # 상담이 퇴사일을 넘기지 않을 경우
            dfs(j, price + p[j])

for i in range(1, N + 1):
    if i + t[i] - 1 <=  N:  # 상담이 퇴사일전 끝날때만
        dfs(i, p[i])

print(ans)

"""

""" 백트래킹풀이
def dfs(i, sm):
    global ans
    
    if i >= N: # 종료조건
        ans = max(ans, sm)
        return
    
    if i + T[i] <= N: # 퇴사일전 상담가능(선택O)
        dfs(i + T[i], sm + P[i])
    dfs(i + 1, sm) # 상담하지 않는 경우(선택X) (항상가능)
    

N = int(input())
T = [0] * N
P = [0] * N
for i in range(N):
    T[i], P[i] = map(int, input().split())

ans = 0
dfs(0, 0) # 일자(0-based),해당일자까지 누적금액
"""
