from collections import deque
def solution(board):
    answer = 0
    N = len(board)
    # 상, 하, 좌, 우 방향
    directions = [(-1,0), (1,0), (0,-1), (0,1)]
    
    # (x,y,방향)에 대한 최소비용 상태 저장
    visited = [[[float("inf")] * 4 for _ in range(N)] for _ in range(N)]
    
    # 시작점 (0,0)에서 4방향으로 출발
    queue = deque([(0,0,-1,0)]) # (x,y,방향,비용)
    
    # 시작점에서 네 방향 모두 비용을 0으로 설정
    for i in range(4):
        visited[0][0][i] = 0
        
        while queue:
            x, y, dir, cost = queue.popleft()
            
            # 현재 방향에서 4방향으로 탐색
            for i in range(4):
                nx, ny = x + directions[i][0], y + directions[i][1]
                
                if 0<=nx<N and 0<=ny<N and board[nx][ny] == 0:
                    # 직진할 경우 비용 100, 코너일 경우 500원 추가 (# 직선 도로 100원 + 코너 500원)
                    new_cost = cost + 100 if dir == i or dir == -1 else cost + 600
                    
                    # 더 적은 비용으로 방문할 수 있다면 업데이트
                    if new_cost < visited[nx][ny][i]:
                        visited[nx][ny][i] = new_cost
                        queue.append((nx, ny, i, new_cost))

    
    # 도착점에서 최단 거리 구하기 (최소 비용 찾기)
    return min(visited[N - 1][N - 1])
