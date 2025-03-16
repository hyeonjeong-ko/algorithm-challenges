# 문제 아이디어: 합이 고정된 경우, 모든 수가 서로 최대한 비슷해야 곱이 최대가 된다.

def solution(n, s):
    answer = [0] * n
    
    if n > s:
        return [-1]
    
    q = s // n
    r = s % n
    
    for i in range(n):
        answer[i] = q
    
    for i in range(r):
        answer[i] += 1
    
    answer.sort()
    
    return answer
