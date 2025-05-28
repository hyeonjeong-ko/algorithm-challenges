# algo: dp 2차원
# hard!!!

def solution(arr):
    # 숫자와 연산자를 분리
    nums = list(map(int, arr[0::2]))
    ops = arr[1::2]
    n = len(nums)

    # dp_max[i][j]: i~j구간에서 얻을 수 있는 최댓값
    # dp_min[i][j]: i~j구간에서 얻을 수 있는 최소값
    dp_max = [[float("-inf")] * n for _ in range(n)]
    dp_min = [[float("inf")] * n for _ in range(n)]

    # 길이 1 구간 초기화
    for i in range(n):
        dp_max[i][i] = dp_min[i][i] = nums[i]

    # 구간 길이 2~n 차례로 계산
    for length in range(2, n + 1):
        for i in range(n - length + 1):
            j = i + length - 1
            # 구간 [i..j] 연산자 k로 분할
            for k in range(i, j):
                if ops[k] == "+":
                    cand_max = dp_max[i][k] + dp_max[k + 1][j]
                    cand_min = dp_min[i][k] + dp_min[k + 1][j]
                else:
                    cand_max = dp_max[i][k] - dp_min[k + 1][j]
                    cand_min = dp_min[i][k] - dp_max[k + 1][j]
                # dp 후보값중 최대최소 갱신
                dp_max[i][j] = max(dp_max[i][j], cand_max)
                dp_min[i][j] = max(dp_min[i][j], cand_min)

    return dp_max[0][n - 1]  # 전체 구간의 최댓값


arr = ["1", "-", "3", "+", "5", "-", "8"]
print(solution(arr))
