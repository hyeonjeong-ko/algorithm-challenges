"""
@backtracking; N개의 자연수 중 M개를 고른 수열
주어진 수들 중 M개를 선택하여 길이가 M인 수열을 만든다.
선택한 수열은 비내림차순이어야 한다 (앞의 숫자가 뒤의 숫자보다 작거나 같은 순서).
중복되는 수열을 제거하고, 각 수열을 사전순으로 출력한다.
백트래킹을 사용해 중복을 방지하며, 조건에 맞는 수열을 생성한다.
"""

def backtracking(depth, start, seq):
    if len(seq) == M:
        ans.append(seq)
        return

    prev = 0
    for i in range(start, N):
        if not visited[i] and prev != li[i]:
            prev = li[i]
            visited[i] = True

            backtracking(depth + 1, i + 1, seq + [li[i]])

            visited[i] = False


N, M = map(int, input().split())
li = sorted(map(int, input().split()))
visited = [False] * N

ans = []
backtracking(0, 0, [])

for lst in ans:
    print(*lst)
