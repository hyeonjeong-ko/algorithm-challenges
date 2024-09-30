"""
중복조합, 백트래킹
"""
def dfs(n, s, lst):
    if n==M:
        ans.append(lst)
        return
    
    for j in range(s, N + 1):
        dfs(n+1, j, lst + [j])


ans = []
N, M = map(int, input().split())
dfs(0, 1, [])
for lst in ans:
    print(*lst)
"""
from itertools import combinations_with_replacement

N, M = map(int, input().split())

li = [i for i in range(1, N + 1)]

ans = combinations_with_replacement(li, M)

for a in ans:
    print(*a)
"""
