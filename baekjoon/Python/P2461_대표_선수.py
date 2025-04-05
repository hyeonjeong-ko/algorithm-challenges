# Two-Pointer

import sys
from collections import defaultdict

input = sys.stdin.readline

N, M = map(int, input().split())
students = []
for class_num in range(N):
    line = list(map(int, input().split()))
    for val in line:
        students.append((val, class_num))  # 능력치,반번호

# 능력치 기준으로 정렬
students.sort()

left = 0
min_diff = float("inf")
count = defaultdict(int)  # 반: 학생수
class_cnt_in_window = 0

for right in range(len(students)):
    ability, class_num = students[right]
    count[class_num] += 1
    if count[class_num] == 1:  # 처음 들어온 반
        class_cnt_in_window += 1  # 윈도우내 반의수+1

    # 모든 반이 포함된 경우
    while class_cnt_in_window == N:
        current_diff = students[right][0] - students[left][0]
        min_diff = min(min_diff, current_diff)

        # 왼쪽 포인터 이동
        count[students[left][1]] -= 1  # 반 학생수 제거
        if count[students[left][1]] == 0:  # 만약 그 반이 이제 존재하지않으면
            class_cnt_in_window -= 1  # 윈도우내 반의수-1
        left += 1
print(min_diff)
