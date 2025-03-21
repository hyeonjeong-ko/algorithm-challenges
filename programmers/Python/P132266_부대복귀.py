from heapq import heappop, heappush
from collections import defaultdict
import sys

def solution(n, roads, sources, destination):
    # 1. 인접리스트 생성
    adj = defaultdict(list)
    for a, b in roads:
        adj[a].append((b,1))
        adj[b].append((a,1))
    
    # 2. 다익스트라 알고리즘 위한 초기화
    visited = [False] * (n + 1)
    dist = [sys.maxsize] * (n + 1)
    dist[destination] = 0
    pq = []
    heappush(pq,(0, destination)) # (거리,현재 정점)
    
    # 3. 다익스트라 알고리즘 수행
    while pq:
        cur_dist, cur_node = heappop(pq)
        if visited[cur_node]:
            continue
        visited[cur_node] = True
        
        for next_node, weight in adj[cur_node]:
            if dist[next_node] > cur_dist + weight:
                dist[next_node] = cur_dist + weight
                heappush(pq, (dist[next_node], next_node))
    # 4. 결과 처리
    answer = [dist[source] if dist[source] != sys.maxsize else -1 for source in sources]
    return answer
