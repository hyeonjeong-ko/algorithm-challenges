# 위상정렬 + dp
# hard

from collections import deque
import sys

input = sys.stdin.readline

N = int(input())
M = int(input())

adj = [[] for _ in range(N + 1)]
indigree = [0] * (N + 1)

# dp[i][j] = i를 만들기 위해 기본 부품 j가 몇 개 필요한가
dp = [[0] * (N + 1) for _ in range(N + 1)]

for _ in range(M):
    X, Y, K = map(int, input().split())
    adj[Y].append((X, K))
    indigree[X] += 1


# 위상정렬 수행
q = deque()
for i in range(1, N + 1):
    if indigree[i] == 0:
        q.append(i)
        dp[i][i] = 1  # 기본 부품은 자기 자신 1개 필요

while q:
    cur = q.popleft()
    for nxt, need in adj[cur]:
        for i in range(1, N + 1):
            dp[nxt][i] += dp[cur][i] * need
        indigree[nxt] -= 1
        if indigree[nxt] == 0:
            q.append(nxt)

for i in range(1, N):
    if dp[N][i] > 0:
        print(f"{i} {dp[N][i]}")
