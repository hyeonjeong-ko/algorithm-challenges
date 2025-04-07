// dijkstra

import sys
import heapq
import math
from collections import defaultdict


def get_distance(p1, p2):
    return math.sqrt((p1[0] - p2[0]) ** 2 + (p1[1] - p2[1]) ** 2)


# 입력 받기
input = sys.stdin.readline
x1, y1 = map(float, input().split())  # 시작점
x2, y2 = map(float, input().split())  # 도착점
N = int(input())  # 대포 개수

points = [(x1, y1)]  # 시작점 먼저 추가
for _ in range(N):
    cx, cy = map(float, input().split())
    points.append((cx, cy))
points.append((x2, y2))  # 도착점 마지막에 추가

# 총 노드 개수는 N + 2
adj = defaultdict(list)

for i in range(N + 2):
    for j in range(N + 2):
        if i == j:
            continue
        dist = get_distance(points[i], points[j])
        if i == 0:
            # 시작점에서는 무조건 달려야 한다
            time = dist / 5
        else:
            # 대포 or 달리기 중 더 빠른 쪽
            time = min(2 + abs(dist - 50) / 5, dist / 5)
        adj[i].append((j, time))

# 다익스트라 알고리즘
pq = [(0, 0)]  # (시간, 현재 노드)
min_time = [float("inf")] * (N + 2)
min_time[0] = 0

while pq:
    cur_time, now = heapq.heappop(pq)
    if cur_time > min_time[now]:
        continue
    for nxt, t in adj[now]:
        if min_time[nxt] > cur_time + t:
            min_time[nxt] = cur_time + t
            heapq.heappush(pq, (min_time[nxt], nxt))

# 도착점은 인덱스 N + 1
print(min_time[N + 1])
