# 슬라이딩 윈도우, 시간 초과 주의

"""
초밥 벨트: [7, 9, 7, 30, 2, 7, 9, 25]
초밥 종류: 30가지, 쿠폰 번호: 30

1. 첫 구간 (i = 0): [7, 9, 7, 30] -> 고유 초밥 종류: 3
2. 슬라이딩 윈도우 이동 (i = 1): [9, 7, 30, 2] -> 고유 초밥 종류: 4
3. 슬라이딩 윈도우 이동 (i = 2): [7, 30, 2, 7] -> 고유 초밥 종류: 3
4. 슬라이딩 윈도우 이동 (i = 3): [30, 2, 7, 9] -> 고유 초밥 종류: 4
5. 슬라이딩 윈도우 이동 (i = 4): [2, 7, 9, 25] -> 고유 초밥 종류: 4

결과: 최댓값 = 5

"""

N, d, k, c = input().split() # 접시수, 초밥 가짓수,연속접시 수, 쿠폰번호
arr = []

for i in range(N):
    arr.append(int(input()))

max_sushi = 0
for i in range(N):
    eat_sushi = 1
    check[c] = 1 # 먹은 쿠폰 번호 체크
    check = [0] * (N + 1)
    
    # i번째 부터 k개 초밥 먹기
    for j in range(i, i + k):
        sushi = arr[j % N]

        if not check[sushi]:
            eat_sushi += 1
        check[sushi] += 1
    max_sushi = max(max_sushi, eat_sushi)

print(max_sushi)

#########################################################
N, d, k, c = map(int, input().split())  # N: 접시수, d: 초밥 가짓수, k: 연속접시 수, c: 쿠폰번호
arr = [int(input()) for _ in range(N)]  # 벨트에 놓인 초밥들

# 초밥 종류를 셀 리스트 (초밥 번호 1부터 d까지, 인덱스 0은 사용하지 않음)
sushi_count = [0] * (d + 1)
unique_sushi = 0  # 현재 먹은 초밥의 종류 수

# 쿠폰 초밥을 1개 먹은 것으로 시작
sushi_count[c] = 1
unique_sushi = 1

# 첫 번째 k개의 초밥을 처리 (슬라이딩 윈도우의 첫 번째 구간)
for i in range(k):
    sushi = arr[i]
    if sushi_count[sushi] == 0:
        unique_sushi += 1
    sushi_count[sushi] += 1

max_sushi = unique_sushi  # 최댓값 초기화

# 슬라이딩 윈도우로 이동하며 계산
for i in range(1, N):
    # i-1 번째 초밥을 제외
    remove_sushi = arr[i - 1]
    sushi_count[remove_sushi] -= 1
    if sushi_count[remove_sushi] == 0:
        unique_sushi -= 1
    
    # i + k - 1 번째 초밥을 추가 (윈도우 오른쪽 끝 초밥 추가)
    add_sushi = arr[(i + k - 1) % N]
    if sushi_count[add_sushi] == 0:
        unique_sushi += 1
    sushi_count[add_sushi] += 1

    # 쿠폰 번호 초밥을 고려하여 최댓값 갱신
    max_sushi = max(max_sushi, unique_sushi)

print(max_sushi)
