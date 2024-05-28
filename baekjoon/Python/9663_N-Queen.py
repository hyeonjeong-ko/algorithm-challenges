N = int(input())
ans = 0


def dfs(row):
    global ans
    if row == N:
        ans += 1
        return

    for j in range(N):  # 특정행의 열 방문
        # 같은 열, 대각선에 방문 X
        if not v1[j] and not v2[row + j] and not v3[row - j + N]:  # 조건을 and로 연결
            v1[j] = v2[row + j] = v3[row - j + N] = True  # 값을 True로 설정
            dfs(row + 1)
            v1[j] = v2[row + j] = v3[row - j + N] = False  # 값을 False로 재설정


v1 = [False] * N
v2 = [False] * (2 * N)
v3 = [False] * (2 * N)
dfs(0)
print(ans)
