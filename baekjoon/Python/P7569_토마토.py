import sys
input = sys.stdin.readline
from collections import deque

def bfs():
    # [1] q생성 v[]생성
    q = deque()
    v = [[[0] * M for _ in range(N)] for _ in range(H)]

    # [2] q에 초기데이터(들) 삽입, 안익은 토마토 카운트
    cnt = 0
    for h in range(H):
        for i in range(N):
            for j in range(M):
                if arr[h][i][j] == 1: # 익은토마토
                    q.append((h,i,j))
                    v[h][i][j]=1
                elif arr[h][i][j]==0: # 안익은토마토
                    cnt += 1
    while q:
        ch,ci,cj = q.popleft()

        # 6방향, 범위내, 미방문, arr[] == 0
        for dh,di,dj in ((0,-1,0),(0,1,0),(0,0,-1),(0,0,1),(-1,0,0),(1,0,0)):
            nh,ni,nj = ch+dh,ci+di,cj+dj
            if 0<=nh<H and 0<=ni<N and 0<=nj<M and v[nh][ni][nj]==0 and arr[nh][ni][nj]==0:
                q.append((nh,ni,nj))
                v[nh][ni][nj] = v[ch][ci][cj] + 1
                cnt -= 1 # 안익은 토마토 1개 익음
    
    if cnt==0: # 모든토마토 익었음
        return v[ch][ci][cj] - 1
    else:
        return -1


M, N, H = map(int, input().split())
arr = [[list(map(int, input().split())) for _ in range(N)] for _ in range(H)]

ans = bfs()
print(ans)
