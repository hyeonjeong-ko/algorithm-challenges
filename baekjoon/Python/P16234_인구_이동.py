# algo: bfs 루프호출
# 주의: pypy로 제출시 통과 (py제출은 아직X,시간초과)

from collections import deque
import sys

input = sys.stdin.readline

N, L, R = map(int, input().split())
li = [list(map(int, input().split())) for _ in range(N)]


dirs = [(-1, 0), (1, 0), (0, -1), (0, 1)]  # 상하좌우

days = 0  # 인구 이동 일 수
q = deque()

while True:
    visited = [[False] * N for _ in range(N)]
    moved = False  # 인구 이동

    for r in range(N):
        for c in range(N):
            if not visited[r][c]:
                # 1) 연합 탐색(BFS)
                q.clear()
                q.append((r, c))
                visited[r][c] = True

                union = [(r, c)]
                total = li[r][c]

                while q:
                    cx, cy = q.popleft()
                    # 상하좌우, 범위내, 미방문, L<=차이<=R
                    for dx, dy in dirs:
                        nx, ny = cx + dx, cy + dy
                        if 0 <= nx < N and 0 <= ny < N and not visited[nx][ny]:
                            diff = abs(li[nx][ny] - li[cx][cy])
                            if L <= diff <= R:
                                visited[nx][ny] = True
                                q.append((nx, ny))
                                union.append((nx, ny))
                                total += li[nx][ny]

                # 2) 인구 이동 처리
                if len(union) > 1:
                    new_num = total // len(union)
                    for ux, uy in union:
                        li[ux][uy] = new_num
                    moved = True

    if not moved:
        break
    days += 1

print(days)
