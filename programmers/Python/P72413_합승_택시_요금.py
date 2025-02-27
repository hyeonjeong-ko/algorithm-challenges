from heapq import heappop,heappush
from collections import defaultdict
import math
import sys

def dijkstra(n,start,fares):
    # 인접 리스트 구현
    adj = defaultdict(list)
    visited = [False] * (n + 1)
    
    # 최단거리 배열 초기화
    for s, e, w in fares:
        adj[s].append((e,w))
        adj[e].append((s,w))
        
    # 다익스트라
    pq = []
    heappush(pq, (0, start)) # 비용, 노드
    dist = [sys.maxsize] * (n + 1) # dist 초기화
    dist[start] = 0
    
    while pq:
        w, cur = heappop(pq) # 현재까지 누적비용 w, 현재노드 cur
        if visited[cur]:
            continue
        visited[cur] = True
        
        for n, nw in adj[cur]:
            if dist[n] > w + nw:
                dist[n] = w + nw
                heappush(pq, (dist[n],n))
    return dist


def solution(n, s, a, b, fares):
    answer = 0
    
    # 1. s에서 다익스트라. s~모든 노드 간의 최단거리 탐색
    s_start_dist = dijkstra(n,s,fares)
    s_to_a = s_start_dist[a]
    s_to_b = s_start_dist[b]
    
    # 각자 따로가는비용
    tmp1 = s_to_a + s_to_b
    
    tmp2 = sys.maxsize
    # 2. 각 중간지점 x 후보들에서 모두 다익스트라
    for x in range(1, n + 1):
        if x == s:
            continue
        
        x_start_dist = dijkstra(n,x,fares)
        x_to_a = x_start_dist[a]
        x_to_b = x_start_dist[b]
        x_to_s = x_start_dist[s]
        
        cost = x_to_a + x_to_b + x_to_s
        
        tmp2 = min(tmp2,cost)
    
    answer = min(tmp2, tmp1)
    return answer
