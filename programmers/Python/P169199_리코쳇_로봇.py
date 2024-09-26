"""
리코쳇 로봇 문제 풀이

분석
입력:
- 보드는 행(rows) x 열(cols) 크기의 격자
- 'R'은 시작 위치, 'G'는 목표 지점, 'D'는 장애물, '.'은 빈 공간
- 로봇은 상, 하, 좌, 우로 이동하며, 벽이나 장애물에 부딪힐 때까지 미끄러짐
예상 시간 복잡도: O(행 * 열)

구현
자료 구조:
- 2D 배열(`map`): 보드 상태 저장
- 2D 배열(`visited`): 방문 여부 기록
- 큐(BFS): 탐색 경로와 이동 횟수 관리
- 1D 배열: 좌표와 이동 횟수 저장

알고리즘:
- BFS 사용: 시작 지점에서부터 네 방향으로 미끄러져 이동
- 각 방향에서 벽이나 장애물에 부딪힐 때까지 이동하며, 방문한 적 없는 위치만 탐색

결과
시간 복잡도: O(행 * 열)
- 각 위치에서 네 방향으로 탐색하며, 모든 칸을 한 번씩만 방문

추가
- 핵심: BFS는 가장 먼저 도달하는 경로가 최단 경로임을 보장
- `visited` 배열로 중복 탐색을 방지하여 효율성 유지
"""
from collections import deque

def solution(board):
    start = None
    goal = None
    
    for i in range(len(board)):
        for j in range(len(board[0])):
            if board[i][j] == 'R':
                start = (i, j)
            elif board[i][j] == 'G':
                goal = (i, j)
    return bfs(board, start, goal)


def bfs(board, start, goal):
    directions = [(-1,0),(1,0),(0,-1),(0,1)]
    rows = len(board)
    cols = len(board[0])
    
    q = deque([(start[0], start[1], 0)]) # 행, 열, 이동횟수
    
    visited = [[False] * cols for _ in range(rows)]
    visited[start[0]][start[1]] = True
    
    while q:
        x, y, move_count = q.popleft()
        
        if (x,y) == goal: # 종료 조건
            return move_count # 최소 이동 횟수 반환
        
        for dx, dy in directions:
            nx, ny = x, y
            
            # 장애물이나 벽에 부딪힐 때까지 이동
            while 0 <= nx + dx < rows and 0<=ny+dy<cols and board[nx + dx][ny + dy] != 'D':
                nx += dx
                ny += dy
            
            # 방문X 위치이면 큐에 추가
            if not visited[nx][ny]:
                visited[nx][ny] = True
                q.append((nx, ny, move_count + 1))
    # 목표 지점에 도달할 수 없는 경우
    return -1
