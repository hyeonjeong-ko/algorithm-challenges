# dp
import sys
input = sys.stdin.readline

N = int(input())
arr = [list(map(int, input().split())) for _ in range(N)] #입력받기

# dp 생성 & 초기값 생성
dp = [[0]*N for _ in range(N)]
dp[0][0] = 1

for i in range(N):
    for j in range(N):
        if dp[i][j]>0 and arr[i][j]>0: # 경로O, 점프 숫자O
            jump = arr[i][j]
            if i + jump < N: #아래쪽 범위이내
                dp[i + jump][j] += dp[i][j] # 경우수 더해주기
            if j + jump < N:
                dp[i][j + jump] += dp[i][j]
print(dp[N - 1][N - 1])
