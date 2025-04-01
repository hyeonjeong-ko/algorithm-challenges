import sys

input = sys.stdin.readline
N, C = map(int, input().split())
li = [int(input()) for _ in range(N)]
li.sort()

# 공유기 사이 거리 탐색 범위 지정
left = 1
right = li[-1] - li[0]
answer = 0


# 해당 최소거리로 공유기 설치 가능한지 확인
def check(mid):
    # 1. 첫번째 집에 무조건 설치
    count = 1  # 공유기 설치한 횟수
    last_installed = li[0]

    for i in li:
        if i - last_installed >= mid:
            count += 1
            last_installed = i
    if count >= C:
        return True


while left <= right:
    mid = (left + right) // 2
    if check(mid):
        answer = mid  # 가능하니 일단 저장
        left = mid + 1  # 더 넓은 거리 시도
    else:
        right = mid - 1  # 거리가 너무 멀어서 불가능->줄여야함

print(answer)
