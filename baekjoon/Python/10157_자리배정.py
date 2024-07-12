"""A solution to seating allocation problem.

Metadata
Difficulty: Intermediate
Time Taken: 1 hr
Correct Answer Rate: 39.834%

Analysis
Input: 
- 1 <= C, R <= 10^3 (number of columns and rows)
- 1 <= K <= R * C (target seat number)
Expected Time Complexity: O(R * C)

Implementation
Data Structures: 2D List
Algorithms: Simulation, Directional Movement
Statements: for loop, while

Result
Time Complexity: O(R * C)

More
- Key insights: Padding the grid with 1s to avoid boundary checks; Using (dr + 1) % 4 for direction changes.
"""


C, R = map(int, input().split())
K = int(input())

if R * C < K:  # 배정이 불가능한 경우 0
    print(0)
else:  # 배정하면서 K가 되면 그때 좌표 출력
    di, dj = [1, 0, -1, 0], [0, 1, 0, -1]
    # 배열을 1로 둘러싸면: 범위체크 필요X
    arr = [[1] * (C + 2)] + [[1] + [0] * C + [1] for _ in range(R)] + [[1] * (C + 2)]

    ci, cj, dr = 1, 1, 0
    for n in range(1, K):
        arr[ci][cj] = n
        ni, nj = ci + di[dr], cj + dj[dr] # 예상 다음 좌표
        if arr[ni][nj] == 0:  # 비어있으니 이동가능
            ci, cj = ni, nj
        else:  # 범위밖 or 이미 방문한 위치
            dr = (dr + 1) % 4
            ci, cj = ci + di[dr], cj + dj[dr]
    print(f"{cj} {ci}")
