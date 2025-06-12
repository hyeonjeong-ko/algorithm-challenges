# algo: , O(N)
# 왼쪽/오른쪽 중 한쪽에는 무조건 자기보다 큰 풍선만 있어야 살아남음
# = 왼쪽/오른쪽 모두 자기보다 작으면 X
# leftMin[i] i이전값들중 최소값, rightMin[i] i이후값들중 최소값

def solution(a):
    n = len(a)
    leftMin = [float('inf')] * n
    rightMin = [float('inf')] * n
    
    
    min_val = a[0]
    for i in range(1, n):
        leftMin[i] = min_val
        min_val = min(min_val, a[i])
        
    min_val = a[-1]
    for i in range(n-2, -1, -1):
        rightMin[i] = min_val
        min_val = min(min_val, a[i])
    
    cnt = 0
    for i in range(n):
        # 양쪽이 모두 자기보다 작을때
        if a[i] > leftMin[i] and a[i] > rightMin[i]:
            continue # 살아남지 못함
        cnt += 1
    
    return cnt
