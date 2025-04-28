# simulation

from collections import deque
import sys

input = sys.stdin.readline

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]


def apply_gravity(grid):
    "각 열마다 빈칸 위 뿌요 아래로 잡아 바닥으로 내리기"
    N, M = 12, 6
    for c in range(M):
        stack = []
        # 한 열의 뿌요만 stack에 아래→위 순서로 저장
        for r in range(N - 1, -1, -1):
            if grid[r][c] != ".":
                stack.append(grid[r][c])

        # 아래에서 위로 채워넣기
        for r in range(N - 1, -1, -1):
            grid[r][c] = stack.pop(0) if stack else "."


def bfs_remove(grid, visited, sr, sc):
    N, M = 12, 6
    color = grid[sr][sc]
    q = deque([(sr, sc)])
    visited[sr][sc] = True
    cells = [(sr, sc)]

    while q:
        r, c = q.popleft()
        for i in range(4):
            nr, nc = r + dr[i], c + dc[i]
            if (
                0 <= nr < N
                and 0 <= nc < M
                and not visited[nr][nc]
                and grid[nr][nc] == color
            ):
                visited[nr][nc] = True
                q.append((nr, nc))
                cells.append((nr, nc))

    if len(cells) >= 4:
        for r, c in cells:
            grid[r][c] = "."
        return True
    return False


def simulate(grid):
    chain = 0
    while True:
        visited = [[False] * 6 for _ in range(12)]
        removed_any = False

        # 4개이상연속뿌요 없애기
        for r in range(12):
            for c in range(6):
                if grid[r][c] != "." and not visited[r][c]:
                    if bfs_remove(grid, visited, r, c):
                        removed_any = True

        if not removed_any:
            break

        apply_gravity(grid)
        chain += 1

    return chain


grid = [list(input().rstrip()) for _ in range(12)]
result = simulate(grid)
print(result)
