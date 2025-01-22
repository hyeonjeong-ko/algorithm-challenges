from collections import deque

def solution(land):
    rows,cols = len(land),len(land[0])
    answer = 0
    dirs = [(-1,0),(1,0),(0,-1),(0,1)]
    visited = [[0] * cols for _ in range(rows)]
    res = [0 for i in range(cols)]
    
    def bfs(i, j):
        q = deque([(i,j)])
        cnt = 1 # 덩어리 크기
        visited[i][j] = 1
        
        # 석유 덩어리가 존재하는 열
        oil_exists = set() # 중복 방지
        oil_exists.add(j)
        
        while q:
            cx, cy = q.popleft()
            for dx,dy in dirs:
                nx, ny = cx + dx, cy + dy
                # 범위내, 미방문, 1
                if (0<=nx<rows) and (0<=ny<cols) and visited[nx][ny] == 0 and land[nx][ny] == 1:
                    q.append((nx, ny))
                    visited[nx][ny] = 1
                    cnt += 1
                    oil_exists.add(ny)
        
        # 석유 덩어리 존재하는 열에 덩어리 더해주기
        for c in oil_exists:
            res[c] += cnt
    
    ### bfs함수끝 --
    
    for i in range(rows):
        for j in range(cols):
            if visited[i][j] == 0 and land[i][j] == 1:
                bfs(i,j)
    
    return max(res)
