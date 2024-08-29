import sys
input = sys.stdin.readline

def dfs(ci, cj):
    if dp[ci][cj] == -1:  # 첫방문
        dp[ci][cj] = 0
        for di, dj in ((-1, 0), (1, 0), (0, -1), (0, 1)):  # 네방향(이전좌표)
            pi, pj = ci + di, cj + dj  # 이전좌표
            if arr[pi][pj] > arr[ci][cj]:  # 내리막길
                dp[ci][cj] += dfs(pi, pj)  # 네방향 경로수 누적
    return dp[ci][cj]

M, N = map(int, input().split())
# 올바르게 arr 생성
arr = [[0] * (N + 2)] + [[0] + list(map(int, input().split())) + [0] for _ in range(M)] + [[0] * (N + 2)]

# dp테이블 생성 및 초기값
dp = [[-1] * (N + 2) for _ in range(M + 2)]
dp[1][1] = 1

print(dfs(M, N))
