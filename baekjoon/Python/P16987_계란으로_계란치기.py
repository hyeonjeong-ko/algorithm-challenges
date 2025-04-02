# backtracking

import sys

input = sys.stdin.readline
sys.setrecursionlimit(10000)

N = int(input())
eggs = [list(map(int, input().split())) for _ in range(N)]

answer = 0


def dfs(idx, cnt):
    global answer

    # 종료조건: 모든 계란 다 확인했으면 최대값 갱신
    if idx == N:
        answer = max(answer, cnt)
        return

    # 현재 든 계란이 이미 깨졌다면 그냥 넘기기
    if eggs[idx][0] <= 0:
        dfs(idx + 1, cnt)
        return

    hit = False  # 실제로 부딪힌 적이 있는지 체크

    for j in range(N):
        if idx == j or eggs[j][0] <= 0:  # 자기자신, 깨진계란 PASS
            continue

        # 계란끼리 충돌
        hit = True
        damage_to_idx = eggs[j][1]
        damage_to_j = eggs[idx][1]

        eggs[idx][0] -= damage_to_idx
        eggs[j][0] -= damage_to_j

        broken = 0
        if eggs[idx][0] <= 0:
            broken += 1
        if eggs[j][0] <= 0:
            broken += 1

        dfs(idx + 1, cnt + broken)

        # 원상복구 (백트래킹)
        eggs[idx][0] += damage_to_idx
        eggs[j][0] += damage_to_j

    # 칠 수 있는 계란이 없으면 그냥 넘김
    if not hit:
        dfs(idx + 1, cnt)


dfs(0, 0)  # 현재 계란(idx), 깬 계란 횟수
print(answer)
