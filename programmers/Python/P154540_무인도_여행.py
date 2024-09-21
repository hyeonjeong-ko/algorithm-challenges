# dfs,bfs
from collections import deque

def solution(maps):
    d_move = [(0, 1), (0, -1), (1, 0), (-1, 0)]
    
    def bfs(si, sj):
        q = deque([(si, sj)])
        visited[si][sj] = True
        ans = int(maps[si][sj])  # 시작점의 값 추가
        
        while q:
            ci, cj = q.popleft()
            
            for dx, dy in d_move:
                nx = ci + dx
                ny = cj + dy
                # 유효 범위 내, 미방문, 'X'가 아닌 경우
                if 0 <= nx < N and 0 <= ny < M and not visited[nx][ny] and maps[nx][ny] != 'X':
                    ans += int(maps[nx][ny])  # 숫자 값 더하기
                    q.append((nx, ny))  # 큐에 추가
                    visited[nx][ny] = True  # 방문 처리
        
        return ans
    
    N = len(maps)  # 행의 길이
    M = len(maps[0])  # 열의 길이
    visited = [[False] * M for _ in range(N)]
    answer = []
    
    for i in range(N):
        for j in range(M):
            # 숫자 & 미방문시 탐색 시작
            if not visited[i][j] and maps[i][j] != 'X':
                suum = bfs(i, j)
                answer.append(suum)
    
    # 만약 탐색된 영역이 없으면 [-1] 반환
    return sorted(answer) if len(answer) > 0 else [-1]
