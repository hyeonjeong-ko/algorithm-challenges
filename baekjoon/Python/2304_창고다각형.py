"""
A solution to calculate the area formed by columns using their heights.

Metadata
- **Difficulty**: Medium
- **Time Taken**: 1 Hr

Analysis
**Input**:
- A list of pairs `(L, H)`, where `L` is the position of a column and `H` is its height

**Expected Time Complexity**:  1<= L,H <=1,000

**Implementation**
- **Data Structures**: 
  - An array `lst` of size 1001 to store the height of columns at respective positions
- **Algorithms**:
  - Two-pass traversal to calculate the area:
    1. From the left up to the tallest column
    2. From the right up to the tallest column
"""
N = int(input())
lst = [0] * (1001)
mx_i = mx = 0
for _ in range(N):
    L, H = map(int, input().split())
    # L위치에 H 기록
    lst[L] = H
    # [1] mx_i 구하기
    if mx < H:
        mx_i, mx = L, H

# [1] 왼쪽부터 처리
ans = mx = 0
for i in range(mx_i + 1):
    mx = max(mx, lst[i])
    ans += mx
# [2] 오른쪽 처리
mx = 0
for i in range(1000, mx_i, -1):
    mx = max(mx, lst[i])
    ans += mx
print(ans)
