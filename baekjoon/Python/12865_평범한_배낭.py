# 입력 받기
N, K = map(int, input().split())
items = [tuple(map(int, input().split())) for _ in range(N)]

# DP 배열 초기화
dp = [0] * (K + 1)

# DP 계산
for i in range(N):
    W, V = items[i]
    for weight in range(K, W - 1, -1):
        dp[weight] = max(dp[weight], dp[weight - W] + V)

print(dp[K])
