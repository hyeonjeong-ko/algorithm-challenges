import sys
from collections import deque

input = sys.stdin.readline


def bfs(v):
    q = deque()
    q.append(v)
    visited[v] += 1

    while q:
        curr = q.popleft()
        for i in A[curr]:
            if visited[i] == -1:
                q.append(i)
                visited[i] = visited[curr] + 1


N, M, K, X = map(int, input().split())

A = [[] for _ in range(N + 1)]

ans = []
visited = [-1] * (N + 1)

for _ in range(M):  # 입력받기
    S, E = map(int, input().split())
    A[S].append(E)

bfs(X)

for i in range(N + 1):
    if visited[i] == K:
        ans.append(i)

if not ans:
    print(-1)
else:
    ans.sort()
    for i in ans:
        print(i)
