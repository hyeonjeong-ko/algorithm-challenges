# mst algo
# 임의의 가상 노드 생성해, 우물비용을 가중치로 둔다.

import sys
import heapq

input = sys.stdin.readline

N = int(input())
wells = [int(input()) for _ in range(N)]  # 우물파기 비용(0~N-1)

# 가상노드와 연결
pq = []
for i, cost in enumerate(wells):
    heapq.heappush(pq, (cost, i, N))  # 우물파기비용, 노드, 가상노드 N

# 연결비용 입력받기
for i in range(N):
    row = list(map(int, input().split()))
    for j in range(i + 1, N):
        cost = row[j]
        heapq.heappush(pq, (cost, i, j))

# 크루스칼
parent = list(range(N + 1))


def find(a):
    if a == parent[a]:
        return a
    else:
        parent[a] = find(parent[a])
        return parent[a]


def union(a, b):
    a = find(a)
    b = find(b)
    if a != b:
        parent[b] = a


use_edge = 0
res = 0

while use_edge < N:
    cost, u, v = heapq.heappop(pq)
    if find(u) != find(v):
        union(u, v)
        res += cost
        use_edge += 1

print(res)
