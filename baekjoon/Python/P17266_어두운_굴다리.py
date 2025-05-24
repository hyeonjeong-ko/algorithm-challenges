# 이분탐색

def possible(H):
    covered = 0  # 지금까지 밝힌 구간 끝

    for x in locations:
        if x - H > covered:
            return False

        covered = x + H

        if covered >= N:
            return True
    return False


N = int(input())  # 굴다리 길이
M = int(input())  # 가로등 개수
locations = list(map(int, input().split()))

left = 0
right = N
answer = N
while left <= right:
    mid = (left + right) // 2
    if possible(mid):
        answer = mid
        right = mid - 1
    else:
        left = mid + 1

print(answer)
