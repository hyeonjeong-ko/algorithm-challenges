# two pointer

import sys

input = sys.stdin.readline


def solution(A, N, M):
    left, right = 0, 0
    answer = float("inf")
    while right < N:
        diff = A[right] - A[left]
        if diff < M:
            right += 1
        elif diff > M:
            answer = min(diff, answer)
            left += 1
        else:
            return M
    return answer


N, M = map(int, input().split())
A = [int(input()) for _ in range(N)]
A.sort()

answer = solution(A, N, M)

print(answer)
