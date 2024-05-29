N, S = map(int, input().split())
lst = list(map(int, input().split()))
sum = 0
ans = 0


def dfs(depth, sum, cnt):
    global ans
    # 종료조건 + 정답처리
    if depth == N:
        if sum == S and cnt > 0:
            ans += 1
        return

    dfs(depth + 1, sum + lst[depth], cnt + 1)  # 포함하는 경우
    dfs(depth + 1, sum, cnt)  # 포함하지 않는 경우


dfs(0, 0, 0)
print(ans)
