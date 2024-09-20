"""
A solution to the card purchasing problem.

Metadata
Difficulty: Intermediate
Time Taken: 1 hr
Correct Answer Rate: 61.582%

Analysis
Input:
- 1 <= N <= 1000 (number of cards to buy)
- 1 <= Pi <= 10,000 (price of card packs)
Expected Time Complexity: O(N^2)

Implementation
Data Structures: 1D List (DP array)
Algorithms: Dynamic Programming
Statements: Nested for loop

Result
Time Complexity: O(N^2)

More
- Key insights: dp[i] stores the maximum cost for purchasing i cards; 
                dp[i] is calculated using dp[i - j] + Pj, where Pj is the price of a pack containing j cards.
"""

import sys

input = sys.stdin.readline

N = int(input())

prices = [0] + list(map(int, input().split()))

# DP 배열 초기화
dp = [0] * (N + 1)

for i in range(1, N + 1): # 카드 i개를 구매하는 데 지불할 수 있는 최대 금액을 계산
    for j in range(1, i + 1): # 카드팩 가능조합
        dp[i] = max(dp[i], dp[i - j] + prices[j])

print(dp[N])
