# BFS

from collections import deque

def solution(storage, requests):
    n, m = len(storage), len(storage[0])
    N, M = n+2, m+2

    # 1) 패딩
    pad_row = [''] * M
    middle  = [[''] + list(r) + [''] for r in storage]
    matrix  = [pad_row] + middle + [pad_row]

    dirs = [(-1,0),(1,0),(0,-1),(0,1)]

    # 2) 지게차
    def remove_by_forklift(target):
        visited = [[False]*M for _ in range(N)]
        q = deque([(0, 0)])
        visited[0][0] = True

        to_remove = set()

        while q:
            x, y = q.popleft()
            for dx, dy in dirs:
                nx, ny = x+dx, y+dy
                if 0 <= nx < N and 0 <= ny < M and not visited[nx][ny]:
                    if matrix[nx][ny] == '':
                        visited[nx][ny] = True
                        q.append((nx, ny))
                    elif matrix[nx][ny] == target:
                        to_remove.add((nx, ny))
                        visited[nx][ny] = True

        for i, j in to_remove:
            matrix[i][j] = ''

    # 3) 크레인
    def remove_by_crane(target):
        for i in range(1, N-1):
            for j in range(1, M-1):
                if matrix[i][j] == target:
                    matrix[i][j] = ''

    # 4) 요청 처리
    for req in requests:
        if len(req) == 1:
            remove_by_forklift(req)
        else:
            remove_by_crane(req[0])

    # 5) 남은 개수 세기
    answer = 0
    for i in range(1, N-1):
        for j in range(1, M-1):
            if matrix[i][j] != '':
                answer += 1

    return answer
