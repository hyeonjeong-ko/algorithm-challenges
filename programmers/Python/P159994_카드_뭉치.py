"""
Queue 자료구조 사용; O(N + M)
- card1과 card2는 무조건 앞부터 사용해야 함.
- 순서를 뒤바꿀 수 없음.
"""
from collections import deque

def solution(cards1, cards2, goal):
    q_cards1 = deque(cards1)
    q_cards2 = deque(cards2)
    q_goal = deque(goal)
    
    while q_goal:
        cur = q_goal.popleft()
        
        if not q_cards1 and not q_cards2:
            return "No"
        
        if q_cards1 and cur == q_cards1[0]:
            q_cards1.popleft()
        elif q_cards2 and cur == q_cards2[0]:
            q_cards2.popleft()
        else:
            return "No"
    
    return "Yes"
