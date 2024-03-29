# 방법1. 가로 세로 탐색
N = int(input())

ans = 0
for i in range(1, N + 1):
    for j in range(i, N + 1):
        if i*j <= N:
            ans += 1
print(ans)

# 방법2. 규칙
N = int(input())

ans = N
for i in range(2, N):
    n = N // i - (i-1)
    if n < 1:
        break
    ans += n
print(ans)
