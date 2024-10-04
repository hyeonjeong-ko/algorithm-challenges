# dp
def solution(money):
    answer = 0
    dp1 = [0] * len(money)
    dp2 = [0] * len(money)
    # 1번집 훔치는 case
    dp1[0] = money[0]
    for i in range(1, len(money) - 1): # 마지막집은 훔치지 못한다.
        dp1[i] = max(dp1[i-1], dp1[i-2] + money[i])
    # 1번집 안훔치는 case
    dp2[0] = 0
    for i in range(1, len(money)): # 마지막 집도 훔칠 수 있다.
        dp2[i] = max(dp2[i-1], dp2[i-2] + money[i])
    
        
    return max(dp1[i-1],dp2[i])
