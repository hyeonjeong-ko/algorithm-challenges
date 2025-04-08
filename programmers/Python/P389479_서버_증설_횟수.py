from collections import deque
import math

def solution(players, m, k):
    answer = 0
    servers = deque() # 시작시간, 서버증설수
    
    for time in range(24):
        # 1. 유효기간 지난 서버 제거
        while servers and servers[0][0] + k <= time:
            servers.popleft()
        # 2. 살아있는 서버 수
        curr = sum(cnt for _, cnt in servers)
        
        # 3. 필요한 서버 수
        need = players[time] // m
        
        # 4. 부족시 증설
        if need > curr:
            add = need-curr
            servers.append((time,add))
            answer += add
    
    return answer
