T = 10
for test_case in range(1, T + 1):
    _ = input()
    N = 100  # 100 x 100
    arr = [[0] + list(map(int, input().split())) + [0] for _ in range(N)]
    # [1] 마지막 행에서 출발위치 찾기
    ci = N - 1
    for j in range(1, N + 1):
        if arr[ci][j] == 2:
            cj = j
            break

    # [2] 0번행까지 사다리 반대로 오르기
    while ci > 0:
        arr[ci][cj] = 0  # 중복 방문 X
        if arr[ci][cj - 1] == 1:
            cj -= 1
        elif arr[ci][cj + 1] == 1:
            cj += 1
        else:
            ci -= 1

    # 양옆패딩추가했으므로 정답 열번호-1
    print(f"#{test_case} {cj-1}")
