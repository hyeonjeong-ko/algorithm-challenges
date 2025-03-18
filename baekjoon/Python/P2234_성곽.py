# bit masking, dfs/bfs

import sys
from collections import deque

sys.setrecursionlimit(10**6)
input = sys.stdin.readline

# 입력: N은 열의 개수, M은 행의 개수
N, M = map(int, input().split())
# 성의 벽 정보가 담긴 그리드 입력
castle = [list(map(int, input().split())) for _ in range(M)]

# 방문 여부와 방 번호를 저장하는 배열
visited = [[False] * N for _ in range(M)]
room_id = [[0] * N for _ in range(M)]
room_sizes = []  # 각 방의 크기를 저장할 리스트

# 이동 방향을 서, 북, 동, 남 순서로 설정 (문제의 비트 순서와 일치)
dy = [0, -1, 0, 1]  # 서, 북, 동, 남에 해당하는 행 변화
dx = [-1, 0, 1, 0]  # 서, 북, 동, 남에 해당하는 열 변화

# BFS 함수 정의
def bfs(start_row, start_col, room_number):
    queue = deque([(start_row, start_col)])
    visited[start_row][start_col] = True
    room_id[start_row][start_col] = room_number
    room_size = 0  # 방의 칸 수
    
    while queue:
        row, col = queue.popleft()
        room_size += 1
        
        # 4방향 탐색 (서, 북, 동, 남 순)
        for i in range(4):
            # 현재 칸의 벽 정보에 따라 i번째 방향으로 이동 가능한지 확인
            # (1 << i)는 서, 북, 동, 남의 벽을 차례로 의미함
            if castle[row][col] & (1 << i):
                continue  # 해당 방향에 벽이 있으므로 이동 불가
            
            new_row = row + dy[i]
            new_col = col + dx[i]
            
            # 그리드 범위 내이며 아직 방문하지 않은 칸이라면
            if 0 <= new_row < M and 0 <= new_col < N and not visited[new_row][new_col]:
                visited[new_row][new_col] = True
                room_id[new_row][new_col] = room_number
                queue.append((new_row, new_col))
    
    return room_size

# 1. 각 방을 번호로 구분하고, 방 크기를 계산
room_count = 0
for i in range(M):
    for j in range(N):
        if not visited[i][j]:
            room_count += 1
            size_of_room = bfs(i, j, room_count)
            room_sizes.append(size_of_room)

# 2. 가장 큰 방의 넓이 구하기
max_room_size = max(room_sizes)

# 3. 하나의 벽을 제거하여 합쳤을 때의 최대 방 넓이 계산
max_combined_room_size = 0
for row in range(M):
    for col in range(N):
        # 4방향 (서, 북, 동, 남) 벽 제거 시뮬레이션
        for i in range(4):
            new_row = row + dy[i]
            new_col = col + dx[i]
            
            if 0 <= new_row < M and 0 <= new_col < N:
                # 현재 칸에 i번째 방향의 벽이 있고, 인접 칸이 다른 방이면
                if castle[row][col] & (1 << i):
                    if room_id[row][col] != room_id[new_row][new_col]:
                        combined_room_size = room_sizes[room_id[row][col] - 1] + room_sizes[room_id[new_row][new_col] - 1]
                        max_combined_room_size = max(max_combined_room_size, combined_room_size)

# 결과 출력: 방의 개수, 가장 큰 방의 크기, 벽 하나 제거 후 합쳐진 최대 방 크기
print(room_count)
print(max_room_size)
print(max_combined_room_size)
