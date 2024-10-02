"""
memo:
- 예외 처리: 계단의 수가 적은 경우(1개 또는 2개) DP 배열을 개별적으로 초기화
- 조건 반영한 점화식 설계: 세 계단을 연속해서 밟을 수 없다는 조건을 점화식에 반영
- 문제 접근 방식: 시작점부터 하나씩 확인하는 것보다 도착 지점을 기준으로 조건을 만족하는 최적의 경로를 찾는 사고의 전환이 필요
"""
N = int(input())
dp = [0] * (N + 1)
stairs = [0] * (N + 1)
for i in range(1, N + 1):
    stairs[i] = int(input())
if N == 1:
    print(stairs[1])
    exit()
elif N == 2:
    print(stairs[1] + stairs[2])
    exit()

dp[1] = stairs[1]
dp[2] = stairs[1] + stairs[2]
for i in range(3, N + 1):
    dp[i] = max(dp[i-2]+stairs[i], dp[i-3]+stairs[i-1]+stairs[i])
    
print(dp[-1])
