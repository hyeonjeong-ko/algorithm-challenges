# simul

import sys

input = sys.stdin.readline

K = int(input())
directions = []
lengths = []
for _ in range(6):
    d, l = map(int, input().split())
    directions.append(d)
    lengths.append(l)

# 가로 중(동1,서2) 최대값 찾기
max_width = 0
idx_w = -1
for i in range(6):
    if directions[i] == 1 or directions[i] == 2:
        if lengths[i] > max_width:
            max_width = lengths[i]
            idx_w = i

# 세로 중 최대값 찾기
max_height = 0
idx_h = -1
for i in range(6):
    if directions[i] == 3 or directions[i] == 4:
        if lengths[i] > max_height:
            max_height = lengths[i]
            idx_h = i

# 큰 사각형 넓이
big_area = max_width * max_height

# 잘린 작은 사각형 구하기
small_width = lengths[(idx_w + 3) % 6]
small_height = lengths[(idx_h + 3) % 6]
small_area = small_width * small_height

# 실제 참외밭넓이 구하기
field_area = big_area - small_area

# 1m^2당 참외개수 k
result = field_area * K

print(result)
