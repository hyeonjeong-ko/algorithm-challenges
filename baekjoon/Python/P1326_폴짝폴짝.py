# algo: bfs

from collections import deque
import sys

input = sys.stdin.readline

N = int(input())
arr = list(map(int, input().split()))
a, b = map(int, input().split())
a -= 1
b -= 1


def bfs(start, target):
    visited = [False] * N
    queue = deque()
    queue.append((start, 0))  # (현재 위치, 점프 횟수)
    visited[start] = True

    while queue:
        cur, cnt = queue.popleft()

        if cur == target:
            return cnt

        k = arr[cur]

        # 앞으로 점프
        i = 1
        while cur + k * i < N:
            next_pos = cur + k * i
            if not visited[next_pos]:
                visited[next_pos] = True
                queue.append((next_pos, cnt + 1))
            i += 1

        # 뒤로 점프
        i = 1
        while cur - k * i >= 0:
            next_pos = cur - k * i
            if not visited[next_pos]:
                visited[next_pos] = True
                queue.append((next_pos, cnt + 1))
            i += 1

    return -1  # 도달 불가능


# 실행
print(bfs(a, b))
