# simulation

# 입력 처리
R, C, T = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(R)]

# 공기청정기 위치 찾기
for i in range(R):
    if arr[i][0] == -1:
        i1, i2 = i, i + 1
        arr[i1][0] = arr[i2][0] = 0  # 청정기 위치는 먼지 없음 처리
        break


# 확산 함수
def spread():
    temp = [row[:] for row in arr]  # 현재 상태 복사 (동시 확산을 위해)
    for i in range(R):
        for j in range(C):
            if arr[i][j] > 4:
                amount = arr[i][j] // 5  # 확산할 양
                for di, dj in ((-1, 0), (1, 0), (0, -1), (0, 1)):
                    ni, nj = i + di, j + dj
                    # 4방향, 범위내, 청정기X
                    if (
                        0 <= ni < R
                        and 0 <= nj < C
                        and (ni, nj) != (i1, 0)
                        and (ni, nj) != (i2, 0)
                    ):
                        temp[i][j] -= amount
                        temp[ni][nj] += amount
    return temp


# 공기청정기 작동 함수
def purify():
    # 위쪽 (반시계)
    for i in range(i1 - 1, 0, -1):  # 아래
        arr[i][0] = arr[i - 1][0]
    for j in range(0, C - 1):  # <-
        arr[0][j] = arr[0][j + 1]
    for i in range(0, i1):  # 위로
        arr[i][C - 1] = arr[i + 1][C - 1]
    for j in range(C - 1, 0, -1):
        arr[i1][j] = arr[i1][j - 1]

    # 아래쪽 (시계)
    for i in range(i2 + 1, R - 1):
        arr[i][0] = arr[i + 1][0]
    for j in range(0, C - 1):
        arr[R - 1][j] = arr[R - 1][j + 1]
    for i in range(R - 1, i2, -1):
        arr[i][C - 1] = arr[i - 1][C - 1]
    for j in range(C - 1, 0, -1):
        arr[i2][j] = arr[i2][j - 1]


# T초 동안 시뮬레이션
for _ in range(T):
    arr = spread()
    purify()

# 결과 출력
print(sum(map(sum, arr)))  # 각행에 sum,모든행sum
