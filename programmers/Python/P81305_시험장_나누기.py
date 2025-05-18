// 이진트리, 이분탐색, dfs
// 난이도 상

import sys

sys.setrecursionlimit(10**7)

# 최대 노드 개수 상수
MAX_N = 10**5

# 전역 배열 선언
Num = [0] * MAX_N
child = [[-1, -1] for _ in range(MAX_N)]
parent = [-1] * MAX_N
root = 0
limit = 0
cnt = 0


def dfs(node):
    global cnt, limit
    if node == -1:
        return 0
    # 1) 자신만으로도 limit 초과 → 무조건 잘라야 함
    if Num[node] > limit:
        cnt = MAX_N + 1
        return Num[node] # 어떤값 반환해도 상관X
    # 2) 자식 서브트리 합 재귀 호출
    ls = dfs(child[node][0])
    rs = dfs(child[node][1])
    total = ls + rs + Num[node]
    # 3) 양쪽 합해도 limit 이하 → 하나의 그룹으로 병합
    if total <= limit:
        return total
    # 4) 자식이 하나만 있을 때
    if child[node][0] == -1 or child[node][1] == -1:
        cnt += 1  # 반드시 자식‐부모 간선 하나를 잘라야 그룹 크기 ≤ limit
        return Num[node]  # 잘린 뒤엔 오직 자신만 리턴
    # 5) 두 자식이 모두 있을 때
    #   a) 둘 다 개별로 합치면 각각 limit 이하라면
    if ls + Num[node] <= limit and rs + Num[node] <= limit:
        cnt += 1  # 큰 쪽 트리를 잘라내고 작은 쪽만 남김
        return Num[node] + min(ls, rs)
    #   b) 왼쪽만 합치는 게 가능하다면
    if ls + Num[node] <= limit:
        cnt += 1
        return ls + Num[node]
    #   c) 오른쪽만 합치는 게 가능하다면
    if rs + Num[node] <= limit:
        cnt += 1
        return rs + Num[node]
    #   d) 둘 다 합칠 수 없으면 둘 다 잘라내야 함
    cnt += 2
    return Num[node]


def check(k):
    """
    limit 이하로 그룹을 만들 때
    필요한 cuts 수(cnt)가 k 미만인지 확인
    """
    global cnt
    cnt = 0
    dfs(root)
    # cnt cuts → cnt+1 그룹 → cnt < k 이면 그룹 ≤ k
    return cnt < k


def solution(k, nums, links):
    global root, limit
    n = len(nums)
    total = 0

    # Num, child, parent 초기화
    for i in range(n):
        Num[i] = nums[i]
        total += nums[i]
        child[i][0], child[i][1] = links[i]
        parent[i] = -1

    # parent 배열 구성 & 루트 찾기
    for u in range(n):
        l, r = child[u]
        if l != -1:
            parent[l] = u
        if r != -1:
            parent[r] = u
    for i in range(n):
        if parent[i] == -1:
            root = i
            break

    # 이분 탐색 범위 설정
    low, high = max(nums), total
    answer = high

    while low <= high:
        mid = (low + high) // 2
        limit = mid
        if check(k):
            answer = mid
            high = mid - 1
        else:
            low = mid + 1

    return answer
