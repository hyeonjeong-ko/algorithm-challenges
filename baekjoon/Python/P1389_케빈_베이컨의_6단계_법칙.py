# bfs

import sys
input = sys.stdin.readline
from collections import deque

N, M = map(int, input().split())
adj = [[] for _ in range(N + 1)]
for _ in range(M):
    A, B = map(int, input().split())
    adj[A].append(B)
    adj[B].append(A)

def bfs(num):
    q = deque()
    visited = [False] * (N + 1)
    q.append((num, 0)) # 유저번호,depth
    visited[num] = True

    sum = 0

    while q:
        curr, depth = q.popleft()
        
        # 연결된 친구 탐색
        for i in adj[curr]:
            if not visited[i]:
                q.append((i,depth + 1))
                visited[i] = True
                sum += depth + 1
    return sum


ans = sys.maxsize
res_idx = 0
# 각 사람마다 케빈 베이컨 수 구하기
for i in range(1,N + 1):
    res = bfs(i)
    if res < ans:
        ans = res
        res_idx = i

print(res_idx)
