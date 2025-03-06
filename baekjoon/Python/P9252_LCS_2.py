# LCS Algorithm

import sys
sys.setrecursionlimit(10000)
input = sys.stdin.readline
A = list(input())
A.pop() # \n문자열 제거
B = list(input())
B.pop() # \n문자열 제거

dp = [[0 for j in range(len(B) + 1)] for i in range(len(A) + 1)]
path = []

for i in range(1, len(A) + 1):
    for j in range(1, len(B) + 1):
        if A[i - 1] == B[j - 1]: # dp테이블에서 i,j 에 대응하는 문자값
            # 같은 문자열일때 왼쪽 대각선 값 + 1
            dp[i][j] = dp[i - 1][j - 1] + 1
        else:
            # 다르면 왼쪽과 위의 값 중 큰 수
            dp[i][j] = max(dp[i - 1][j],dp[i][j - 1])

print(dp[len(A)][len(B)]) # LCS길이

# LCS 구현 함수
def getText(r, c):
    if r == 0 or c == 0:
        return
    if A[r - 1] == B[c - 1]: # 같으면 LCS에 기록하고 왼쪽 위로 이동
        path.append(A[r - 1])
        getText(r - 1, c - 1)
    else:   # 다르면 왼쪽과 위의 값 중 큰 수로 이동
        if dp[r - 1][c] > dp[r][c - 1]:
            getText(r - 1, c)
        else:
            getText(r, c - 1)

getText(len(A), len(B))

for i in range(len(path) - 1, -1, -1):
    print(path.pop(i), end='')

print()
