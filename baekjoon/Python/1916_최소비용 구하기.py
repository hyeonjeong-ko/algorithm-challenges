import sys
input = sys.stdin.readline
from queue import PriorityQueue

N = int(input())
M = int(input())
adj = [[] for _ in range(N + 1)]
distance = [sys.maxsize] * (N + 1)
visited = [False] * (N + 1)
q = PriorityQueue()

for _ in range(M):
    s, e, v = map(int, input().split())
    adj[s].append((e,v)) # 노드->연결 노드 / 간선
    
S, E = map(int, input().split())

distance[S] = 0 # 거리 초기화
q.put((0, S)) # 거리, 노드

while q.qsize() > 0:
    dist, now = q.get()
    if visited[now]:
        continue
    visited[now] = True
    for next in adj[now]:
        next_node = next[0]
        value = next[1]
        if distance[next_node] > dist + value:
            distance[next_node] = dist + value
            q.put((distance[next_node],next_node))

print(distance[E])
