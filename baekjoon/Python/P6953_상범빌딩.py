# BOJ 6593 - 상범 빌딩
# 알고리즘: BFS (3차원 너비 우선 탐색)
# Level: Medium
# 3차원 건물 내에서 S → E로의 최단 시간을 BFS로 구하는 문제. 상하좌우+위아래 6방향 이동.

import sys
from collections import deque

input = sys.stdin.readline

dz = [1, -1, 0, 0, 0, 0]
dx = [0, 0, 1, -1, 0, 0]
dy = [0, 0, 0, 0, 1, -1]

while True:
    L, R, C = map(int, input().split())

    if L == 0 and R == 0 and C == 0:
        break

    building = []
    start = end = None

    for l in range(L):
        floor = []
        for r in range(R):
            row = list(input().strip())
            for c in range(C):
                if row[c] == "S":
                    start = (l, r, c)
                elif row[c] == "E":
                    end = (l, r, c)
            floor.append(row)
        building.append(floor)
        input()  # 층 사이 빈 줄 처리

    # BFS
    visited = [[[False] * C for _ in range(R)] for _ in range(L)]
    q = deque([(*start, 0)])  # (층, 행, 열, 시간)
    visited[start[0]][start[1]][start[2]] = True
    escaped = False

    while q:
        cz, cx, cy, time = q.popleft()

        # 종료조건
        if (cz, cx, cy) == end:
            print(f"Escaped in {time} minute(s).")
            escaped = True
            break

        # 상하좌우위아래 탐색
        for i in range(6):
            nz, nx, ny = cz + dz[i], cx + dx[i], cy + dy[i]
            if 0 <= nz < L and 0 <= nx < R and 0 <= ny < C:  # 범위내
                if (
                    not visited[nz][nx][ny] and building[nz][nx][ny] != "#"
                ):  # 미방문, 벽X
                    visited[nz][nx][ny] = True
                    q.append((nz, nx, ny, time + 1))

    if not escaped:
        print("Trapped!")
