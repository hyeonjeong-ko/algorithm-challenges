# algo: simul
# level: hard (내기준 hard...)
# point: 달팽이 템플릿, 좌표 회전

import sys

input = sys.stdin.readline

N = int(input())

arr = [list(map(int, input().split())) for _ in range(N)]


# ← 기준 비율(%). 마지막은 α(나머지)
mul = [2, 10, 7, 1, 5, 10, 7, 1, 2, 0]
# ← 기준 오프셋(행,열): 2%,10%,7%,1%,5%,10%,7%,1%,2%, α(왼1) 순서
base = [
    (-2, 0),
    (-1, -1),
    (-1, 0),
    (-1, 1),
    (0, -2),
    (1, -1),
    (1, 0),
    (1, 1),
    (2, 0),
    (0, -1),
]


def rot(r, c, d):
    # d=0: ←, 1: ↓, 2: →, 3: ↑
    if d == 0:  # ←
        return r, c
    elif d == 1:  # ↓  (90° 반시계)
        return -c, r
    elif d == 2:  # →  (180°)
        return -r, -c
    else:  # ↑  (270° 반시계 = 90° 시계)
        return c, -r


# 모래 분산(moved, outv 값 구하기)
def scatter(ci, cj, d):
    sand = arr[ci][cj]
    if sand == 0:
        return 0
    arr[ci][cj] = 0

    moved = outv = 0

    # 9개 비율 칸
    for i in range(9):
        rr, cc = rot(base[i][0], base[i][1], d)
        ni, nj = ci + rr, cj + cc
        t = (sand * mul[i]) // 100  # 해당 칸으로 휘날리는(분배되는) 모래양
        if 0 <= ni < N and 0 <= nj < N:
            arr[ni][nj] += t
        else:
            outv += t
        moved += t

    # 남은 모래 α(나머지) 처리
    rr, cc = rot(base[9][0], base[9][1], d)
    ni, nj = ci + rr, cj + cc
    t = sand - moved
    if 0 <= ni < N and 0 <= nj < N:
        arr[ni][nj] += t
    else:
        outv += t
    return outv


di = [0, 1, 0, -1]
dj = [-1, 0, 1, 0]
cnt_mx = 1
ci = cj = N // 2
dr = cnt = flag = 0
# t = 1
out_sand = 0
while (ci, cj) != (0, 0):
    ci, cj = ci + di[dr], cj + dj[dr]  # dr방향으로 한 칸 이동
    out_sand += scatter(ci, cj, dr)  # 현재 방향 dr 기준으로 y의 모래 분산
    # arr[ci][cj] = t
    # t += 1
    cnt += 1
    if cnt == cnt_mx:
        cnt = 0
        dr = (dr + 1) % 4  # 방향 전환
        if flag == 0:  # 1,1,2,2,3,3...두 번에 한칸씩 +1
            flag = 1
        else:
            flag = 0
            cnt_mx += 1
print(out_sand)
