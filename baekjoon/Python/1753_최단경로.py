import sys
input = sys.stdin.readline
from queue import PriorityQueue

V, E = map(int, input().split())
S = int(input())
distance = [sys.maxsize] * (V + 1)
visited = [False] * (V + 1)
myList = [[] for _ in range(V + 1)]
q = PriorityQueue()

for _ in range(E): # 가중치 인접 리스트 초기화
    u, v, w = map(int, input().split())
    myList[u].append((v,w)) # myList 각 Node->(연결노드,가중치)

q.put((0, S)) # 시작점 q에 삽입
distance[S] = 0 # 시작점 거리 초기화

while q.qsize() > 0:
    current = q.get()
    c_v = current[1] # 타깃 노드
    if visited[c_v]:
        continue
    visited[c_v] = True # 방문표시
    for tmp in myList[c_v]:
        next = tmp[0]
        value = tmp[1] # 간선 가중치
        if distance[next] > distance[c_v] + value:
            distance[next] = distance[c_v] + value
            # 가중치가 정렬 기준이므로 순서를 (가중치,타깃 노드)로 삽입
            q.put((distance[next], next))

for i in range(1, V + 1):
    if visited[i]:
        print(distance[i])
    else:
        print("INF")
