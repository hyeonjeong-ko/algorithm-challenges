# algo: backtracking

from itertools import combinations
import sys

input = sys.stdin.readline
n, m = map(int, input().split())
city = [list(map(int, input().split())) for _ in range(n)]

houses = []
chickens = []

for r in range(n):
    for c in range(n):
        if city[r][c] == 1:
            houses.append((r, c))
        elif city[r][c] == 2:
            chickens.append((r, c))

# 모든 치킨집 중에서 m개 고르기
chicken_combs = list(combinations(chickens, m))


def get_chicken_dist(seleted_chickens):
    total = 0
    for hx, hy in houses:
        min_dist = float("inf")
        for cx, cy in seleted_chickens:
            dist = abs(hx - cx) + abs(hy - cy)
            min_dist = min(min_dist, dist)
        total += min_dist
    return total


ans = float("inf")
for comb in chicken_combs:
    dist = get_chicken_dist(comb)
    ans = min(ans, dist)

print(ans)
