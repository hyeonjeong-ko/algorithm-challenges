# 이분탐색 / 누적합
# 문제핵심: N^2 시간초과 -> nlogn

import sys, bisect
input = sys.stdin.readline

# 입력 받기
N = int(input().strip())
claims = list(map(int, input().split()))

# 양수(k > 0)와 음수(k <= 0) 그룹으로 나누기
positives = []  # "k명 이상"인 발언 (k > 0)
negatives = []  # "(-k)명 이하"인 발언 (k <= 0), 여기서는 -k를 저장

for k in claims:
    if k > 0:
        positives.append(k)
    else:
        negatives.append(-k)  # k가 0이거나 음수인 경우, -k를 저장

# 이분 탐색을 위해 두 리스트 정렬
positives.sort()
negatives.sort()

possible = []

# X: 실제 거짓말쟁이 수, 0부터 N까지 시도
for X in range(N + 1):
# ! 거짓말쟁이수 X일때 진실 수 찾기
    # positives 그룹: "X >= k" 조건을 만족하는 발언의 수
    count_pos = bisect.bisect_right(positives, X)
    
    # negatives 그룹: "X <= -k" 조건을 만족하는 발언의 수.
    # negatives 리스트에는 -k 값들이 오름차순으로 있으므로,
    # negatives에서 X 이상인 원소의 수는:
    count_neg = len(negatives) - bisect.bisect_left(negatives, X)
    
    # X일 때, 전체 참인 발언의 수
    T = count_pos + count_neg
    
    # 문제 조건: 진실을 말하는 사람은 N-X명이어야 함.
    if T == N - X:
        possible.append(X)

# 결과 출력
print(len(possible))
if possible:
    print(" ".join(map(str, possible)))
