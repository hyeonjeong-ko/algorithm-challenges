import sys

sys.setrecursionlimit(10**6)

N = int(input())
team_size = N // 2

lst = [list(map(int, input().split())) for _ in range(N)]
lst = [[0] * (N + 1)] + [[0] + row for row in lst]  # 1-based, 패딩 추가
ans = float("inf")


def get_score(team):
    s = 0
    size = len(team)
    for i in range(size):
        for j in range(i + 1, size):
            s += lst[team[i]][team[j]] + lst[team[j]][team[i]]
    return s


def dfs(start_index, depth):
    global ans
    if depth == team_size:
        start_team = [i for i in range(1, N + 1) if visited[i]]
        link_team = [i for i in range(1, N + 1) if not visited[i]]
        s_score = get_score(start_team)
        l_score = get_score(link_team)
        ans = min(ans, abs(s_score - l_score))
        return

    for i in range(start_index, N + 1):
        if not visited[i]:
            visited[i] = True
            dfs(i + 1, depth + 1)
            visited[i] = False


visited = [False] * (N + 1)
dfs(1, 0)  # 다음 선택할 수 있는 팀원 후보의 시작 인덱스
print(ans)

"""
def dfs(n, alst, blst):
    global ans
    if n == N:  # 종료 조건
        if len(alst) == len(blst):
            asm = bsm = 0
            for i in range(M):
                for j in range(M):
                    asm += arr[alst[i]][alst[j]]
                    bsm += arr[blst[i]][blst[j]]
            ans = min(ans, abs(asm - bsm))

    dfs(n + 1, alst + [n], blst)
    dfs[n + 1, alst, blst + [n]]


N = int(input())
arr = [list(map(int, input().split())) for _ in range(N)]

M = N // 2
ans = float("inf")
dfs(0, [], [])
print(ans)
"""
